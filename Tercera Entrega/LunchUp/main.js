// Seleccionar todas las tarjetas dinámicamente una vez que se carguen
let cards = [];
let activeCardIndex = 0; // Índice de la tarjeta activa
// Crear una variable para almacenar el número de solicitudes pendientes
let usuarioActual = null;
let usuarioActualId; // ID del usuario actual

async function obtenerUsuarioActual() {
    try {
        const response = await fetch('/api/usuarioActual');
        const data = await response.json();
        usuarioActual = data; // Asignar los datos del usuario actual
        usuarioActualId=usuarioActual.id ; // calcular el id del usuario actual
        console.log('Usuario actual:', usuarioActual);
    } catch (error) {
        console.error('Error al obtener el usuario actual:', error);
    }
}

// Función para iniciar el "match" con otro usuario, esperando hasta que usuarioActual esté listo
async function iniciarMatch(user) {
    await obtenerUsuarioActual();  // Asegúrate de que usuarioActual esté disponible
    matchUser(user);  // Llama a la función de match ahora que usuarioActual está listo
}


// Función para actualizar la visibilidad de las tarjetas
function updateCardVisibility() {
    cards.forEach((card, index) => {
        card.style.display = index === activeCardIndex ? 'block' : 'none';
    });
}

// Función para deslizar la tarjeta a la izquierda o derecha
function swipeCard(direction) {
    if (activeCardIndex < cards.length) {
        const card = cards[activeCardIndex];
        card.style.transform = `translateX(${direction * 1000}px) rotate(${direction * 20}deg)`; // Desliza la tarjeta
        card.style.transition = 'transform 0.5s ease';

        // Después de deslizar, pasar a la siguiente tarjeta
        setTimeout(() => {
            activeCardIndex++;
            if (activeCardIndex < cards.length) {
                updateCardVisibility(); // Actualizar la visibilidad de las tarjetas
            } else {
                // Mostrar advertencia cuando no haya más tarjetas
                mostrarAdvertenciaNoMasUsuarios();
            }
        }, 500);
    } else {
        // Mostrar advertencia cuando no haya más tarjetas (aunque no haya swipe)
        mostrarAdvertenciaNoMasUsuarios();
    }
}

// Función para mostrar advertencia de que no hay más usuarios
function mostrarAdvertenciaNoMasUsuarios() {
    const cardContainer = document.querySelector('.card-container');
    cardContainer.innerHTML = ''; // Limpiar cualquier tarjeta existente

    const advertencia = document.createElement('div');
    advertencia.classList.add('advertencia');
    advertencia.style.textAlign = 'center'; // Centrar el contenido
    advertencia.style.padding = '20px';

    const mensaje = document.createElement('h2');
    mensaje.textContent = 'No hay más usuarios compatibles';
    mensaje.style.color = '#FF6347'; // Color del texto (puedes cambiarlo a tu gusto)

    const caritaTriste = document.createElement('div');
    caritaTriste.style.fontSize = '100px'; // Tamaño grande para la carita
    caritaTriste.textContent = '😔'; // Carita triste

    // Añadir mensaje y carita al contenedor de advertencia
    advertencia.appendChild(mensaje);
    advertencia.appendChild(caritaTriste);

    // Añadir advertencia al contenedor de tarjetas
    cardContainer.appendChild(advertencia);
}



// Función para gestionar el botón de "No"
const dislikeButton = document.getElementById('dislikeButton');
dislikeButton?.addEventListener('click', () => {
    swipeCard(-1); // Desliza a la izquierda
});

// Función para gestionar el botón de "Sí"
const likeButton = document.getElementById('likeButton');
likeButton?.addEventListener('click', async () => {
    const userData = cards[activeCardIndex]?.dataset.user;
    if (userData) {
        const user = JSON.parse(userData);
        if (user && user.matching) {
            await iniciarMatch(user);  // Asegúrate de que usuarioActual esté disponible antes de hacer match
        } else {
            console.error('Usuario no Quiere Matchs contigo');
        }
        swipeCard(1);  // Desliza a la derecha
    } else {
        console.error('No se encontró la tarjeta activa o los datos del usuario');
    }
});


//CAMBIOSSS ____________-
// Simulamos una cola FIFO para el usuarioActual, implementada en arreglo
let colaSolicitudes = [];
let usersol = [];


function matchUser(user) {
    if (!usuarioActual) {
        console.error('El usuario actual no está disponible aún.');
        return;  // Evitar continuar si no se ha definido usuarioActual
    }
    if (user.matching === 1) {
        numeroSolicitudes++;
        actualizarNumeroSolicitudes();
        const diasComunes = obtenerDiasDisponiblesComunes(user);
        let diaMasCercano; // Declarar la variable aquí para que esté disponible más adelante
        if (diasComunes.length > 0) {
            diaMasCercano = encontrarDiaMasCercano(diasComunes);
        } else {
            console.log('No hay dias comunes'); // Manejar el caso si no hay días comunes
        }
        // Agregar información al arreglo usersol
        usersol.push(user.firstName, user.lastName, user.carrera,user.restaurant, diaMasCercano);
        console.log("Información usuario solicitud:");
        console.log(usersol);
        
        // Añadir el usuario a la cola de solicitudes del usuario actual
        colaSolicitudes.push(usersol);
        usersol = []; // Limpiar el arreglo usersol para la siguiente solicitud
        console.log("Cola de Solicitudes:");
        console.log(colaSolicitudes);
        console.log("Usuario actual ID:" + usuarioActualId);
        enviarColaSolicitudes(usuarioActualId, colaSolicitudes);
    }
}


// Supongamos que tienes la cola de solicitudes generada en main.js
function enviarColaSolicitudes(usuarioActualId, colaSolicitudes) {
    fetch('http://localhost:3000/api/solicitudes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ usuarioActualId, colaSolicitudes }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al enviar la cola de solicitudes');
        }
        return response.json();
    })
    .then(data => {
        console.log(data.message);
    })
    .catch(error => console.error('Error:', error));
}




//FIN CAMBIOS_____________
function obtenerDiasDisponiblesComunes(user) {
    const diasUsuarioActual = obtenerDiasDisponibles(usuarioActual);  // Días del usuario actual
    const diasOtroUsuario = obtenerDiasDisponibles(user);  // Días del otro usuario
    return diasUsuarioActual.filter(dia => diasOtroUsuario.includes(dia));  // Días comunes
}

function encontrarDiaMasCercano(diasComunes) {
    const diasOrdenados = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes'];
    const hoy = new Date().getDay();  // Día de la semana actual (0 = Domingo, 1 = Lunes, ..., 6 = Sábado)
    let diaMasCercano = null;

    // Encuentra el día más cercano
    for (let i = 0; i < diasOrdenados.length; i++) {
        const indexDia = (hoy + i) % 7;
        const dia = diasOrdenados[indexDia - 1];  // Ajustar al índice del array (0 = Domingo)
        if (diasComunes.includes(dia)) {
            diaMasCercano = dia;
            break;
        }
    }
    return diaMasCercano;
}

// Función para mostrar el número de solicitudes en el menú lateral
function actualizarNumeroSolicitudes() {
    const solicitudesMenuItem = document.querySelector('a[href="/solicitudes"]');
    let contador = solicitudesMenuItem.querySelector('.badge');

    // Si no existe el contador, crear uno
    if (!contador) {
        contador = document.createElement('span');
        contador.classList.add('badge', 'badge-danger');
        contador.style.marginLeft = '10px';
        solicitudesMenuItem.appendChild(contador);
    }

    // Actualizar el contador con el número de solicitudes
    if (numeroSolicitudes > 0) {
        contador.textContent = numeroSolicitudes;
        contador.style.display = 'inline-block';
    } else if (numeroSolicitudes === 0) {
        contador.style.display = 'none';
    }
}


// Función para voltear la tarjeta hacia adelante o atrás
function addFlipFunctionality() {
    cards.forEach((card) => {
        const moreButton = card.querySelector('.moreButton'); // Selecciona el botón "más" de cada tarjeta
        const backButton = card.querySelector('.backButton'); // Selecciona el botón "volver" de cada tarjeta (en la parte trasera)

        if (moreButton) {
            // Voltea la tarjeta hacia adelante al hacer clic en "más"
            moreButton.addEventListener('click', () => {
                card.classList.add('flipped'); // Agrega la clase 'flipped' para voltear la tarjeta
            });
        }

        if (backButton) {
            // Voltea la tarjeta hacia atrás al hacer clic en "volver"
            backButton.addEventListener('click', () => {
                card.classList.remove('flipped'); // Elimina la clase 'flipped' para regresar la tarjeta a la vista frontal
            });
        }
    });
}


// Función para crear dinámicamente las tarjetas de usuario
function createCard(user) {
    const card = document.createElement('div');
    card.classList.add('card');
    card.dataset.user = JSON.stringify(user);

    const cardInner = document.createElement('div');
    cardInner.classList.add('card-inner');

    // Parte frontal de la tarjeta
    const cardFront = document.createElement('div');
    cardFront.classList.add('card-front');

    const img = document.createElement('img');
    img.src = `images/${user.username}.jpg`; // Imagen del usuario
    img.alt = user.username;

    const cardBodyFront = document.createElement('div');
    cardBodyFront.classList.add('card-body');

    const cardTitle = document.createElement('h5');
    cardTitle.classList.add('card-title');
    cardTitle.textContent = `${user.firstName} ${user.lastName}`;

    const cardText = document.createElement('p');
    cardText.classList.add('card-text');
    cardText.textContent = `Estudiante de ${user.carrera}`;

    const moreButton = document.createElement('button');
    moreButton.classList.add('moreButton', 'btn', 'btn-primary');
    moreButton.textContent = 'Más';

    // Añadir elementos al cuerpo de la tarjeta frontal
    cardBodyFront.appendChild(cardTitle);
    cardBodyFront.appendChild(cardText);
    cardBodyFront.appendChild(moreButton);
    cardFront.appendChild(img);
    cardFront.appendChild(cardBodyFront);

    // Parte trasera de la tarjeta
    const cardBack = document.createElement('div');
    cardBack.classList.add('card-back');

    const cardBodyBack = document.createElement('div');
    cardBodyBack.classList.add('card-body');

    const backTitle = document.createElement('h5');
    backTitle.classList.add('card-title');
    backTitle.textContent = 'Restaurantes favoritos';

    const restaurantList = document.createElement('ul');
    const restaurantItem = document.createElement('li');
    restaurantItem.textContent = user.restaurant; // Restaurante favorito
    restaurantList.appendChild(restaurantItem);

    const lunchDays = document.createElement('h5');
    lunchDays.textContent = 'Días de almuerzo';

    const daysText = document.createElement('p');
    daysText.textContent = obtenerDiasDisponibles(user);

    const backButton = document.createElement('button');
    backButton.classList.add('backButton', 'btn', 'btn-secondary');
    backButton.textContent = 'Volver';

    // Añadir elementos al cuerpo de la tarjeta trasera
    cardBodyBack.appendChild(backTitle);
    cardBodyBack.appendChild(restaurantList);
    cardBodyBack.appendChild(lunchDays);
    cardBodyBack.appendChild(daysText);
    cardBodyBack.appendChild(backButton);
    cardBack.appendChild(cardBodyBack);

    // Añadir ambas partes (frontal y trasera) a la tarjeta interna
    cardInner.appendChild(cardFront);
    cardInner.appendChild(cardBack);
    card.appendChild(cardInner);

    return card;
}

function obtenerDiasDisponibles(usuario) {
    if (!usuario) {
        console.error('El objeto usuario es null o undefined');
        return []; // Devolver un array vacío en lugar de un string
    }

    const dias = [];
    if (usuario.lunes === 1) dias.push('Lunes');
    if (usuario.martes === 1) dias.push('Martes');
    if (usuario.miercoles === 1) dias.push('Miércoles');
    if (usuario.jueves === 1) dias.push('Jueves');
    if (usuario.viernes === 1) dias.push('Viernes');

    return dias;  // Devolver el array de días en lugar de un string
}



// Función para cargar los usuarios desde el servidor y generar las tarjetas
function cargarUsuarios() {
    fetch('http://localhost:3000/api/perfiles')  // Ajusta la ruta aquí
        .then(response => response.json())
        .then(usuarios => {
            console.log('Usuarios recibidos:', usuarios); // Añade este log para verificar los datos

            const cardContainer = document.querySelector('.card-container');
            cardContainer.innerHTML = ''; // Limpiar el contenedor de las tarjetas

            // Reiniciar el número de solicitudes
            numeroSolicitudes = 0;

            // Crear las tarjetas dinámicamente
            usuarios.forEach(usuario => {
                const card = createCard(usuario);
                cardContainer.appendChild(card); // Añadir la tarjeta al contenedor

                // Verificar si el usuario tiene una solicitud pendiente
                if (usuario.tieneSolicitud) {
                    numeroSolicitudes++;
                }
            });

            // Actualizar el número de solicitudes en el menú
            actualizarNumeroSolicitudes();

            // Actualizar la lista de tarjetas y añadir funcionalidad
            cards = document.querySelectorAll('.card');
            updateCardVisibility();
            addFlipFunctionality();
        })
        .catch(error => console.error('Error fetching usuarios:', error));
}
// Función para enviar el estado de solicitudesCargadas=false al servidor
function reiniciarSolicitudesCargadas() {
    fetch('http://localhost:3000/api/solicitudesCargadas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ estado: false }), // Enviar solicitudesCargadas=false
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al reiniciar solicitudesCargadas');
        }
        console.log('SolicitudesCargadas reiniciado a false');
    })
    .catch(error => console.error('Error al reiniciar solicitudesCargadas:', error));
}

// Función para reiniciar citas en el servidor
function reiniciarCitas() {
    return fetch("http://localhost:3000/api/reiniciarCitas", {
      method: "DELETE", // Puedes usar POST si lo prefieres
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error al reiniciar las citas");
        }
        console.log("Citas reiniciadas con éxito");
      })
      .catch((error) => console.error("Error al reiniciar las citas:", error));
}

// Llamar a la función cuando se cargue la página
document.addEventListener("DOMContentLoaded", () => {
    cargarUsuarios();
    reiniciarSolicitudesCargadas();
    reiniciarCitas();
});


// Redirigir a solicitudes.html al hacer clic en el enlace de solicitudes
const enlaceSolicitudes = document.querySelector('a[href="/solicitudes"]');
enlaceSolicitudes.addEventListener('click', (event) => {
    event.preventDefault();
    fetch('http://localhost:3000/api/solicitudes') // Verifica que esta ruta sea correcta
        .then(response => {
            if (response.ok) {
                window.location.href = '/solicitudes.html';
            } else {
                console.error('Error al cargar solicitudes:', response.statusText);
            }
        })
        .catch(error => console.error('Error de red:', error));
});

// Redirigir a citas.html al hacer clic en el enlace de solicitudes
const enlaceCitas = document.querySelector('a[href="/citas"]');
enlaceCitas.addEventListener('click', (event) => {
    event.preventDefault();
    fetch('http://localhost:3000/api/citas') // Verifica que esta ruta sea correcta
        .then(response => {
            if (response.ok) {
                window.location.href = '/citas.html';
            } else {
                console.error('Error al cargar solicitudes:', response.statusText);
            }
        })
        .catch(error => console.error('Error de red:', error));
});

// Selecciona el botón de menú y el sidebar
const menuToggle = document.getElementById('menuToggle');
const sidebar = document.getElementById('sidebar');

// Añade un event listener para el botón de menú
menuToggle?.addEventListener('click', () => {
    // Alterna la clase 'expanded' para expandir/colapsar el sidebar
    sidebar.classList.toggle('expanded');
});
