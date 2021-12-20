package com.cacharrero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @ autor Paola Martinez
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clone")
public class Clone {
    /**
     * Atributo ID
     */
    @Id
    private Integer id;
    /**
     * Atributo brand
     */
    private String brand;
    /**
     * Atributo Procesor
     */
    private String procesor;
    /**
     * Atributo os
     */
    private String os;
    /**
     * Atributo Description
     */
    private String description;
    /**
     * Atributo memery
     */
    private String memory;
    /**
     * Atributo hardDrive
     */
    private String hardDrive;
    /**
     * Atributo availability
     */
    private Boolean availability = true;
    /**
     * Atributo price
     */
    private Double price;
    /**
     * Atributo quantity
     */
    private Integer quantity;
    /**
     * Atributo photography
     */
    private String photography;

}


