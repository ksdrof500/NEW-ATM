package main;


import common.Screen;
import interfaces.Menuinteraction;
import model.Deposit;
import model.Withdrawal;
import utils.DepositSlot;
import utils.Keypad;
import viewmodel.BalanceInquiry;
import viewmodel.CashDispenser;

public class ATM extends Screen implements Menuinteraction {
	private boolean userAuthenticated; // whether user is authenticated
	private int currentAccountNumber; // current user's account number
	private Keypad keypad; // ATM's keypad
	private CashDispenser cashDispenser; // ATM's cash dispenser
	private DepositSlot depositSlot; // ATM's deposit slot

	// constants corresponding to main menu options
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;

	// no-argument ATM constructor initializes instance variables
	public ATM() {
		userAuthenticated = false; // user is not authenticated to start
		currentAccountNumber = 0; // no current account number to start
		keypad = Keypad.getInstance();
		cashDispenser = new CashDispenser(); // create cash dispenser
		depositSlot = new DepositSlot(); // create deposit slot
	} // end no-argument ATM constructor

	// start ATM
	@Override
	public void run() {
		// welcome and authenticate user; perform transactions
		while (true) {
			// loop while user is not yet authenticated
			while (!userAuthenticated) {
				displayMessageLine("\nWelcome!");
				authenticateUser(); // authenticate user
			} // end while

			menu(); // user is now authenticated
			userAuthenticated = false; // reset before next ATM session
			currentAccountNumber = 0; // reset before next ATM session
			displayMessageLine("\nThank you! Goodbye!");
		} // end while
	} // end method run

	@Override
	public void menu() {
		boolean userExited = false; // user has not chosen to exit

		// loop while user has not chosen option to exit system
		while (!userExited) {
			// decide how to proceed based on user's menu selection
			switch (displayMainMenu()) {
			// user chose to perform one of three transaction types
			case BALANCE_INQUIRY:
				new BalanceInquiry(currentAccountNumber, this);
				break;
			case WITHDRAWAL:
				new Withdrawal(currentAccountNumber, this, cashDispenser);
				break;
			case DEPOSIT:
				new Deposit(currentAccountNumber, this);
				break;
			case EXIT: // user chose to terminate session
				displayMessageLine("\nExiting the system...");
				userExited = true; // this ATM session should end
				break;
			default: // user did not enter an integer from 1-4
				displayMessageLine("\nYou did not enter a valid selection. Try again.");
				break;
			} // end switch
		} // end while
	} // end method performTransactions

	// display the main menu and perform transactions
	@Override
	public int displayMainMenu() {
		displayMessageLine("\nMain menu:");
		displayMessageLine("1 - View my balance");
		displayMessageLine("2 - Withdraw cash");
		displayMessageLine("3 - Deposit funds");
		displayMessageLine("4 - Exit\n");
		displayMessage("Enter a choice: ");
		return keypad.getInput(); // return user's selection
	} // end method displayMainMenu
	
	// attempts to authenticate user against database
	private void authenticateUser() {
		displayMessage("\nPlease enter your account number: ");
		int accountNumber = keypad.getInput(); // input account number
		displayMessage("\nEnter your PIN: "); // prompt for PIN
		int pin = keypad.getInput(); // input PIN

		// set userAuthenticated to boolean value returned by database
		userAuthenticated = ATMCaseStudy.getPersistence().authenticateUser(accountNumber, pin);

		// check whether authentication succeeded
		if (userAuthenticated) {
			currentAccountNumber = accountNumber; // save user's account #
		} // end if
		else
			displayMessageLine("Invalid account number or PIN. Please try again.");
	} // end method authenticateUser


} // end class ATM
