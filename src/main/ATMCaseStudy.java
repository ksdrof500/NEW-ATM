package main;

import dao.BankDatabase;
import interfaces.DataRepository;
import interfaces.Menuinteraction;
import ui.ATM;

public class ATMCaseStudy {
	private static Menuinteraction menuinteraction;
	private static DataRepository repository;
	
	public static void main(String[] args) {
		definePersistence(new BankDatabase());
		defineMenuinteraction(new ATM());
		menuinteraction.run();
	}
	
	public static void definePersistence(DataRepository repository) {
		ATMCaseStudy.repository = repository;
	}
	
	public static DataRepository getPersistence() {
		return repository;
	}
	
	public static void defineMenuinteraction(Menuinteraction menuinteraction) {
		ATMCaseStudy.menuinteraction = menuinteraction;
	}

	public static Menuinteraction getMenuinteraction() {
		return menuinteraction;
	}
}
