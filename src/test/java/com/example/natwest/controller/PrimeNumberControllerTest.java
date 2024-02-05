package com.example.natwest.controller;

import com.natwest.controller.PrimeNumberController;
import com.natwest.entity.ResponseWrapper;
import com.natwest.service.CalculatePrimeNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Validation;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PrimeNumberControllerTest {

@Mock
private CalculatePrimeNumberService calculatePrimeNumberService;

@InjectMocks
private PrimeNumberController primeNumberController;

@BeforeEach
void setUp() {
     MockitoAnnotations.openMocks(this);
}

@Test
void getPrimeNumbers_ValidInput_ReturnsPrimeNumbers() {

    int number = 10;
    List<Integer> expectedPrimeNumbers = Arrays.asList(2, 3, 5, 7);
    when(calculatePrimeNumberService.primeNumbersBruteForce(number)).thenReturn(expectedPrimeNumbers);

    ResponseEntity<ResponseWrapper<List<Integer>>> responseEntity = primeNumberController.getPrimeNumbers(number);

    // Assert
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    ResponseWrapper<List<Integer>> responseWrapper = responseEntity.getBody();
    assertEquals("Prime numbers up to " + number, responseWrapper.getMessage());
    assertEquals(expectedPrimeNumbers, responseWrapper.getData());
}


}
