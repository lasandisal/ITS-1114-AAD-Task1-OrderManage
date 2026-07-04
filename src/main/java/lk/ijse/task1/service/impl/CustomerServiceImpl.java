package lk.ijse.task1.service.impl;

import lk.ijse.task1.dto.CustomerDTO;
import lk.ijse.task1.dto.FilterOrderDTO;
import lk.ijse.task1.dto.ItemDTO;
import lk.ijse.task1.entity.Customer;
import lk.ijse.task1.entity.Item;
import lk.ijse.task1.entity.Order;
import lk.ijse.task1.entity.OrderItem;
import lk.ijse.task1.repository.CustomerRepository;
import lk.ijse.task1.repository.OrderItemRespository;
import lk.ijse.task1.repository.OrderRepository;
import lk.ijse.task1.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving customer into DB");
        try {
            if (customerDTO.getName() == null) {
                throw new RuntimeException("Customer name is null");
            }
            if (customerDTO.getAddress() == null) {
                throw new RuntimeException("Customer address is null");
            }

            Customer customer = new Customer();
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());

            customerRepository.save(customer);
        } catch (Exception e) {
            log.error("Error while saving customer into DB: {}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        log.info("Getting customers from DB");
        try {
            List<Customer> customers = customerRepository.findAll();
            List<CustomerDTO> customerDTOs = new ArrayList<>();
            for (Customer customer : customers) {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setId(customer.getId());
                customerDTO.setName(customer.getName());
                customerDTO.setAddress(customer.getAddress());
                customerDTOs.add(customerDTO);
            }
            return customerDTOs;
        } catch (Exception e) {
            log.error("Error while getting customers: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        log.info("Updating customer in DB");
        try {
            if (customerDTO.getId() == null) {
                throw new RuntimeException("Customer ID is required for update");
            }
            Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getId());
            if (!optionalCustomer.isPresent()) {
                throw new RuntimeException("Customer not found");
            }
            Customer customer = optionalCustomer.get();
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customerRepository.save(customer);
        } catch (Exception e) {
            log.error("Error while updating customer: {}", e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<FilterOrderDTO> getCustomerOrders(long customerId) {
        log.info("Getting customerOrders from DB");
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCustomer.isEmpty()) {
                throw new RuntimeException("Customer not found");
            }

            Customer customer = optionalCustomer.get();
            List<Order> orders = customer.getOrders();
            List<FilterOrderDTO> filterOrderDTOs = new ArrayList<>();

            if (orders != null) {
                for (Order order : orders) {
                    FilterOrderDTO filterOrderDTO = new FilterOrderDTO();
                    filterOrderDTO.setOrderId(order.getId());
                    filterOrderDTO.setCustomerName(customer.getName());

                    List<ItemDTO> itemDTOs = new ArrayList<>();
                    List<OrderItem> orderItems = order.getOrderItems();

                    if (orderItems != null) {
                        for (OrderItem orderItem : orderItems) {
                            Item item = orderItem.getItem();

                            if (item != null) {
                                ItemDTO itemDTO = new ItemDTO();
                                itemDTO.setId(item.getId());
                                itemDTO.setName(item.getName());

                                itemDTOs.add(itemDTO);
                            }
                        }
                    }

                    filterOrderDTO.setItems(itemDTOs);
                    filterOrderDTOs.add(filterOrderDTO);
                }
            }

            return filterOrderDTOs;

        } catch (Exception e) {
            log.error("Error while getting customerOrders from DB: {}", e.getMessage(), e);
            throw e;
        }
    }
}