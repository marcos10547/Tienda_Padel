const db = require('../DB-Config');

module.exports = {
  findAll: () => db.query('SELECT id_usuario, nombre, email FROM usuarios'),
  findById: (id) => db.query('SELECT id_usuario, name, email FROM usuarios WHERE id_usuario = $1', [id]),
  update: (id, nombre, email) => 
    db.query('UPDATE usuarios SET nombre = $1, email = $2 WHERE id_usuario = $3 RETURNING id_usuario, nombre, email', 
    [name, email, id]),
  delete: (id) => db.query('DELETE FROM usuarios WHERE id_usuario = $1', [id]),
};