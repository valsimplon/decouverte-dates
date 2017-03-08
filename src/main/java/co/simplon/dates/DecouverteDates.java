package co.simplon.dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DecouverteDates {

	public DecouverteDates() {
		super();
	}

	public boolean estInferieurDateCourante(Date date) throws ParseException {
		
		Boolean retour = false;
		Date aujourdhui = new Date();
		if (date.before(aujourdhui)) {
			retour = true;
		} else if (date.after(aujourdhui)) {
			retour =  false;
		}
		
		return retour;
	}

	public Date construireDate(String chaineFournie) {
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dateFinale = formatter1.parse(chaineFournie);
			return dateFinale;
		} catch (ParseException erreur) {
			erreur.printStackTrace();
		}
		return null;
		
	}

	public Date augmenterDate(Date dateInitiale, int nombreJours,
			int nombreHeures, int nombreMinutes) {
		
		Calendar calendrier = Calendar.getInstance();
		calendrier.setTime(dateInitiale);
		calendrier.add(Calendar.DATE, nombreJours);
		calendrier.add(Calendar.HOUR, nombreHeures);
		calendrier.add(Calendar.MINUTE, nombreMinutes);
		Date dateFinale = calendrier.getTime();
		return dateFinale;
	}

	public String formaterUneDate(Date date) {
		// TODO
		return "";
	}

	public String formaterUneHeure(Date time) {
		// TODO
		return "";
	}
	
	public String formaterDateEnFrancais(Date date) {
		// TODO
		return "";
	}
}
