package infrastructure.service;

import infrastructure.dto.OrderDTO;
import infrastructure.model.Order;
import infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> getOrderTable() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>(orders.size());
        for (Order order : orders) {
            OrderDTO o = new OrderDTO();
            o.setId(order.getId());
            o.setPrice(order.getPrice().toString());
            o.setDate(order.getDate().toString());
            o.setNumberOfDay(String.valueOf(order.getNumberOfDay()));
            o.setLogin(order.getAllUsers()[0].getEmail());
            o.setBrand(order.getAllCars()[0].getCarBrand().getBrandName());
            o.setModel(order.getAllCars()[0].getCarModel().getModelName());
            orderDTOList.add(o);
        }
        return orderDTOList;
    }
}
