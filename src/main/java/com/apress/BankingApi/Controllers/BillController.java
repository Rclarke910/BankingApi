package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Models.Bill;
import com.apress.BankingApi.Repos.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @RequestMapping(value="/accounts/{accountId}/bills", method= RequestMethod.GET)
    public Optional<Bill> getBillsById(@PathVariable Long billId) {
        return billRepository.findById(billId);
    }
    @RequestMapping(value="/bills/{billId}", method=RequestMethod.GET)
    public ResponseEntity<?> getBillById(@PathVariable Long billId) {
        Optional<Bill> b = billRepository.findById(billId);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    @RequestMapping(value="/accounts/{accountId}/bills", method= RequestMethod.POST)
    public ResponseEntity<?> createBill (@PathVariable Long accountId, @RequestBody Bill bill) {
        bill = billRepository.save(bill);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{accountId}")
                .buildAndExpand(bill.getId())
                .toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    @RequestMapping(value="/bills/{billId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId) {
        billRepository.save(bill);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
