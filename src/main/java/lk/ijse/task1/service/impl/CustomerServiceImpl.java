package lk.ijse.task1.service.impl;

import lk.ijse.task1.dto.CustomerDTO;
import lk.ijse.task1.entity.Customer;
import lk.ijse.task1.repository.CustomerRepository;
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
        try{
            Customer customer = new Customer();

            if(customerDTO.getName() ==null){
                throw new Exception("Customer name is null");
            }

            if(customerDTO.getName() ==null){
                throw new Exception("Customer name is null");
            }

            if(customerDTO.getAddress() ==null){
                throw new Exception("Customer address is null");
            }

            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());

            customerRepository.save(customer);

        }catch(Exception e){
            log.error("Error while saving customer into DB" + e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        log.info("Getting customers into DB");
        try{
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
        }catch(Exception e){
            log.error("Error while getting customers into DB" + e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        log.info("Updating customer into DB");
        try{
            Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getId());
            if(!optionalCustomer.isPresent()){
                throw new Exception("Customer not found");
            }
            Customer customer = optionalCustomer.get();
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customerRepository.save(customer);
            }catch(Exception e){
            log.error("Error while updating customer into DB" + e.getMessage());
        }
    }
}
