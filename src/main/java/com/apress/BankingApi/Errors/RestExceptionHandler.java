package com.apress.BankingApi.Errors;

import com.apress.BankingApi.Exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    protected ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException cnfe, WebRequest request, HttpHeaders headers, HttpStatusCode status)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Customer Not Found");
        errorDetail.setDetail(cnfe.getMessage());
        errorDetail.setDeveloperMessage(cnfe.getClass().getName());
        errorDetail.setPath(request.getContextPath());
        errorDetail.setTimeStamp(new Date().getTime());
        return handleExceptionInternal(cnfe, errorDetail, headers, status, request);
    }


}