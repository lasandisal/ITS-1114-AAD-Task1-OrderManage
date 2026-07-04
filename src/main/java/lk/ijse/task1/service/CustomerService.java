package lk.ijse.task1.service;

import lk.ijse.task1.dto.CustomerDTO;
import lk.ijse.task1.dto.FilterOrderDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getCustomers();
    void updateCustomer(CustomerDTO customerDTO);
    List<FilterOrderDTO> getCustomerOrders(long customerId);
}
