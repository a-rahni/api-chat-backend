package fr.m2i.apichat;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;//! fait gaffe bcp AsserThat
public class BasicTests {
    Calculator underTest=new Calculator();
    @Test//Junit
    void itShouldAddTwoNumbers(){
        //given
        int numberOne=20;
        int numberTwo=30;
        //when
        int result=underTest.add(numberOne,numberTwo);
        //then
        int excepted=50;
        assertThat(result).isEqualTo(excepted);

    }
    class Calculator{
        int add(int a, int b){
            return a+b;
        }
    }
}
