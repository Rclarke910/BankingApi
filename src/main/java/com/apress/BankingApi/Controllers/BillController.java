package com.apress.BankingApi.Controllers;

import com.apress.BankingApi.Exception.ResourceNotFoundException;
import com.apress.BankingApi.Models.Account;
import com.apress.BankingApi.Models.Bill;
import com.apress.BankingApi.Response.BillResponse;
import com.apress.BankingApi.Services.AccountService;
import com.apress.BankingApi.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BillController {
    @Autowired
    private BillResponse billResponse;

    @Autowired
    private BillService billService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/accounts/{accountId}/bills", method= RequestMethod.GET)
    public ResponseEntity<?> getAllBillsById (@PathVariable Long accountId){
      
    ResponseEntity<?> b = billResponse.getAllBillsByAccountId(accountId) ;
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    @RequestMapping(value="/bills/{billId}", method=RequestMethod.GET)
    public ResponseEntity<?> getBill(@PathVariable Long billId) {
        ResponseEntity<?> b = billResponse.getBillById(billId);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    protected void verifyBill(Long billId) throws ResourceNotFoundException {
        ResponseEntity<?> responseEntity = billResponse.getBillById(billId);
        Object bill = responseEntity.getBody();
        if (bill == null) {
            throw new ResourceNotFoundException("Bill " + billId + " not found");
        }
    }
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@PathVariable Long accountId, @RequestBody Bill bill) {
        ResponseEntity<?> createdBill = billResponse.createBill(bill, accountId);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bill.getId())
                .toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
//@PostMapping("/accounts/{accountId}/bills")
//public ResponseEntity<?> createBill(@PathVariable Long accountId, @RequestBody Bill bill) {
//    Account account = accountService.getAccountById(accountId); // Fetch Account by accountId
//    if (account == null) {
//        throw new ResourceNotFoundException("Account " + accountId + " not found");
//    }
//    bill.setAccount(account); // Set the Account in Bill
//    ResponseEntity<?> createdBill = billResponse.createBill(bill);
//    HttpHeaders responseHeaders = new HttpHeaders();
//    responseHeaders.setLocation(ServletUriComponentsBuilder
//            .fromCurrentRequest()
//            .path("/{id}")
//            .buildAndExpand(bill.getId())
//            .toUri());
//    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//}



    @RequestMapping(value="/bills/{billId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId) {
        billResponse.updateBill(bill, billId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{customerId}/bills", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBillsForCustomer (@PathVariable Long customerId) throws Exception {
        billResponse.getAllBillsByCustomerId(customerId);
                return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value="/bills/{billId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        verifyBill(billId);
        billResponse.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
