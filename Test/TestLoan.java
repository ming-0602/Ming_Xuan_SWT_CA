import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestLoan {

    @Test
    public void TestConstructor(){
        Loan loan = new Loan(500, 3);
        assertEquals(3, loan.getPeriod());
        assertEquals(500,loan.getAmount());
        assertEquals(10, loan.getRate());
    }


    //Test Period
    @Test
    public void testperiod(){
        Loan loan = new Loan(500, 3);
        assertEquals(3, loan.getPeriod());
    }
    @Test
    public void testAmount(){
        Loan loan = new Loan(500, 3);
        assertEquals(500,loan.getAmount());
    }
    @Test
    public void testRate(){
        Loan loan = new Loan(500, 3);
        assertEquals(10, loan.getRate());
    }

    @Test
    public void testgetMonthlyPayment(){
        Loan loan = new Loan(500, 1);
        assertEquals(43.957943615004936, loan.getMonthlyPayment());
    }

    @Test
    public void testgetTotalPayment(){
        Loan loan = new Loan(500, 1);
        assertEquals(527.4953233800593, loan.getTotalPayment());
    }


    @Test
    public void PositiveTest1(){
        Loan loan = new Loan(500,1);
        assertEquals(10,loan.getRate());
    }

    @Test
    public void PositiveTest2(){
        Loan loan = new Loan(5000,3);
        assertEquals(10,loan.getRate());
    }

    @Test
    public void PositiveTest3(){
        Loan loan = new Loan(5001,1);
        assertEquals(8,loan.getRate());
    }

    @Test
    public void PositiveTest4(){
        Loan loan = new Loan(10000,3);
        assertEquals(8,loan.getRate());
    }

    @Test
    public void PositiveTest5(){
        Loan loan = new Loan(500,4);
        assertEquals(6,loan.getRate());
    }

    @Test
    public void PositiveTest6(){
        Loan loan = new Loan(5000,5);
        assertEquals(6,loan.getRate());
    }

    @Test
    public void PositiveTest7(){
        Loan loan = new Loan(5001,4);
        assertEquals(5,loan.getRate());
    }

    @Test
    public void PositiveTest8(){
        Loan loan = new Loan(10000,5);
        assertEquals(5,loan.getRate());
    }

    @Test()
    public void NagativeTest1() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(499,1);
        });
    }

    @Test()
    public void NagativeTest2() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(10001,1);

        });
    }

    @Test()
    public void NagativeTest3() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(0,0);
        });
    }






    @Test()
    public void NagativeTest4() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(500,0);
        });
    }

    @Test()
    public void NagativeTest5() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(500,6);
        });
    }

    @Test()
    public void NagativeTest6() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(Double.parseDouble("Abcd"),0);
        });
    }

    @Test()
    public void NagativeTest7() throws IllegalArgumentException{
        assertThrows(IllegalArgumentException.class, () -> {
            new Loan(500,Integer.parseInt("AbcD"));
        });
    }

//    @Test
//    public void testsetRate(){
//        Loan loan = new Loan(500, 2);
//        assertEquals(10,loan.getRate());
//    }


    @ParameterizedTest
    @CsvFileSource(resources = "rateResource.csv")
    void testparamRate(int amount, int period, int expected){
        Loan loan = new Loan(amount, period);
        assertEquals(expected, loan.getRate());
    }


    @Test
    public void testsetRate() throws Exception{
        Loan loan = new Loan(500, 2);
        Class secretclass  = loan.getClass();
        Field f = secretclass.getDeclaredField("annualRate");
        f.setAccessible(true);
        double result = f.getDouble(loan);
        assertEquals(10, result);

    }


    @Test
    public void testsetAmount() throws Exception{
        Loan loan = new Loan(500, 1);
        Class secretclass = loan.getClass();
        Field f = secretclass.getDeclaredField("loanAmount");
        f.setAccessible(true);
        double result = f.getDouble(loan);
        assertEquals(500, result);

    }

    @Test
    public void testsetPeriod() throws Exception{
        Loan loan = new Loan(500, 1);
        Class secretclass = loan.getClass();
        Field f = secretclass.getDeclaredField("numberOfPayments");
        f.setAccessible(true);
        int result = f.getInt(loan);
        assertEquals(12, result);

    }


}

