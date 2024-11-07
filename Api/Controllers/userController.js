const User = require('../Models/userModel');

exports.getAllUsers = async (req, res) => {
  try {
    const result = await User.findAll();
    res.json(result.rows);
  } catch (err) {
    res.status(500).json({ message: 'Error al obtener usuarios', error: err.message });
  }
};

exports.getUser = async (req, res) => {
  try {
    const result = await User.findById(req.params.id_usuario
    );
    if (result.rows.length > 0) {
      res.json(result.rows[0]);
    } else {
      res.status(404).json({ message: 'Usuario no encontrado' });
    }
  } catch (err) {
    res.status(500).json({ message: 'Error al obtener usuario', error: err.message });
  }
};

exports.updateUser = async (req, res) => {
  const { nombre, email } = req.body;
  try {
    const result = await User.update(req.params.id_usuario, nombre, email);
    if (result.rows.length > 0) {
      res.json(result.rows[0]);
    } else {
      res.status(404).json({ message: 'Usuario no encontrado' });
    }
  } catch (err) {
    res.status(400).json({ message: 'Error al actualizar usuario', error: err.message });
  }
};

exports.deleteUser = async (req, res) => {
  try {
    const result = await User.delete(req.params.id_usuario);
    if (result.rowCount > 0) {
      res.json({ message: 'Usuario eliminado' });
    } else {
      res.status(404).json({ message: 'Usuario no encontrado' });
    }
  } catch (err) {
    res.status(500).json({ message: 'Error al eliminar usuario', error: err.message });
  }
};