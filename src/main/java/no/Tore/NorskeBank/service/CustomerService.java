package no.Tore.NorskeBank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import no.Tore.NorskeBank.model.Customer;
import no.Tore.NorskeBank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CustomerService {
    // This class will contain the business logic for managing customers
    // For now, it's empty, but you can add methods to handle customer-related operations
    // such as creating, updating, deleting, and retrieving customers.
    @Autowired CustomerRepository customerRepository;
        
    // Example method to get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
