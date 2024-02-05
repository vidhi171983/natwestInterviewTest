package com.natwest.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class CalculatePrimeNumberService {
    @Cacheable("primeNumbers")
    public List<Integer> primeNumbersBruteForce(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        for (var i = 2; i <= n; i++) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    private boolean isPrimeBruteForce(int number) {
        for (var i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> primeNumbersMoreEfficient(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        if (n >= 2) {
            primeNumbers.add(2);
        }
        for (int i = 3; i <= n; i += 2) {
            if (isPrimeNumber(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    private boolean isPrimeNumber(int number) {
        for (int i = 2; i*i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
