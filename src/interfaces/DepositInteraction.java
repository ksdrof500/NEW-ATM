package interfaces;

public interface DepositInteraction extends WithdrawInteraction {
	void displayMessage(String message);
	void displayDollarAmount(double amount);
}
