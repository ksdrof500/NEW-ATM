package interfaces;

public interface Menuinteraction extends BalanceInteraction, DepositInteraction {
	void menu();
	int displayMainMenu();
	void run();
}
