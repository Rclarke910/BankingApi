package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Deposit;
import com.apress.BankingApi.Response.P2PResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public class P2PController
{
    P2PResponse p2pResponse;
    @RequestMapping(value = "/accounts/{accountId}/peerPayment", method = RequestMethod.POST)
    public ResponseEntity<?> peerPayment(@PathVariable Long accountId,@Valid @RequestBody Deposit deposit )
    {
        return new ResponseEntity<>(p2pResponse.createPeerPayment(accountId,deposit), HttpStatus.CREATED);
    }
}
