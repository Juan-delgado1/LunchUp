
// Función para cargar las citas desde el servidor
function cargarCitas() {
    fetch('http://localhost:3000/api/citas')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener las citas');
            }
            return response.json();
        })
        .then(citas => {
            console.log('Citas recibidas:', citas);
            mostrarCitasEnCalendario(citas);
        })
        .catch(error => console.error('Error cargando las citas:', error));
}

// Función para mostrar citas en el calendario
function mostrarCitasEnCalendario(citas) {
    const calendarioElement = document.querySelector('#calendario'); // Suponiendo que tienes un contenedor para el calendario
    calendarioElement.innerHTML = ''; // Limpiar el calendario antes de mostrar nuevas citas

    citas.forEach(cita => {
        const citaCard = document.createElement('div');
        citaCard.classList.add('cita-card');
        citaCard.innerHTML = `
            <h2>${cita.nombre}</h2>
            <h4>Lugar: ${cita.lugar}</h4>
            <h4>Fecha: ${cita.fecha}</h4>
        `;
        calendarioElement.appendChild(citaCard);
    });
}

// Llamar a cargarCitas al cargar la página de citas
document.addEventListener('DOMContentLoaded', () => {
    if (window.location.pathname === '/citas.html') { // Cambia esto al nombre de tu ruta de citas
        cargarCitas();
    }
});
