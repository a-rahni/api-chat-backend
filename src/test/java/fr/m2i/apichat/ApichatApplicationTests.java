package fr.m2i.apichat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@SpringBootTest
class ApichatApplicationTests {

	Calculator underTest = new Calculator();
	@Test
	void contextLoads() {
	}

	/*
	basic test using Junit and assertJ
	* */
	@Test
	void itShouldAddNumbers() {
		//given
		int numberA = 20;
		int numberB = 25;
		//when
		int result = underTest.add(numberA,numberB);

		// then
		int expected = 45;
		assertThat(result).isEqualTo(expected);
	}

	class Calculator {
		int add(int a, int b){
			return a+b;
		}
	}

}
