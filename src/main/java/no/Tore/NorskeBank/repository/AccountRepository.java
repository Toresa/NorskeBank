package no.Tore.NorskeBank.repository;

import no.Tore.NorskeBank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Use a custom query to reference customer.customerId
    @Query("SELECT a FROM Account a WHERE a.customer.customerId = :customerId")
    List<Account> findByCustomerId(@Param("customerId") Long customerId);
}
