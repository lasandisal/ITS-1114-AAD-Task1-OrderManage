package lk.ijse.task1.service;

import lk.ijse.task1.dto.FilterOrderDTO;
import lk.ijse.task1.dto.PlaceOrderDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderService {
    void placeOrder(PlaceOrderDTO placeOrderDTO);

    List<FilterOrderDTO> filterOrder(String customerName);


}
