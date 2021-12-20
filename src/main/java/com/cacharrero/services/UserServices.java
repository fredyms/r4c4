package com.cacharrero.services;

import com.cacharrero.model.User;
import com.cacharrero.repository.crud.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ autor Paola Martinez
 **/
@Service
public class UserServices {
    /**
     * Atributo repositorio
     */


    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Metodo para obtener todos los usuarios
     *
     * @return
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Metodo para crear un usuario
     *
     * @param user
     * @return
     */

    public User newUser(User user) {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return userRepository.save(user);
        } else if (verifyEmail(user.getEmail()) == false) {
            return userRepository.save(user);
        }
        return user;
    }

    /**
     * Metodo para verificar un correo existe en la base de datos
     *
     * @param email
     * @return
     */

    public boolean verifyEmail(String email) {
        boolean flag = false;
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Metodo para obtener y retornar un registro de documento de cuenta de
     * usuario por el valor de su atributo 'id', hacia el metodo 'getUserById'
     * del UserRepository
     *
     * @param id
     * @return
     */
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Metodo para verificar si un usuario existe en la base de datos
     *
     * @param email
     * @param pass
     * @return
     */
    public User verifyUser(String email, String pass) {
        List<User> users = userRepository.findAll();
        User notExit = User.builder().build();
        for (User user : users) {
            if (email.equals(user.getEmail()) && pass.equals(user.getPassword())) {
                return user;
            }
        }
        return notExit;
    }

    /**
     * Metodo para eliminar un usuario
     *
     * @param idUser
     */
    public void deleteUser(Integer idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            userRepository.deleteById(idUser);

        }


    }

    /**
     * Metodo par actualizar el usuario
     *
     * @param user
     * @return
     */

    public User updateUser(User user) {
        if (user.getId() != null) {
            Optional<User> exist = userRepository.findById(user.getId());
            if (exist.isPresent()) {
                if (user.getName() != null) {
                    exist.get().setName(user.getName());
                }
                if (user.getIdentification() != null) {
                    exist.get().setIdentification(user.getIdentification());
                }
                if (user.getEmail() != null) {
                    exist.get().setEmail(user.getEmail());
                }
                if (user.getBirthtDay() != null) {
                    exist.get().setBirthtDay(user.getBirthtDay());
                }
                if (user.getMonthBirthtDay() != null) {
                    exist.get().setMonthBirthtDay(user.getMonthBirthtDay());
                }
                if (user.getAddress() != null) {
                    exist.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    exist.get().setCellPhone(user.getCellPhone());
                }
                if (user.getPassword() != null) {
                    exist.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    exist.get().setZone(user.getZone());
                }
                if (user.getType() != null) {
                    exist.get().setType(user.getType());
                }
                return userRepository.save(exist.get());
            }
            return user;
        }
        return user;
    }

    public Optional<User> getbyidentificacion(String identificacion) {

        return userRepository.findByIdentificacion(identificacion);
    }
    /**
     * Método para listar usuarios cuyo mes de cumpleaños sea el ingresado
     *
     * @param month
     * @return
     */

    public List<User> userByMonth(String month) {
        return userRepository.findByMonthBirthtDay(month);
    }

}
