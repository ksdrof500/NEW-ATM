package interfaces;

public interface DataRepository {
	boolean authenticateUser(int userAccountNumber, int userPIN);
	double getAvailableBalance(int userAccountNumber);
	double getTotalBalance(int userAccountNumber);
	void credit(int userAccountNumber, double amount);
	void debit(int userAccountNumber, double amount);
}
