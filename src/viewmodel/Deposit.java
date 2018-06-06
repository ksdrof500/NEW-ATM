package viewmodel;
// Deposit.java

// Represents a deposit ATM transaction

import common.Transaction;
import interfaces.DepositInteraction;
import utils.DepositSlot;
import utils.Keypad;

public class Deposit extends Transaction {
	private double amount; // amount to deposit
	private final static int CANCELED = 0; // constant for cancel option
	private DepositInteraction depositInteraction;

	// Deposit constructor
	public Deposit(int userAccountNumber, DepositInteraction depositInteraction) {
		// initialize superclass variables
		super(userAccountNumber);
		this.depositInteraction = depositInteraction;
	} // end Deposit constructor

	// perform transaction
	@Override
	public void execute() {

		amount = promptForDepositAmount(); // get deposit amount from user

		// check whether user entered a deposit amount or canceled
		if (amount != CANCELED) {
			// request deposit envelope containing specified amount
			depositInteraction.displayMessage("\nPlease insert a deposit envelope containing ");
			depositInteraction.displayDollarAmount(amount);
			depositInteraction.displayMessageLine(".");

			// check whether deposit envelope was received
			if (DepositSlot.getInstance().isEnvelopeReceived()) {
				depositInteraction.displayMessageLine("\nYour envelope has been "
						+ "received.\nNOTE: The money just deposited will not "
						+ "be available until we verify the amount of any " + "enclosed cash and your checks clear.");

				// credit account to reflect the deposit
				getBankDatabase().credit(getAccountNumber(), amount);
			} // end if
			else // deposit envelope not received
			{
				depositInteraction.displayMessageLine(
						"\nYou did not insert an " + "envelope, so the ATM has canceled your transaction.");
			} // end else
		} // end if
		else // user canceled instead of entering amount
		{
			depositInteraction.displayMessageLine("\nCanceling transaction...");
		} // end else
	} // end method execute

	// prompt user to enter a deposit amount in cents
	private double promptForDepositAmount() {

		// display the prompt
		depositInteraction.displayMessage("\nPlease enter a deposit amount in " + "CENTS (or 0 to cancel): ");
		int input = Keypad.getInstance().getInput(); // receive input of deposit amount

		// check whether the user canceled or entered a valid amount
		if (input == CANCELED)
			return CANCELED;
		else {
			return (double) input / 100; // return dollar amount
		} // end else
	} // end method promptForDepositAmount
} // end class Deposit

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