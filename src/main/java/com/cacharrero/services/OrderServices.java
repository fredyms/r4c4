package com.cacharrero.services;
import com.cacharrero.model.Clone;
import com.cacharrero.model.Order;
import com.cacharrero.repository.crud.OrderRepository;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ autor Paola Martinez
 *
 **/

@Service
public class OrderServices {
    /**
     * Atributo repositorio
     */

    private OrderRepository orderRepository;

    public OrderServices(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Metodo para obtener  todos los orden
     *
     * @return
     */
    public List<Order> getAll() {
        return (List<Order>) orderRepository.findAll();
    }

    /**
     * Metodo para obtener  todos los orden por id
     *
     * @return
     */
    public Optional<Order> getOrder(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    /**
     * Metodo para eliminar ordenes
     *
     * @return
     */
    public void deleteOrder(Integer orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.deleteById(orderId);
        }
    }

    /**
     * Metodo para top de usuarios
     *
     * @return
     */
    public Optional<Order> TopUserId() {
        return orderRepository.findTopByOrderByIdDesc();
    }

    /**
     * Metodo para nueva orden
     *
     * @return
     */
    public Order newOrder(Order order) {


        Optional<Order> e = orderRepository.findById(order.getId());
        if (e.isEmpty()) {
            return orderRepository.save(order);
        } else {
            return order;
        }
    }

    /**
     * Metodo para actualizar orden
     *
     * @return
     */
    public Order updateOrder(Order order) {
        if (order.getId() != null) {
            Optional<Order> exist = orderRepository.findById(order.getId());
            if (order.getRegisterDay() != null) {
                exist.get().setRegisterDay(order.getRegisterDay());
            }
            if (order.getStatus() != null) {
                exist.get().setStatus(order.getStatus());
            }
            if (order.getSalesMan() != null) {
                exist.get().setSalesMan(order.getSalesMan());
            }
            if (order.getProducts() != null) {
                exist.get().setProducts(order.getProducts());
            }
            if (order.getQuantities() != null) {
                exist.get().setQuantities(order.getQuantities());
            }

            return orderRepository.save(exist.get());
        }
        return order;
    }

    /**
     * Metodo de Ordenes de pedido asociadas a los asesores de una zona
     *
     * @return
     */
    public List<Order> findByZone(String zona) {
        return orderRepository.findByZone(zona);
    }

    /**
     * Metodo de Ordenes de pedido asociadas a los asesores de una zona por identification
     *
     * @return
     */
    public List<Order> findByIdentification(String identification) {
        return orderRepository.findByIdentification(identification);
    }

    /**
     * Metodo para agregar un producto
     *
     * @param clone
     * @param idOrder
     * @return
     */
    public Order addProduct(Optional<Clone> clone, Integer idOrder) {
        if (clone.isPresent()) {
            Optional<Order> exist = orderRepository.findById(idOrder);
            Map<String, Clone> products = exist.get().getProducts();
            Integer var = 0;
            Set<String> keys = products.keySet();
            ArrayList<String> claves = new ArrayList<>();
            for (String key : keys) {
                claves.add(key);
            }
            Collections.sort(claves);
            int mayorKey = 0;
            int claveInt;
            for (String clave : claves) {
                claveInt = Integer.parseInt(clave);
                if (claveInt > mayorKey) {
                    mayorKey = claveInt;
                }
            }
            var = mayorKey + 1;

            products.put(var + "", clone.get());
            exist.get().setProducts(products);
            return orderRepository.save(exist.get());
        } else {
            return Order.builder().build();
        }
    }

    /**
     * Metodo para agregar cantidad
     *
     * @param cantidad
     * @param idOrder
     * @param idQuantity
     * @return
     */
    public Order addCantidad(Integer cantidad, Integer idOrder, String idQuantity) {
        if (cantidad > 0) {
            Optional<Order> exist = orderRepository.findById(idOrder);
            Map<String, Integer> cantidades = exist.get().getQuantities();
            Integer var = 0;
            if (cantidades.isEmpty()) {
                var = cantidades.size() + 1;
            } else if (cantidades.containsKey(idQuantity)) {
                cantidades.put(idQuantity, cantidad);
                exist.get().setQuantities(cantidades);
                return orderRepository.save(exist.get());
            } else {
                Set<String> keys = cantidades.keySet();
                ArrayList<String> claves = new ArrayList<>();
                for (String key : keys) {
                    claves.add(key);
                }
                Collections.sort(claves);
                int mayorKey = 0;
                int claveInt;
                for (String clave : claves) {
                    claveInt = Integer.parseInt(clave);
                    if (claveInt > mayorKey) {
                        mayorKey = claveInt;
                    }
                }
                var = mayorKey + 1;
            }
            cantidades.put(var + "", cantidad);
            exist.get().setQuantities(cantidades);

            return orderRepository.save(exist.get());
        } else {
            return Order.builder().build();
        }
    }

// public List<Order> ordersSalesManByDate(String dateStr, int id) {
//      return orderRepository.ordersSalesManByDate(dateStr, id);
//  }

// public List<Order> ordersSalesManByState(String state, Integer id) {
//     return orderRepository.ordersSalesManByState(state, id);
//  }

    /**
     * Metodo para busca el id del Asesor
     *
     * @param id
     * @return
     */

    public List<Order> orderById(Integer id) {
        return orderRepository.findBySalesManId(id);

    }

    /**
     * Metodo para buscar el registro y el id del asesor
     *
     * @param registerDay
     * @param id
     * @return
     */
    public List<Order> getByRegisterDayAndSalesManId(String registerDay, Integer id) {
            return orderRepository.findByRegisterDayContainsAndSalesManId(registerDay , id);

    }

    /**
     * Metodo para busca el asesor por estado y id
     *
     * @param id
     * @return
     */

    public List<Order> orderBySalesManStatusAndId(String status, Integer id) {
        return orderRepository.findBySalesManIdAndStatus(id , status);

    }

}

