package util;


import static org.junit.Assert.*;

import utils.DepositSlot;

import org.junit.Before;
import org.junit.Test;

public class DepositSlotTest {
	private DepositSlot depositSlot;
	
	@Before
	public void setUp() {
		depositSlot = new DepositSlot();
	}
	
	@Test()
	public void testIsEnvelopeReceived() {
		assertTrue(depositSlot.isEnvelopeReceived());
	}

}
