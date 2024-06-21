package com.apress.BankingApi.Response;

import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.Models.Withdrawal;
import com.apress.BankingApi.Services.WithdrawalService;
import com.apress.BankingApi.dto.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WithdrawalResponse {

    @Autowired
    WithdrawalService withdrawalService;

    public ResponseEntity<?> getAccountWithdrawals(Long accountID){
        try {

            Body body = new Body();
            body.setData(withdrawalService.getAllAccountWithdrawals(accountID));
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Successfully retrieved all withdrawals");

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Account not found");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) {
        try {
            Optional<Withdrawal> withdrawal = withdrawalService.getWithdrawalByID(withdrawalId);

            if (withdrawal.isPresent()) {
                Body body = new Body();
                body.setData(withdrawal.get());
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Withdrawal successfully retrieved");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("error fetching withdrawal with id:" + withdrawalId);

                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error Account Not Found");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> createWithdrawal(Withdrawal withdrawal, long accountID){
        try{
            Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(withdrawal,accountID);

            Body body = new Body();
            body.setData(createdWithdrawal);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("Created withdrawal and deducted it from the account");

            return ResponseEntity.ok(body);
        }catch(Exception exception){
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error creating withdrawal account not found");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawal, Long withdrawalId) {
        try {

           Withdrawal changingWithdrawal = withdrawalService.updateWithdrawal(withdrawalId, withdrawal);

            if (changingWithdrawal != null) {
                Body body = new Body();
                body.setData(changingWithdrawal);
                body.setCode(HttpStatus.OK.value());
                body.setMessage("Accepted withdrawal modification");

                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                Body body = new Body();
                body.setCode(HttpStatus.NOT_FOUND.value());
                body.setMessage("Withdrawal ID does not exist");

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
            body.setMessage("Error updating withdrawal");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> deleteWithdrawal(Long withdrawalID) {
        try {
            withdrawalService.deleteWithdrawalById(withdrawalID);
            Body body = new Body();
            body.setCode(HttpStatus.NO_CONTENT.value());
            body.setMessage("Withdrawal deleted successfully");

            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("This id does not exist in withdrawal");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error deleting Withdrawal");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
