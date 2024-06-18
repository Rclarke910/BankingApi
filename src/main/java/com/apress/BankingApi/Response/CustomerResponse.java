package com.apress.BankingApi.Response;


import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.dto.Body;
import com.apress.BankingApi.Models.Customer;
import com.apress.BankingApi.Services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerResponse {

    @Autowired
    CustomerService customerService;

    public ResponseEntity<?> createCustomer(Customer customer){
        try{
            Customer createdCustomer = customerService.createCustomer(customer);

            Body body = new Body();
            body.setData(createdCustomer);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("Customer created Successfully");

            return ResponseEntity.ok(body);
        }catch(Exception exception){
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error creating customer");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    public ResponseEntity<?> getAllCustomers(){
        try{
            Body body = new Body();
            body.setData(customerService.getAllCustomers());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Successfully retrieved all customers");

            return new ResponseEntity<>(body, HttpStatus.OK);
        }catch(Exception exception){
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching customer");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> getCustomerById(Long customerId) {
        try {
            Optional<Customer> customer = customerService.getCustomerById(customerId);

            if (customer.isPresent()) {
                Body body = new Body();
                body.setData(customer.get());
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Customer retrieval success");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Customer not found");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error fetching customer");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateCustomer(Customer updatedCustomer, Long customerId) {
        try {
            Customer editedCustomer = customerService.editCustomer(updatedCustomer, customerId);

            if (editedCustomer != null) {
                Body body = new Body();
                body.setData(editedCustomer);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Customer updated successfully");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Customer not found");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error updating customer");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteCustomer(Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            Body body = new Body();
            body.setCode(HttpStatus.NO_CONTENT.value());
            body.setMessage("Customer deleted successfully");

            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error deleting customer");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
