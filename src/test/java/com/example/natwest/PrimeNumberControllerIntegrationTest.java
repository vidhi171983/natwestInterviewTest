package com.example.natwest;

import com.natwest.controller.PrimeNumberController;
import com.natwest.error.CustomExceptionHandler;
import com.natwest.service.CalculatePrimeNumberService;
import io.restassured.filter.Filter;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import io.restassured.RestAssured;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PrimeNumberController.class})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class PrimeNumberControllerIntegrationTest {

    @MockBean
    private CalculatePrimeNumberService calculatePrimeNumberService;

    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    public void testGetPrimeNumbers() {
        int number = 11;

        List<Integer> expectedPrimeNumbers = Arrays.asList(2, 3, 5, 7);
        when(calculatePrimeNumberService.primeNumbersBruteForce(number)).thenReturn(expectedPrimeNumbers);

        given().auth().basic("user", "password")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("number", number)
                .when()
                .get("/api/primeNumbers")
                .then()
                .statusCode(200)
                .body("message", containsString("Prime numbers up to " + number))
                .body("data", hasSize(greaterThan(0)));
    }

    @Test
    public void testGetPrimeNumbersForNegativeNumber() {
        int number = -1;

        given().auth().basic("user", "password")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("number", number)
                .when()
                .get("/api/primeNumbers").then().statusCode(500).log().all();


    }
}
