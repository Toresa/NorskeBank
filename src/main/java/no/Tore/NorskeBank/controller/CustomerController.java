package no.Tore.NorskeBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import no.Tore.NorskeBank.model.Customer;
import no.Tore.NorskeBank.service.CustomerService;

@Controller
public class CustomerController {
    // This class will handle HTTP requests related to customers
    // For now, it's empty, but you can add methods to handle customer-related requests
    // such as creating, updating, deleting, and retrieving customers.
    // Example method to get all customers
    @Autowired CustomerService customerService;

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers); // Add the list of customers to the model
        return "customers"; // This will return the view name to be rendered
    }
    
}
