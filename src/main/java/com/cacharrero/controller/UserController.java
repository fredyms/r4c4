package com.cacharrero.controller;

import com.cacharrero.model.User;
import com.cacharrero.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @ autor Paola Martinez
 **/
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {
    /**
     * atributo API
     */

    private UserServices userServices;

    /**
     * Metodo para obtener y retornar un registro de documento de producto
     * por el valor de su atributo 'id', hacia el metodo 'getUserById'
     * del CloneService
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id) {
        return userServices.getUserById(id);
    }

    /**
     * Metodo para obtener todos los usarios
     *
     * @return
     */
    @GetMapping("all")
    public List<User> getAll() {
        return userServices.getAll();
    }

    /**
     * Metodo para crear un usuario
     *
     * @param
     * @return
     */
    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userServices.newUser(user);
    }

    /**
     * metodo para verificar un correo
     *
     * @param email
     * @return
     */
    @GetMapping("emailexist/{email}")
    public boolean verifyEmail(@PathVariable("email") String email) {
        return userServices.verifyEmail(email);
    }

    /**
     * Metodo verificar si existe un usuario por su correo y contraseña
     *
     * @param email
     * @param pass
     * @return
     */
    @GetMapping("{email}/{pass}")
    public User verifyUser(@PathVariable("email") String email, @PathVariable("pass") String pass) {
        return userServices.verifyUser(email, pass);
    }

    /**
     * Metodo para borrar un usuario
     *
     * @param idUser
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer idUser) {
        userServices.deleteUser(idUser);
    }

    /**
     * Metodo para actualizar usuario
     *
     * @param user
     * @return
     */
    @PutMapping("update")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@RequestBody User user) {
        return userServices.updateUser(user);
    }

    /**
     * Metodo para obtener y retornar un registro de documento de producto
     * por el valor de su atributo 'id', hacia el metodo 'getUserById'
     * del CloneService
     *
     * @param identificacion
     * @return
     */
    @GetMapping("identificacion/{identificacion}")
    public Optional<User> getbyId(@PathVariable("identificacion") String identificacion) {
        return userServices.getbyidentificacion(identificacion);
    }
    /**
     * Método para listar usuarios cuyo mes de cumpleaños sea el ingresado
     *
     * @param month
     * @return
     */
    @GetMapping("birthday/{month}")
    public List<User> userByMonth(@PathVariable("month") String month) {
        return userServices.userByMonth(month);
    }


}