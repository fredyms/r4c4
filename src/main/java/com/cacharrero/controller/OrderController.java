package com.cacharrero.controller;

import com.cacharrero.model.Clone;
import com.cacharrero.model.Order;
import com.cacharrero.services.OrderServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Paola Martinez
 */
@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")

public class OrderController {
    /**
     * atributo Services
     */

    private OrderServices orderServices;

    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    /**
     * Mmetodo para obtener zona
     *
     * @param zone
     * @return
     */
    @GetMapping("/zona/{zone}")
    public List<Order> getOrder(@PathVariable("zone") String zone) {
        return orderServices.findByZone(zone);
    }

    /**
     * Metodo para obtener identificación
     *
     * @param identification
     * @return
     */

    @GetMapping("/identificacion/{identification}")
    public List<Order> getOrderByIdentification(@PathVariable("identification") String identification) {
        return orderServices.findByIdentification(identification);
    }

    /**
     * Metodo para obtener toda la orden
     *
     * @return
     */

    @GetMapping("/all")
    public List<Order> getAll() {
        return orderServices.getAll();
    }

    /**
     * Metodo para obtener el id de la orden
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") int id) {
        return orderServices.getOrder(id);
    }

    /**
     * Metodo para guardar una orden
     *
     * @param order
     * @return
     */

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        return orderServices.newOrder(order);
    }

    /**
     * Metodo para Actualizar la orden
     *
     * @param order
     * @return
     */

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order order) {
        return orderServices.updateOrder(order);
    }

    /**
     * Metodo para eliminar orden
     *
     * @param id
     */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        orderServices.deleteOrder(id);
    }

    /**
     * Metodo para agregar un producto
     *
     * @param idOrder
     * @param clone
     * @return
     */

    @PutMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order addProduct(@PathVariable("id") Integer idOrder, @RequestBody Optional<Clone> clone) {
        return orderServices.addProduct(clone, idOrder);
    }

    /**
     * Metodo para agregar cantidad
     *
     * @param idOrder
     * @param cantidad
     * @param idQuantity
     * @return
     */

    @PutMapping("/cantidad/{id}/{cantidad}/{idQuantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order addCantidad(@PathVariable("id") Integer idOrder, @PathVariable("cantidad") Integer cantidad, @PathVariable("idQuantity") String idQuantity) {
        return orderServices.addCantidad(cantidad, idOrder, idQuantity);
    }


    /**
     * Metodo para buscar el id y el estado de un Asesor
     *
     * @param status
     * @param id
     * @return
     */


    @GetMapping("/state/{status}/{id}")
    public List<Order> getByStatusId(@PathVariable("status") String status, @PathVariable("id") Integer id) {
        return orderServices.orderBySalesManStatusAndId(status, id);
    }

    /**
     * Mmetodo para buscar id y fecha del Asesor
     *
     * @param date
     * @param id
     * @return
     */


    @GetMapping("/date/{date}/{id}")
    public List<Order> getByDateId(@PathVariable("date") String date, @PathVariable("id") Integer id) {
        return orderServices.getByRegisterDayAndSalesManId(date, id);
    }

    /**
     * @param id
     * @return
     */

    @GetMapping("/salesman/{id}")
    public List<Order> getBySalesManId(@PathVariable("id") Integer id) {
        return orderServices.orderById(id);
    }


}
