package com.apress.BankingApi.Response;

import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Services.DepositService;
import com.apress.BankingApi.dto.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class DepositResponse {
    @Autowired
    DepositService depositService;

    public ResponseEntity<?> createDeposit(Deposit deposit) {
        try {
            Deposit createdDeposit = depositService.createDeposit(deposit);

            Body body = new Body();
            body.setData(createdDeposit);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("Deposit created Successfully");

            return ResponseEntity.ok(body);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error Posting Deposit");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllDeposits() {
        try {
            Body body = new Body();
            body.setData(depositService.getAllDeposits());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Successfully retrieved Deposits");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error fetching Deposit");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getDepositById(Long depositId) {
        try {
            Optional<Deposit> deposit = Optional.ofNullable(depositService.getDepositById(depositId));

            if (deposit.isPresent()) {
                Body body = new Body();
                body.setData(deposit.get());
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Deposit retrieval success");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Deposit not found");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error finding Deposit");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateDeposit(Deposit updatedDeposit, Long depositId) {
        try {
            Deposit editedDeposit = depositService.updateDeposit(updatedDeposit);

            if (editedDeposit != null) {
                Body body = new Body();
                body.setData(editedDeposit);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Deposit updated successfully");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Deposit not found");

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } finally {

        }
    }

    public ResponseEntity<?> deleteDeposit(Long depositId) {
        try {
            depositService.deleteDepositById(depositId);

            Body body = new Body();
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Deposit deleted successfully");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error deleting Deposit");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

