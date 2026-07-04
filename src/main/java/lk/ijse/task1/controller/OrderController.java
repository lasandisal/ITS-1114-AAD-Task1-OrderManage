package lk.ijse.task1.controller;

import lk.ijse.task1.constant.CommonResponse;
import lk.ijse.task1.dto.FilterOrderDTO;
import lk.ijse.task1.dto.PlaceOrderDTO;
import lk.ijse.task1.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.task1.constant.ResponseMessage.SUCCESS_MESSAGE;
import static lk.ijse.task1.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping(value = "v1/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        orderService.placeOrder(placeOrderDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterOrder(@RequestParam(value = "customerName", required = false) String customerName) {
        List<FilterOrderDTO> filterOrderDTOList = orderService.filterOrder(customerName);
        return new CommonResponse(OPERATION_SUCCESS,filterOrderDTOList,SUCCESS_MESSAGE);
    }
}
