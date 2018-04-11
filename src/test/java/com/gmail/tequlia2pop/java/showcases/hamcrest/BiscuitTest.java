package com.gmail.tequlia2pop.java.showcases.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class BiscuitTest {

	@Test
	public void testEquals() {
		Biscuit theBiscuit = new Biscuit("Ginger");
		Biscuit myBiscuit = new Biscuit("Ginger");
		assertThat(theBiscuit, equalTo(myBiscuit));
	}

}
