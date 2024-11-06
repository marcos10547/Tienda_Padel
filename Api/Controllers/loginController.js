const Login = require('../Models/loginModel');
const bcrypt = require('bcrypt');

exports.login = async (req, res) => {
  const { email, contraseña } = req.body;

  try {
    const result = await Login.findByEmail(email);
    if (result.rows.length === 0) {
      return res.status(401).json({ message: 'Credenciales inválidas' });
    }

    const user = result.rows[0];
    const isPasswordValid = await bcrypt.compare(contraseña, user.contraseña);

    if (!isPasswordValid) {
      return res.status(401).json({ message: 'Credenciales inválidas' });
    }

    //res.json({ user: { id_usuario: user.id_usuario, nombre: user.nombre, email: user.email } });
    res.json({ success: true, message: "Inicio de sesión exitoso"});
  } catch (err) {
    res.status(500).json({ message: 'Error en el inicio de sesión', error: err.message });
  }
};
