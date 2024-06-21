package com.apress.BankingApi.Response;

import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.Models.Bill;
import com.apress.BankingApi.Services.BillService;
import com.apress.BankingApi.dto.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class BillResponse {
    @Autowired
    BillService billservice;

    public ResponseEntity<?> createBill(Bill bill) {
        try {
            Bill createdBill = billservice.createBill(bill);

            Body body = new Body();
            body.setData(createdBill);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("Bill created Successfully");

            return ResponseEntity.ok(body);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error Posting Bill");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<?> getAllBills(Long accountId) {
        try {
            Body body = new Body();
            body.setData(billservice.getBillsByAccountId(accountId));
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Successfully retrieved Bills");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching Bill");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getBillById(Long billId) {
        try {
            Optional<Bill> bill = billservice.getBillById(billId);

            if (bill.isPresent()) {
                Body body = new Body();
                body.setData(bill.get());
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Bill retrieval success");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Bill not found");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error finding Bill");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateBill(Bill updatedBill, Long billId) {
        try {
            Bill editedBill = billservice.updateBill(updatedBill, billId);

            if (editedBill != null) {
                Body body = new Body();
                body.setData(editedBill);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Bill updated successfully");

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
            body.setMessage("Error updating Bill");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteBill(Long billId) {
        try {
            billservice.deleteBillById(billId);
            Body body = new Body();
            body.setCode(HttpStatus.NO_CONTENT.value());
            body.setMessage("Bill deleted successfully");

            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage(e.getMessage());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error deleting Bill");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}