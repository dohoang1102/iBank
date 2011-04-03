package bavaria.hightech.testit;

import bavaria.hightech.banking.BankImpl;
import bavaria.hightech.banking.konto.KontoTyp;

public class TestBank {

	public static void main(String[] args) {
		BankImpl bank = new BankImpl();
		bank.newKonto("Heinz", KontoTyp.GIROKONTO);
		bank.newKonto("Karl", KontoTyp.FESTGELDKONTO);
		bank.list();
		bank.deposit(1000, 2000, "gehalt");
		bank.deposit(1001, 10, "hartz4");
		bank.verzinsen();
		try {
			bank.drawout(1000, 3000, "steuerhinterziehung");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bank.drawout(1001, 200, "tv gekauft");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bank.verzinsen();
		bank.list();
	}
}
