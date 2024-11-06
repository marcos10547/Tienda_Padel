const express = require("express");
const cors = require('cors');
const bodyParser = require('body-parser');
const dotenv = require('dotenv');

dotenv.config();

const app = express();

// Middleware
app.use(cors());
app.use(bodyParser.json());

// Importar rutas
const userRoutes = require('./Routes/userRoutes');
const loginRoutes = require('./routes/loginRoutes');
const registerRoutes = require('./Routes/registerRoutes');

// Usar rutas
app.use('/api/users', userRoutes);
app.use('/api/login', loginRoutes);
app.use('/api/register', registerRoutes);

// Ruta de prueba
app.get('/', (req, res) => {
    res.send('¡El servidor esta funcionando!');
});

// Manejo de errores
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).send('Algo salió mal!');
});

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
    console.log(`Servidor corriendo en http://localhost:${PORT}`);
});

module.exports = app

//ENDPOINTS
//http://localhost:3000/api/register