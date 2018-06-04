package viewmodel;
// BalanceInquiry.java


// Represents a balance inquiry ATM transaction

import common.Transaction;
import interfaces.BalanceInteraction;

public class BalanceInquiry extends Transaction {
	// BalanceInquiry constructor
	private BalanceInteraction balanceInteraction;
	
	public BalanceInquiry(int userAccountNumber, BalanceInteraction balanceInteraction) {
		super(userAccountNumber);
		this.balanceInteraction = balanceInteraction;
	} // end BalanceInquiry constructor

	// performs the transaction
	@Override
	public void execute() {
		// get references to bank database and screen	
		balanceInteraction.displayBalance(getBankDatabase().getAvailableBalance(getAccountNumber()), 
				getBankDatabase().getTotalBalance(getAccountNumber()));
	} // end method execute
} // end class BalanceInquiry

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/