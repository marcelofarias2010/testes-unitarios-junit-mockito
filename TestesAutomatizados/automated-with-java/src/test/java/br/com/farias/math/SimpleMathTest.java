package br.com.farias.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test Math Operation Simple Math Class")
public class SimpleMathTest {

    @BeforeAll
    static void setup(){
        System.out.println("Running @BeforeAll method!");
    }

    @AfterAll
    static void cleanup(){
        System.out.println("Running @AfterAll method!");
    }

    @BeforeEach
    void beforeEachMethod(){

        System.out.println("Running @BeforeEach method!");
    }

    @AfterEach
    void afterEachMethod(){
        System.out.println("Running @AfterEach method!");
    }

    @Test
    @DisplayName("Test 6.2 + 2.2 = 8.2")
    void testSum() {
        // Given / Arrange
        SimpleMath math = new SimpleMath();
        Double firstNumber = 6.2D;
        double secondNumber = 2D;
        double expected = 8.2D;
        // when / Act
        Double actual = math.sum(firstNumber, secondNumber);
        // then / Assert
        Assertions.assertEquals(expected, actual, () -> firstNumber + "+" + secondNumber + " did not produce 8.2!" + expected);
        Assertions.assertNotEquals(9.2, actual);
        Assertions.assertNotNull(actual);
        //Assertions.assertEquals(expected,actual,"The testSum() did not produce expected result!");

    }

    @Test
    @DisplayName("Test 6.2 - 2.2 = 4")
    void testSub() {
        SimpleMath math = new SimpleMath();
        Double firstNumber = 6.2D;
        double secondNumber = 2.2D;

        Double actual = math.subtraction(firstNumber, secondNumber);
        double expected = 4D;

        Assertions.assertEquals(expected, actual, "Valor inválido! Esperava-se como resultado: " + expected);
    }

    @Test
    @DisplayName("Test 5 * 5 = 25")
    void testMult() {
        SimpleMath math = new SimpleMath();
        Double firstNumber = 5D;
        double secondNumber = 5D;

        Double actual = math.multiplication(firstNumber, secondNumber);
        double expected = 25D;

        Assertions.assertEquals(expected, actual, "Valor inválido! Esperava-se: " + expected);

    }

    @Test
    @DisplayName("Test 25 / 5 = 5")
    void testDiv() {
        SimpleMath math = new SimpleMath();
        Double firstNumber = 25D;
        double secondNumber = 5D;

        Double actual = math.division(firstNumber, secondNumber);
        double expected = 5D;

        Assertions.assertEquals(expected, actual, "Valor inválido! Esperava-se: " + expected);
    }

    @Test
    @DisplayName("Test (5 + 5) / 2  = 5")
    void testMean() {
        SimpleMath math = new SimpleMath();
        Double firstNumber = 5D;
        double secondNumber = 5D;
        Double actual = math.mean(firstNumber,secondNumber);
        double expected = 5D;

        Assertions.assertEquals(expected,actual,"Valor inválido! Esperava-se: " + expected);
    }

    @Test
    @DisplayName("Test Square Root of 25 = 5")
    void testSquare(){
        SimpleMath math = new SimpleMath();
        Double firstNumber = 25D;
        Double actual = math.squareRoot(firstNumber);
        double expected = 5D;

        Assertions.assertEquals(expected,actual,"Valor inválido! Esperava-se: " + expected);
    }

    @Disabled("TODO: we need still work on it!")
    @Test
    @DisplayName("Test Division by zero")
    void testDivision_by_zero(){
        // Given
        SimpleMath math = new SimpleMath();
        Double firstNumber = 5D;
        double secondNumber = 0D;

        var expectedMessage = "Impossible to divide by zero!";

        ArithmeticException actual =  assertThrows(ArithmeticException.class, () -> {
            // When & then
            math.division(firstNumber, secondNumber);
        }, () -> "Division by zero should throw an ArithmeticException");

        assertEquals(expectedMessage,actual.getMessage(), () -> "Unexpected exception message!");

    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Display Name")
    @Test
    void testABCD_When_XYZ_Should(){
        // Give / Arrange
        // When / Act
        // Then / Assert
    }
}
