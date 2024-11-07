const Login = require('../Models/loginModel');
const bcrypt = require('bcrypt');

exports.login = async (req, res) => {
  const { email, contraseña } = req.body;

  console.log('Intento de inicio de sesión:', { email, contraseña });

  try {
    const result = await Login.findByEmail(email);
    if (result.rows.length === 0) {
      console.log('Inicio de sesión fallido: Usuario no encontrado');
      return res.status(401).json({ message: 'Credenciales inválidas' });
    }

    const user = result.rows[0];
    const isPasswordValid = await bcrypt.compare(contraseña, user.contraseña);

    if (!isPasswordValid) {
      console.log('Inicio de sesión fallido: Contraseña incorrecta');
      return res.status(401).json({ message: 'Credenciales inválidas' });
    }

    console.log('Inicio de sesión exitoso:', { email });

    res.json({ success: true, message: "Inicio de sesión exitoso" });
  } catch (err) {
    console.error('Error en el inicio de sesión:', err);
    res.status(500).json({ message: 'Error en el inicio de sesión', error: err.message });
  }
};