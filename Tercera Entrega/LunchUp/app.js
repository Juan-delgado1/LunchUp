const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql');
const cors = require('cors');
const multer = require('multer');
const path = require('path');
const session = require('express-session'); // ✔️ Añadido para manejar sesiones

const app = express();

// Configuración de Multer para subir imágenes
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'images/')
    },
    filename: function (req, file, cb) {
        cb(null, file.originalname) // Mantener el nombre original del archivo
    }
});

const upload = multer({ storage: storage }).fields([{ name: 'profilePicture', maxCount: 1 }]);

app.use(express.static(__dirname));
app.use(cors());

// Configuración de la sesión ✔️
app.use(session({
    secret: 'mi_secreto_super_seguro', // Cambia esto por una clave segura
    resave: false,
    saveUninitialized: true,
    cookie: { secure: false } // `secure: true` si usas HTTPS
}));

// Conexión a la base de datos
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'usuarios'
});

db.connect((err) => {
    if (err) throw err;
    console.log('Connected to database');
});

// Configuración de bodyParser para manejar solicitudes POST
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

const ComedorYutakeuchi = new Set();
const ComedorCentral = new Set();
const ComedorBiologia = new Set();

let hashMap = {};

// Ruta para el registro (signin)
app.post('/submit-form', upload, (req, res) => {
    var firstName = req.body.firstName;
    var lastName = req.body.lastName;
    var dob = req.body.dob;
    var university = req.body.university;
    var restaurant = req.body.restaurant;
    var lunes = req.body.lunes !== undefined ? '1' : '0';
    var martes = req.body.martes ? '1' : '0';
    var miercoles = req.body.miercoles ? '1' : '0';
    var jueves = req.body.jueves ? '1' : '0';
    var viernes = req.body.viernes ? '1' : '0';
    var username = req.body.username;
    var password = req.body.password;
    var matching = Math.round(Math.random());
    var carrera = req.body.carrera;

    var query = 'INSERT INTO usuarios (firstName, lastName, dob, university, restaurant, lunes, martes, miercoles, jueves, viernes, username, password, matching, carrera) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)';
    var values = [firstName, lastName, dob, university, restaurant, lunes, martes, miercoles, jueves, viernes, username, password, matching, carrera];

    db.query(query, values, (err, result) => {
        if (err) {
            console.log(err);
            res.send('Error registering user.');
        } else {
            res.redirect('main.html');
            db.query('SELECT * FROM usuarios', (err, usuarios) => {
                if (err) throw err;
                usuarios.forEach(usuario => {
                    switch (usuario.restaurant) {
                        case 'Yutakeuchi':
                            ComedorYutakeuchi.add(usuario);
                            break;
                        case 'Central':
                            ComedorCentral.add(usuario);
                            break;
                        case 'Biologia':
                            ComedorBiologia.add(usuario);
                            break;
                    }
                });

                const ultimoUsuario = usuarios[usuarios.length - 1];
                hashMap = crearHashMap(ultimoUsuario, usuarios); // Actualiza el hashMap global
            });
        }
    });
});

// ✔️ Nueva ruta para manejar el login, con gestión de sesiones
app.post('/login', (req, res) => {
    const username = req.body.username;
    const password = req.body.password;

    // Verificar si el usuario existe en la base de datos
    const query = 'SELECT * FROM usuarios WHERE username = ? AND password = ?';
    db.query(query, [username, password], (err, results) => {
        if (err) {
            console.log(err);
            return res.status(500).json({ success: false, message: 'Error al verificar el usuario.' });
        }

        if (results.length > 0) {
            const usuario = results[0];

            // Guardar el usuario en la sesión ✔️
            req.session.user = usuario;

            db.query('SELECT * FROM usuarios', (err, usuarios) => {
                if (err) {
                    return res.status(500).json({ success: false, message: 'Error al obtener los usuarios.' });
                }

                const userHashMap = crearHashMap(usuario, usuarios);
                hashMap = userHashMap; // Actualizar hashMap global

                res.redirect('main.html'); // Redirigir al usuario a la página principal
            });
        } else {
            // Si no se encuentra el usuario
            res.status(401).json({ success: false, message: 'Usuario o contraseña incorrectos.' });
        }
    });
});

// ✔️ Modificación de la ruta `/api/perfiles` para que use el usuario logueado
app.get('/api/perfiles', (req, res) => {
    // Verificar si hay un usuario logueado en la sesión
    if (!req.session.user) {
        return res.status(401).json({ error: 'No hay usuario logueado.' });
    }

    // Obtener el usuario logueado de la sesión
    const usuarioLogueado = req.session.user;

    db.query('SELECT * FROM usuarios', (err, usuarios) => {
        if (err) {
            return res.status(500).json({ error: 'Error al obtener los usuarios.' });
        }

        // Crear el hashMap para el usuario logueado
        const userHashMap = crearHashMap(usuarioLogueado, usuarios);

        let arrayHash = Array.from(Object.values(userHashMap));
        res.json(arrayHash); // Enviar el hashMap como respuesta JSON
    });
});

// ✔️ Modificación de la ruta `/api/usuarioActual` para usar el usuario logueado
app.get('/api/usuarioActual', (req, res) => {
    // Verificar si hay un usuario logueado en la sesión
    if (!req.session.user) {
        return res.status(401).json({ error: 'No hay usuario logueado.' });
    }

    // Devolver los datos del usuario logueado
    res.json(req.session.user);
});

// Función para crear el hashMap
function crearHashMap(ultimoUsuario, usuarios) {
    const hashMap = {};

    usuarios.forEach(usuario => {
        if (usuario.username !== ultimoUsuario.username && usuario.restaurant === ultimoUsuario.restaurant) {
            var coincideEnDias = false;
            if (usuario.lunes === 1 && ultimoUsuario.lunes === 1) { coincideEnDias = true }
            if (usuario.martes === 1 && ultimoUsuario.martes === 1) { coincideEnDias = true }
            if (usuario.miercoles === 1 && ultimoUsuario.miercoles === 1) { coincideEnDias = true }
            if (usuario.jueves === 1 && ultimoUsuario.jueves === 1) { coincideEnDias = true }
            if (usuario.viernes === 1 && ultimoUsuario.viernes === 1) { coincideEnDias = true }

            if (coincideEnDias) {
                hashMap[usuario.username] = usuario;
            }
        }
    });
    return hashMap;
}

//recibir cola de solicitudes________CAMBIO

app.use(express.json());
let colasSolicitudesPorUsuario = {};

// Endpoint para recibir la cola de solicitudes de un usuario
app.post('/api/solicitudes', (req, res) => {
    const { usuarioActualId, colaSolicitudes } = req.body;
    // Guardar la cola de solicitudes en colasSolicitudesPorUsuario
    colasSolicitudesPorUsuario[usuarioActualId] = colaSolicitudes;
    res.status(200).json({ message: 'Cola de solicitudes guardada correctamente' });
});

// Endpoint para obtener las solicitudes (FIFO) del usuario actual
app.get('/api/solicitudes', (req, res) => {
    const usuarioActualId = req.query.usuarioId;
    // Obtener la cola de solicitudes del usuario actual
    const colaSolicitudes = colasSolicitudesPorUsuario[usuarioActualId] || [];
    res.status(200).json(colaSolicitudes);
});

//Final del cambio_______-
//pagina de citas INICIO

// Variable para almacenar citas próximas
let citasProximas = [];

// Ruta para obtener las citas
app.get('/api/citas', (req, res) => {
    res.json(citasProximas);
});

// Ruta para recibir las citas desde solicitudes.js
app.post('/api/citas', (req, res) => {
    const nuevasCitas = req.body;
    // Agregar nuevas citas a la lista
    citasProximas = [...citasProximas, ...nuevasCitas];
    console.log('Citas recibidas:', nuevasCitas);
    res.status(201).send('Citas añadidas correctamente.');
});


// Ruta para reiniciar (borrar) las citas próximas
app.delete('/api/reiniciarCitas', (req, res) => {
    // Vaciar el array citasProximas
    citasProximas = [];
    console.log('Citas reiniciadas.');
    res.status(200).send('Citas reiniciadas correctamente.');
});
//pagina de citas FINAL

// Variable para almacenar el estado de solicitudesCargadas
let solicitudesCargadas = false; // Por defecto es false

// Ruta para obtener el estado de solicitudesCargadas
app.get('/api/solicitudesCargadas', (req, res) => {
    res.json({ solicitudesCargadas });
    console.log('Estado de solicitudesCargadas', solicitudesCargadas);
});

// Ruta para recibir el nuevo valor de solicitudesCargadas
app.post('/api/solicitudesCargadas', (req, res) => {
    const { estado } = req.body;
    solicitudesCargadas = estado; // Actualiza el valor de solicitudesCargadas
    console.log('Estado de solicitudesCargadas actualizado a:', solicitudesCargadas);
    res.status(200).send('Estado actualizado correctamente.');
});


// Inicializar el servidor en el puerto 3000
const port = 3000;
app.listen(port, () => {
    console.log('Server is running on port 3000');
});
