package bavaria.hightech.banking.konto;

import bavaria.hightech.banking.Buchung;
import bavaria.hightech.banking.Buchungsliste;

public abstract class Konto {

	private int kontonummer;
	private float kontostand = 0;
	private String kontoinhaber;
	private Buchungsliste buchungsliste = new Buchungsliste();

	/** Constructor */
	public Konto(int kontonummer, String kontoinhaber) {
		this.kontonummer = kontonummer;
		this.kontoinhaber = kontoinhaber;
	}

	public int getKontonummer() {
		return kontonummer;
	}

	public String getKontoinhaber() {
		return kontoinhaber;
	}

	public float getKontostand() {
		return kontostand;
	}

	/** kontostand �ndern */
	public void zugang(float betrag, String grund) {
		verbuchen(true, betrag, grund);
	}

	public void abgang(float betrag, String grund) throws Exception {
		verbuchen(false, betrag, grund);
	}

	protected void verbuchen(boolean zugang, float betrag, String grund) {
		if (betrag > 0) {
			if (zugang) {
				kontostand += betrag;
			} else {
				kontostand -= betrag;
			}
			Buchung buchung = new Buchung(zugang ? betrag : -betrag, grund);
			buchungsliste.add(buchung);
		}
	}

	public String kontoauszug() {
		StringBuilder builder = new StringBuilder();
		buchungsliste.resetIteration();
		Buchung current = buchungsliste.next();
		while (current != null) {
			builder.append(current.toString());
			builder.append("\n");
			current = buchungsliste.next();
		}
		return builder.toString();
	}

	/** abstrakte methode "verzinsen" */
	public abstract void verzinsen();

}
