const db = require('../DB-Config');
const bcrypt = require('bcrypt');

module.exports = {
  create: async (nombre, email, contraseña, rol) => {
    const hashedPassword = await bcrypt.hash(contraseña, 10);
    return db.query(
      'INSERT INTO usuarios (nombre, email, contraseña, rol) VALUES ($1, $2, $3, $4) RETURNING id_usuario, nombre, email, rol',
      [nombre, email, hashedPassword, rol]
    );
  },
  findByEmail: (email) => 
    db.query('SELECT * FROM usuarios WHERE email = $1', [email]),
};
