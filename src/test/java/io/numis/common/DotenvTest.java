package io.numis.common;

import org.junit.Test;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;

import io.numis.common.environment.Dotenv;
import io.numis.common.environment.DotenvFactory;
import io.numis.common.exceptions.DotenvException;

public class DotenvTest {
	public static void main(String[] args) {
		try {
			Dotenv dotenv = new DotenvFactory()
					.envLocation("src/main/resources")
					.createInstance();
			System.out.println(dotenv.retrieveVariable("GRAPHENEDB_TEAL_BOLT_USER"));
		} catch (DotenvException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_value_integrity() {
		Dotenv dotenv;
		try {
			dotenv = new DotenvFactory()
					.envLocation("src/main/resources")
					.createInstance();
			String y = dotenv.retrieveVariable("GRAPHENEDB_TEAL_BOLT_USER");
			String x = "app58740712-RTZpup";
			assertThat(y, CoreMatchers.containsString(x));
		} catch (DotenvException e) {
			e.printStackTrace();
		}
	}
}
