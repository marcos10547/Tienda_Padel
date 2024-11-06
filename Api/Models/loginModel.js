const db = require('../db-config');

module.exports = {
  findByEmail: (email) => 
    db.query('SELECT * FROM usuarios WHERE email = $1', [email]),
};