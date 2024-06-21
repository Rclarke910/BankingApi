package com.apress.BankingApi.Response;

import com.apress.BankingApi.Models.*;
import com.apress.BankingApi.Services.AccountService;
import com.apress.BankingApi.Services.DepositService;
import com.apress.BankingApi.Services.WithdrawalService;
import com.apress.BankingApi.dto.Body;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class P2PResponse
{
    WithdrawalService withdrawalService;
    DepositService depositService;
    AccountService accountService;
    public ResponseEntity<?> createPeerPayment(long accountID, Deposit deposit) {
        try {
            Deposit createdDeposit = depositService.createDeposit(deposit,deposit.getPayee().getId());
            Withdrawal createdWithdrawal = withdrawalService.peerWithdrawalFromDeposit(accountID,deposit);
            P2P createdP2P = new P2P(createdWithdrawal,createdDeposit);
            Body body = new Body();
            body.setData(createdP2P);
            body.setCode(HttpStatus.CREATED.value());
            body.setMessage("PeerPayment created Successfully");
            return ResponseEntity.ok(body);
        } catch (Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.setMessage("Error Posting PeerPayment");

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
