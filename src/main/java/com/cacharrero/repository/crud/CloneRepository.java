package com.cacharrero.repository.crud;

import com.cacharrero.model.Clone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @ autor Paola Martinez
 **/
public interface CloneRepository extends MongoRepository<Clone, Integer> {
    /**
     * Listar Clones que tengan un precio menor o igual al ingresado
     *
     * @param price
     * @return
     */
    List<Clone> findCloneByPriceIsLessThanEqual(Double price);

    /**
     * Listar Clones por un String enviado, si se encuentra en su descripción
     *
     * @param description
     * @return
     */
    List<Clone> findCloneByDescriptionContains(String description);

}
