package com.cacharrero.controller;

import com.cacharrero.model.Clone;
import com.cacharrero.services.CloneServices;
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
@RequestMapping("/api/clone/")
@AllArgsConstructor

public class CloneController {

    /**
     * atributo API
     */

    private CloneServices cloneServices;

    /**
     * Metodo para obtener y retornar un registro de documento de cuenta de
     * usuario por el valor de su atributo 'id', hacia el metodo 'getUserById'
     * del UserService
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Clone> getUser(@PathVariable("id") Integer id) {
        return cloneServices.getCloneById(id);
    }

    /**
     * Metodo para obtener todos los usarios
     *
     * @return
     */
    @GetMapping("all")
    public List<Clone> getAll() {
        return cloneServices.getAll();
    }

    /**
     * Metodo para crear un usuario
     *
     * @param
     * @return
     */
    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public Clone save(@RequestBody Clone clone) {
        return cloneServices.newClone(clone);
    }

    /**
     * Metodo para borrar un usuario
     *
     * @param cloneId
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer cloneId) {
        cloneServices.deleteClone(cloneId);
    }

    /**
     * Metodo para actualizar usuario
     *
     * @param clone
     * @return
     */
    @PutMapping("update")
    @ResponseStatus(HttpStatus.CREATED)
    public Clone updateUser(@RequestBody Clone clone) {
        return cloneServices.updateclone(clone);
    }

    /**
     * Listar clones por un String enviado, si se encuentra en su descripción
     *
     * @param description
     * @return
     */
    @GetMapping("description/{desc}")
    public List<Clone> cloneByDesc(@PathVariable("desc") String description) {
        return cloneServices.cloneByDesc(description);
    }

    /**
     * Listar clones que tengan un precio menor o igual al ingresado
     *
     * @param price
     * @return
     */
    @GetMapping("price/{price}")
    public List<Clone> cloneByPrice(@PathVariable("price") Double price) {
        return cloneServices.cloneByPrice(price);
    }


}
