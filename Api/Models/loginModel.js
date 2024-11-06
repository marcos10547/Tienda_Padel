const db = require('../DB-Config');

module.exports = {
  findByEmail: (email) => 
    db.query('SELECT * FROM usuarios WHERE email = $1', [email]),
};