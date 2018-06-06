package viewmodel;

import common.Transaction;
import interfaces.WithdrawInteraction;
import utils.CashDispenser;

public class Withdrawal extends Transaction {
	private static final int[] amounts = { 0, 20, 40, 60, 100, 200 };
	private int amount; // amount to withdraw
	private CashDispenser cashDispenser; // reference to cash dispenser
	private WithdrawInteraction withdrawInteraction;

	// constant corresponding to menu option to cancel
	private final static int TWENTY = 1;
	private final static int FORTY = 2;
	private final static int SEXTY = 3;
	private final static int HUNDRED = 4;
	private final static int TWO_HUNDRED = 5;
	private final static int CANCELED = 6;

	// Withdrawal constructor
	public Withdrawal(int userAccountNumber, WithdrawInteraction withdrawInteraction) {
		// initialize superclass variables
		super(userAccountNumber);
		this.cashDispenser = new CashDispenser();
		this.withdrawInteraction = withdrawInteraction;
	} // end Withdrawal constructor

	// perform transaction
	@Override
	public void execute() {
		boolean cashDispensed = false; // cash was not dispensed yet
		double availableBalance; // amount available for withdrawal

		// loop until cash is dispensed or the user cancels
		do {
			// obtain a chosen withdrawal amount from the user
			amount = displayMenuOfAmounts();

			// check whether user chose a withdrawal amount or canceled
			if (amount != CANCELED) {
				// get available balance of account involved
				availableBalance = getBankDatabase().getAvailableBalance(getAccountNumber());

				// check whether the user has enough money in the account
				if (amount <= availableBalance) {
					// check whether the cash dispenser has enough money
					if (cashDispenser.isSufficientCashAvailable(amount)) {
						// update the account involved to reflect the withdrawal
						getBankDatabase().debit(getAccountNumber(), amount);

						cashDispenser.dispenseCash(amount); // dispense cash
						cashDispensed = true; // cash was dispensed

						// instruct user to take cash
						withdrawInteraction.displayMessageLine(
								"\nYour cash has been" + " dispensed. Please take your cash now.");
					} // end if
					else // cash dispenser does not have enough cash
						withdrawInteraction.displayMessageLine(
								"\nInsufficient cash available in the ATM." + "\n\nPlease choose a smaller amount.");
				} // end if
				else // not enough money available in user's account
				{
					withdrawInteraction.displayMessageLine(
							"\nInsufficient funds in your account." + "\n\nPlease choose a smaller amount.");
				} // end else
			} // end if
			else // user chose cancel menu option
			{
				withdrawInteraction.displayMessageLine("\nCanceling transaction...");
				return; // return to main menu because user canceled
			} // end else
		} while (!cashDispensed);

	} // end method execute

	// display a menu of withdrawal amounts and the option to cancel;
	// return the chosen amount or 0 if the user chooses to cancel
	private int displayMenuOfAmounts() {
		int userChoice = 0; // local variable to store return value

		// array of amounts to correspond to menu numbers

		// loop while no valid choice has been made
		while (userChoice == 0) {
			int input = withdrawInteraction.displayAmount();
			switch (input) {
			case TWENTY: // if the user chose a withdrawal amount
			case FORTY: // (i.e., chose option 1, 2, 3, 4 or 5), return the
			case SEXTY: // corresponding amount from amounts array
			case HUNDRED:
			case TWO_HUNDRED:
				userChoice = amounts[input]; // save user's choice
				break;
			case CANCELED: // the user chose to cancel
				userChoice = CANCELED; // save user's choice
				break;
			default: // the user did not enter a value from 1-6
				withdrawInteraction.displayMessageLine("\nInvalid selection. Try again.");
			} // end switch
		} // end while

		return userChoice; // return withdrawal amount or CANCELED
	} // end method displayMenuOfAmounts
} // end class Withdrawal

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