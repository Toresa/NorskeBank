package no.Tore.NorskeBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Add this import statement

import no.Tore.NorskeBank.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    // Custom query methods can be defined here if needed
    public Customer findByCustomerId(Long customerId);
    public Customer findByCustomerName(String customerName);
	
}
