package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	
	private Account account;

	@Before
	public void setUp() {
		account = new Account(224489, 113389, 500, 800);
	}

	@Test
	public void testValidatePINSuccess() {
		assertTrue(account.validatePIN(113389));
	}
	
	@Test
	public void testValidatePINError() {
		assertFalse(account.validatePIN(1133908));
	}
	
	@Test
	public void testGetAvailableBalance() {
		assertEquals(500, account.getAvailableBalance(), 0);
	}
	
	@Test
	public void testGetTotalBalance() {
		assertEquals(800, account.getTotalBalance(), 0);
	}
	
	@Test
	public void testGetAccountNumber() {
		assertEquals(224489, account.getAccountNumber(), 0);
	}	
}
