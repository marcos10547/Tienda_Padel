const Register = require('../Models/registerModel');

exports.register = async (req, res) => {
  const { nombre, email, contraseña , rol } = req.body;

  try {
    const userExists = await Register.findByEmail(email);
    if (userExists.rows.length > 0) {
      return res.status(400).json({ message: 'El usuario ya existe' });
    }

    const result = await Register.create(nombre, email, contraseña, rol);
    const user = result.rows[0];

    res.status(201).json({ user });
  } catch (err) {
    res.status(500).json({ message: 'Error en el registro', error: err.message });
  }
};
