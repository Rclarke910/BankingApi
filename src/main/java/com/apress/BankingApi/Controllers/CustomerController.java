package com.apress.BankingApi.Controllers;



import com.apress.BankingApi.Models.Customer;
import com.apress.BankingApi.Repos.CustomerRepository;
import com.apress.BankingApi.Response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerResponse customerResponse;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/customers/{customer_Id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customer_Id){
        return new ResponseEntity<>(customerResponse.getCustomerById(customer_Id), HttpStatus.OK);
    }


    @GetMapping(value = "/customers")
    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<>(customerResponse.getAllCustomers(), HttpStatus.OK);
    }


    @PostMapping(value = "/customers")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer){
        return new ResponseEntity<>(customerResponse.createCustomer(customer), HttpStatus.CREATED);
    }


    @PutMapping(value = "/customers/{customer_Id}")
    public ResponseEntity<?> updateCustomerById(@RequestBody Customer customer, @PathVariable Long customer_Id){
        return new ResponseEntity<>(customerResponse.updateCustomer(customer, customer_Id), HttpStatus.OK);
    }


    @DeleteMapping(value = "/customers/{customer_Id}")
    public ResponseEntity<?> deleteCustomerById (@PathVariable Long customer_Id){
        return new ResponseEntity<>(customerResponse.deleteCustomer(customer_Id), HttpStatus.NO_CONTENT);
    }


}
