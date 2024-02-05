package com.example.natwest.service;

import com.natwest.service.CalculatePrimeNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatePrimeNumberServiceTest {

@InjectMocks
private CalculatePrimeNumberService calculatePrimeNumberService;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
void primeNumbersBruteForce_GeneratesPrimeNumbers() {
    int n = 15;

    List<Integer> expectedPrimeNumbers = List.of(2, 3, 5, 7, 11, 13);
    List<Integer> primeNumbers = calculatePrimeNumberService.primeNumbersBruteForce(n);
    assertEquals(expectedPrimeNumbers, primeNumbers);
}

}

