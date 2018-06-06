package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dao.BankDatabase;
import interfaces.DataRepository;

public class BankDatabaseTest {
	private DataRepository dataRepository;

	@Before
	public void setUp() {
		dataRepository = new BankDatabase();  
	}

	@Test
	public void testAuthSuccess() {
		assertTrue(dataRepository.authenticateUser(12345, 54321));
	}
	
	@Test
	public void testAuthFalseAccount() {
		assertFalse(dataRepository.authenticateUser(122345, 54321));
	}
	
	@Test
	public void testAuthFalsePin() {
		assertFalse(dataRepository.authenticateUser(12345, 543221));
	}
	
	@Test
	public void testGetAvailableBalance() {
		assertEquals(dataRepository.getAvailableBalance(12345), 1000.0, 0);
	}
	
	@Test
	public void testGetTotalBalance() {
		assertEquals(dataRepository.getTotalBalance(12345), 1200.0, 0);
	}
	
	@Test
	public void testCredit() {
		dataRepository.credit(12345, 100.0);
		assertEquals(dataRepository.getTotalBalance(12345), 1300.0, 0);
	}
	
	@Test
	public void testDebit() {
		dataRepository.debit(12345, 100.0);
		assertEquals(dataRepository.getTotalBalance(12345), 1100.0, 0);
		assertEquals(dataRepository.getAvailableBalance(12345), 900.0, 0);
	}
}
