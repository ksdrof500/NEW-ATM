package util;


import static org.junit.Assert.*;

import utils.CashDispenser;

import org.junit.Before;
import org.junit.Test;

public class CashDispenserTest {

	private CashDispenser cashDispenser;
	
	@Before
	public void setUp() {
		cashDispenser = new CashDispenser();
	}
	
	@Test()
	public void testDispenseCash() {
		cashDispenser.dispenseCash(100);
		assertEquals(495, cashDispenser.getCount());
	}
	
	@Test()
	public void testIsSufficientCashAvailablehTrue() {
		assertTrue(cashDispenser.isSufficientCashAvailable(100));
	}
	
	@Test()
	public void testIsSufficientCashAvailablehFalse() {
		assertFalse(cashDispenser.isSufficientCashAvailable(100001));
	}
	

}
