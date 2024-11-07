const db = require('../DB-Config');

module.exports = {
  findAll: () => db.query('SELECT id_usuario, nombre, email FROM usuarios'),
  findById: (id_usuario) => db.query('SELECT id_usuario, name, email FROM usuarios WHERE id_usuario = $1', [id_usuario]),
  update: (id_usuario, nombre, email) => 
    db.query('UPDATE usuarios SET nombre = $1, email = $2 WHERE id_usuario = $3 RETURNING id_usuario, nombre, email', 
    [nombre, email, id_usuario]),
  delete: (id_usuario) => db.query('DELETE FROM usuarios WHERE id_usuario = $1', [id_usuario]),
};