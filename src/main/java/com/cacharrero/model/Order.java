package com.cacharrero.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.Map;

/**
 * @ autor Paola Martinez
 **/
@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    /**
     * Atributo estaticos Pending
     */
    public static String PENDING = "Pendiente";
    /**
     * Atributo estaticos Aproved
     */
    public static String APROVED = "Aprobada";
    /**
     * Atributo estaticos Rejected
     */
    public static String REJECTED = "Rechazada";
    /**
     * Atributo Id
     */
    @Id
    private Integer id;
    /**
     * Atributo registerDay
     */
    private String registerDay;
    /**
     * Atributo status
     */
    private String status;
    /**
     * Atributo Salesman
     */
    private User salesMan;
    /**
     * Atributo products
     */
    private Map<String, Clone> products;
    /**
     * Atributo quantities
     */
    private Map<String, Integer> quantities;

}
