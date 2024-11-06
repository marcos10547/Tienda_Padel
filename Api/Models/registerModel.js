const db = require('../DB-Config');
const bcrypt = require('bcrypt');

module.exports = {
  create: async (nombre, email, contraseña) => {
    const hashedPassword = await bcrypt.hash(contraseña, 10);
    return db.query(
      'INSERT INTO usuarios (nombre, email, contraseña) VALUES ($1, $2, $3) RETURNING id, nombre, email',
      [nombre, email, hashedPassword]
    );
  },
  findByEmail: (email) => 
    db.query('SELECT * FROM usuarios WHERE email = $1', [email]),
};
