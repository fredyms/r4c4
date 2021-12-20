package com.cacharrero.services;

import com.cacharrero.model.Clone;
import com.cacharrero.repository.crud.CloneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @ autor Paola Martinez
 **/
@Service
public class CloneServices {
    /**
     * Atributo repositorio
     */
    private final CloneRepository cloneRepository;

    public CloneServices(CloneRepository cloneRepository) {
        this.cloneRepository = cloneRepository;
    }

    /**
     * Metodo para obtener todos los usuarios
     *
     * @return
     */
    public List<Clone> getAll() {
        return cloneRepository.findAll();
    }

    /**
     * Metodo para guardar un Clone
     *
     * @param clone
     * @return
     */
    public Clone newClone(Clone clone) {

        if (clone.getId() != null && clone.getBrand() != null && clone.getProcesor() != null &&
                clone.getOs() != null && clone.getDescription() != null && clone.getPrice() != null &&
                clone.getAvailability() != null && clone.getQuantity() != null && clone.getPhotography() != null) {
            return cloneRepository.save(clone);
        }
        return clone;
    }

    /**
     * Metodo para eliminar Clone
     *
     * @param cloneId
     */

    public void deleteClone(Integer cloneId) {
        Optional<Clone> clone = cloneRepository.findById(cloneId);
        if (clone.isPresent()) {
            cloneRepository.deleteById(cloneId);
        }

    }

    /**
     * Método para actualizar clone
     *
     * @param clone
     * @return
     */
    public Clone updateclone(Clone clone) {
        if (clone.getId() != null) {
            Optional<Clone> exist = cloneRepository.findById(clone.getId());
            if (clone.getBrand() != null) {
                exist.get().setBrand(clone.getBrand());
            }
            if (clone.getProcesor() != null) {
                exist.get().setProcesor(clone.getProcesor());
            }
            if (clone.getOs() != null) {
                exist.get().setOs(clone.getOs());
            }
            if (clone.getDescription() != null) {
                exist.get().setDescription(clone.getDescription());
            }
            if (clone.getPrice() != null) {
                exist.get().setPrice(clone.getPrice());
            }
            if (clone.getAvailability() != null) {
                exist.get().setAvailability(clone.getAvailability());
            }
            if (clone.getQuantity() != null) {
                exist.get().setQuantity(clone.getQuantity());
            }
            if (clone.getPhotography() != null) {
                exist.get().setPhotography(clone.getPhotography());
            }
            return cloneRepository.save(exist.get());
        }
        return clone;
    }

    /**
     * Metodo para obtener y retornar un registro de documento de producto
     * por el valor de su atributo 'id', hacia el metodo 'getCloneById' del
     * CloneRepository
     *
     * @param id
     * @return
     */
    public Optional<Clone> getCloneById(Integer id) {
        return cloneRepository.findById(id);
    }

    /**
     * Listar Clones por un String enviado, si se encuentra en su descripción
     *
     * @param description
     * @return
     */
    public List<Clone> cloneByDesc(String description) {
        return cloneRepository.findCloneByDescriptionContains(description);
    }

    /**
     * Listar Clones que tengan un precio menor o igual al ingresado
     *
     * @param price
     * @return
     */
    public List<Clone> cloneByPrice(Double price) {
        return cloneRepository.findCloneByPriceIsLessThanEqual(price);
    }
}


