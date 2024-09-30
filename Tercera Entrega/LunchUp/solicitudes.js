// Variable para rastrear si ya se han cargado las solicitudes
let solicitudesCargadas;
// Función para crear una fila de solicitud en la tabla
function createSolicitudRow(usuario, diaMasCercano) {
  const row = document.createElement("tr");
  const nombreCell = document.createElement("td");
  nombreCell.textContent = `${usuario.firstName} ${usuario.lastName}`;

  const carreraCell = document.createElement("td");
  carreraCell.textContent = usuario.carrera;

  const lugarCell = document.createElement("td");
  lugarCell.textContent = usuario.restaurant;

  const invitacionCell = document.createElement("td");
  invitacionCell.textContent = `¿Almorzamos juntos el próximo ${diaMasCercano}?`;
  invitacionCell.style.fontWeight = "bold";
  invitacionCell.style.color = "#007BFF";
  invitacionCell.style.textAlign = "center";

  const accionCell = document.createElement("td");
  const aceptarButton = document.createElement("button");
  aceptarButton.textContent = "Aceptar";
  aceptarButton.classList.add("btn", "btn-success", "mr-2");

  const declinarButton = document.createElement("button");
  declinarButton.textContent = "Declinar";
  declinarButton.classList.add("btn", "btn-danger");
  let citasProximas = [];
  aceptarButton.addEventListener("click", () => {
    // Agregar el usuario a las citas próximas
    const cita = {
      nombre: `${usuario.firstName} ${usuario.lastName}`,
      lugar: usuario.restaurant,
      fecha: diaMasCercano, // Puedes modificar para incluir la hora si es necesario
    };
    citasProximas.push(cita);
    // Enviar las citas al servidor
    enviarCitasAlServidor(citasProximas);
    console.log("Arreglo de citas proximas: ", citasProximas);
    // Eliminar la fila con animación
    colaSolicitudesGlobal = colaSolicitudesGlobal.filter(
      (u) =>
        !(u.firstName === usuario.firstName && u.lastName === usuario.lastName)
    );
    eliminarFilaConAnimacion(row);
    // Eliminar la solicitud de la cola
    // Llamar a la función para verificar el estado de la tabla
    finalizarTabla();
  });

  // Lógica para declinar la solicitud
  declinarButton.addEventListener("click", () => {
    alert(`Solicitud declinada para ${usuario.firstName} ${usuario.lastName}`);
    // Eliminar la fila con animación
    // Filtrar el usuario declinado de la cola
    colaSolicitudesGlobal = colaSolicitudesGlobal.filter(
      (u) =>
        !(u.firstName === usuario.firstName && u.lastName === usuario.lastName)
    );
    eliminarFilaConAnimacion(row);
    finalizarTabla();
  });

  accionCell.appendChild(aceptarButton);
  accionCell.appendChild(declinarButton);

  row.appendChild(nombreCell);
  row.appendChild(carreraCell);
  row.appendChild(lugarCell);
  row.appendChild(invitacionCell);
  row.appendChild(accionCell);

  return row;
}

// Función para eliminar la fila con una animación de desvanecimiento
function eliminarFilaConAnimacion(fila) {
  fila.classList.add("fade-out"); // Añadir la clase de desvanecimiento
  setTimeout(() => {
    fila.remove(); // Eliminar la fila después de la animación
  }, 500); // Coincide con la duración de la animación
}

// Función para enviar las citas al servidor
function enviarCitasAlServidor(citas) {
  fetch("http://localhost:3000/api/citas", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(citas),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al enviar citas al servidor");
      }
      console.log("Citas enviadas con éxito:", citas);
    })
    .catch((error) => console.error("Error al enviar las citas:", error));
}

// Función para cargar las solicitudes desde el backend y llenar la tabla
function cargarSolicitudes() {
  if (solicitudesCargadas) {
    console.log("Las solicitudes ya han sido cargadas y no quedan usuarios.");
    return; // Si ya se cargaron, no hacemos nada
  }
  // Primero, obtenemos el usuario actual
  fetch("http://localhost:3000/api/usuarioActual")
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al obtener el usuario actual");
      }
      return response.json();
    })
    .then((usuarioActual) => {
      const usuarioActualId = usuarioActual.id; // Asegúrate de tener el ID del usuario actual
      console.log("Usuario actual:", usuarioActual);
      // Ahora hacemos el fetch para las solicitudes
      return fetch(
        `http://localhost:3000/api/solicitudes?usuarioId=${usuarioActualId}`
      );
    })
    .then((response) => response.json())
    .then((colaSolicitudes) => {
      console.log("Solicitudes recibidas:", colaSolicitudes);
      colaSolicitudesGlobal = colaSolicitudes; // Almacena la cola globalmente
      const tablaSolicitudesBody = document.querySelector(
        "#tablaSolicitudes tbody"
      );
      tablaSolicitudesBody.innerHTML = ""; // Limpiar la tabla

      colaSolicitudes.forEach((usuarioData) => {
        const [firstName, lastName, carrera, restaurant, diaMasCercano] =
          usuarioData; // Desestructurando el arreglo
        const fila = createSolicitudRow(
          { firstName, lastName, carrera, restaurant },
          diaMasCercano
        );
        tablaSolicitudesBody.appendChild(fila);
      });
    })
    .catch((error) => console.error("Error cargando las solicitudes:", error));
}

// Nueva función para verificar el estado de la tabla
function finalizarTabla() {
  const tablaSolicitudesBody = document.querySelector(
    "#tablaSolicitudes tbody"
  );
  if (tablaSolicitudesBody.rows.length === 1) {
    solicitudesCargadas = true; // Marcar que no hay más solicitudes
    console.log("No hay más solicitudes para mostrar.");
    enviarEstadoSolicitudesCargadas(solicitudesCargadas);
  } else {
    solicitudesCargadas = false;
  }
  console.log("cantidad de solicitudes ", tablaSolicitudesBody.rows.length);
  console.log(    "valor de la variable solicitudesCargadas: ", solicitudesCargadas);
}

// Función para enviar el estado de solicitudesCargadas al servidor
function enviarEstadoSolicitudesCargadas(estado) {
  fetch("http://localhost:3000/api/solicitudesCargadas", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ estado }), // Enviar el estado en el cuerpo de la petición
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al actualizar el estado de solicitudesCargadas");
      }
      console.log(
        "Estado de solicitudesCargadas enviado correctamente:",
        estado
      );
    })
    .catch((error) =>
      console.error("Error al enviar el estado de solicitudesCargadas:", error)
    );
}



document.addEventListener("DOMContentLoaded", () => {
  // Pedir el estado de solicitudesCargadas al servidor
  fetch("http://localhost:3000/api/solicitudesCargadas")
    .then((response) => response.json())
    .then((data) => {
      if (!data.solicitudesCargadas) {
        cargarSolicitudes();
        //enviarEstadoSolicitudesCargadas(true); // Cambiar el estado a true
      } else {
        console.log(
          "Las solicitudes ya han sido cargadas, no se cargan de nuevo."
        );
      }
    })
    .catch((error) =>
      console.error("Error al obtener el estado de solicitudesCargadas:", error)
    );
});
