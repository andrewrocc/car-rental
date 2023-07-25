package infrastructure.service;

import infrastructure.dto.OrderDto;
import infrastructure.model.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrderTable(PageRequest pageRequest);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    OrderDto getOrderInfoById(String orderString);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    List<OrderDto> setInfo(List<Order> orders);

    void add(long id, LocalDate startDate, short numberOfDay, OrderDto orderDTO);
}
