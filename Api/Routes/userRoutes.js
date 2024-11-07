const express = require('express');
const router = express.Router();
const userController = require('../Controllers/userController');

router.get('/', userController.getAllUsers);
router.get('/:id_usuario', userController.getUser);
router.put('/:id_usuario', userController.updateUser);
router.delete('/:id_usuario', userController.deleteUser);

module.exports = router;