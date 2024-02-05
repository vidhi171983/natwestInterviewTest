package com.natwest.controller;

import com.natwest.entity.ResponseWrapper;
import com.natwest.service.CalculatePrimeNumberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@Validated
public class PrimeNumberController {

    private CalculatePrimeNumberService calculatePrimeNumberService;


    @GetMapping(value = "/primeNumbers" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<Integer>>>  getPrimeNumbers(@RequestParam @Positive(message = "Number cannot be negative") Integer number )
    {
        List<Integer>  primeNumbersList = calculatePrimeNumberService.primeNumbersBruteForce(number);
        // Create a ResponseWrapper with message and data
        ResponseWrapper <List<Integer>> responseWrapper = new ResponseWrapper<> ();
        responseWrapper.setMessage("Prime numbers up to " + number);
        responseWrapper.setData(primeNumbersList);

        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }
}
