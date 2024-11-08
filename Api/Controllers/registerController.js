const Register = require('../Models/registerModel');

exports.register = async (req, res) => {
  const { nombre, email, contraseña, rol } = req.body;
  console.log('Se está registrando', { nombre, email, rol });

  try {
    const userExists = await Register.findByEmail(email);
    if (userExists.rows.length > 0) {
      return res.status(400).json({ success: false, message: 'El usuario ya existe' });
    }

    const result = await Register.create(nombre, email, contraseña, rol);
    const user = result.rows[0];

    res.status(201).json({
      success: true,
      message: "Usuario registrado con éxito",
      usuario: {
        id: user.id_usuario,
        nombre: user.nombre,
        email: user.email,
        rol: user.rol
      }
    });
  } catch (err) {
    console.error('Error en el registro:', err);
    res.status(500).json({ success: false, message: 'Error en el registro', error: err.message });
  }
};