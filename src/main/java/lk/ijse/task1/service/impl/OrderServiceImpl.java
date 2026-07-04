package lk.ijse.task1.service.impl;


import lk.ijse.task1.dto.FilterOrderDTO;
import lk.ijse.task1.dto.ItemDTO;
import lk.ijse.task1.dto.PlaceOrderDTO;
import lk.ijse.task1.entity.Customer;
import lk.ijse.task1.entity.Item;
import lk.ijse.task1.entity.Order;
import lk.ijse.task1.entity.OrderItem;
import lk.ijse.task1.repository.CustomerRepository;
import lk.ijse.task1.repository.ItemRepository;
import lk.ijse.task1.repository.OrderRepository;
import lk.ijse.task1.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository,  CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void placeOrder(PlaceOrderDTO placeOrderDTO) {
        log.info("Execute method placeOrder");
        try{
            Order order = new Order();
            order.setTotal(placeOrderDTO.getTotal());

            Optional<Customer> optionalCustomer = customerRepository.findById(placeOrderDTO.getCustomerId());
            if(optionalCustomer.isEmpty()){
                throw new Exception("Customer not found");
            }
            Customer customer = optionalCustomer.get();
            order.setCustomer(customer);
            Order saveOrder = orderRepository.save(order);

            for(Long itemId : placeOrderDTO.getItemIds()) {
                OrderItem orderItem = new OrderItem();
                Optional<Item> optionalItem = itemRepository.findById(itemId);
                if(optionalItem.isEmpty()){
                    throw new Exception("Item not found");
                }
                Item item = optionalItem.get();
                orderItem.setItem(item);
                orderItem.setOrders(saveOrder);
                orderRepository.save(order);
            }

        }catch(Exception e){
            log.error("Error happened. " +e.getMessage());
        }
    }

    @Override
    public List<FilterOrderDTO> filterOrder(String customerName) {
        log.info("Execute method filterOrder");
        try {
            List<FilterOrderDTO> responseList = new ArrayList<>();
            List<Order> orders = orderRepository.filterOrders(customerName);

            for (Order order : orders) {
                FilterOrderDTO filterOrderDTO = new FilterOrderDTO();
                filterOrderDTO.setOrderId(order.getId());

                // Check for null to prevent NullPointerException if an order has no customer
                if (order.getCustomer() != null) {
                    filterOrderDTO.setCustomerName(order.getCustomer().getName());
                }

                List<ItemDTO> itemDTOList = new ArrayList<>();
                List<OrderItem> orderItemList = order.getOrderItems();

                if (orderItemList != null) {
                    for (OrderItem orderItem : orderItemList) {
                        Item item = orderItem.getItem();
                        if (item != null) {
                            ItemDTO itemDTO = new ItemDTO();
                            itemDTO.setId(item.getId());
                            itemDTO.setName(item.getName());
                            // Add other item fields here if needed (e.g., price, qty)

                            itemDTOList.add(itemDTO);
                        }
                    }
                }

                // 1. Set the item list into your FilterOrderDTO
                // (Make sure FilterOrderDTO has a setItemDTOList or setItems method)
                filterOrderDTO.setItems(itemDTOList);

                // 2. Add the complete DTO to the final response list
                responseList.add(filterOrderDTO);
            }

            // 3. Return the populated list
            return responseList;

        } catch (Exception e) {
            log.error("Error happened while filtering orders: " + e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
