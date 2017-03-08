package co.simplon.dates;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class DecouverteDatesTest {

	static DecouverteDates dd;
	
	@BeforeClass
	public static void init() {
		dd = new DecouverteDates();
	}
	
	@Test
	public void la_date_1er_janvier_2017_doit_etre_inferieure_a_la_date_courante() throws ParseException {
		Date date1erJanvier = new SimpleDateFormat("ddMMyy").parse("010117");
	
		boolean resultatComparaison = dd.estInferieurDateCourante(date1erJanvier);
		
		assertTrue(resultatComparaison);
	}
	
	@Test
	public void la_date_3_juin_2017_doit_etre_superieure_a_la_date_courante() throws ParseException {
		Date date3Juin = new SimpleDateFormat("ddMMyy").parse("030617");
		
		boolean resultatComparaison = dd.estInferieurDateCourante(date3Juin);
		
		assertFalse(resultatComparaison);
	}
	
	@Test
	public void la_chaine_fournie_est_convertie_en_la_bonne_date_1() throws ParseException {
		String chaineFournie = "04/04/2017";
		
		Date dateConstruite = dd.construireDate(chaineFournie);
		
		Calendar calendrier = Calendar.getInstance();
		calendrier.setTime(dateConstruite);
		assertThat(calendrier.get(Calendar.DAY_OF_MONTH), equalTo(4));
		assertThat(calendrier.get(Calendar.MONTH), equalTo(Calendar.APRIL));
		assertThat(calendrier.get(Calendar.YEAR), equalTo(2017));
		assertThat(calendrier.get(Calendar.HOUR_OF_DAY), equalTo(0));
		assertThat(calendrier.get(Calendar.MINUTE), equalTo(0));
	}
	
	@Test
	public void la_chaine_fournie_est_convertie_en_la_bonne_date_2() throws ParseException {
		String chaineFournie = "31/12/2017";
		
		Date dateConstruite = dd.construireDate(chaineFournie);
		
		Calendar calendrier = Calendar.getInstance();
		calendrier.setTime(dateConstruite);
		assertThat(calendrier.get(Calendar.DAY_OF_MONTH), equalTo(31));
		assertThat(calendrier.get(Calendar.MONTH), equalTo(Calendar.DECEMBER));
		assertThat(calendrier.get(Calendar.YEAR), equalTo(2017));
		assertThat(calendrier.get(Calendar.HOUR_OF_DAY), equalTo(0));
		assertThat(calendrier.get(Calendar.MINUTE), equalTo(0));
	}
	
	@Test
	public void la_date_est_incrementee_d_un_jour_deux_heures_trente_minutes() throws ParseException {
		Date date3Juin = new SimpleDateFormat("ddMMyy").parse("030617");
		
		Date nouvelleDate = dd.augmenterDate(date3Juin, 1, 2, 30);
		
		Calendar calendrier = Calendar.getInstance();
		calendrier.setTime(nouvelleDate);
		assertThat(calendrier.get(Calendar.DAY_OF_MONTH), equalTo(4));
		assertThat(calendrier.get(Calendar.MONTH), equalTo(Calendar.JUNE));
		assertThat(calendrier.get(Calendar.YEAR), equalTo(2017));
		assertThat(calendrier.get(Calendar.HOUR_OF_DAY), equalTo(2));
		assertThat(calendrier.get(Calendar.MINUTE), equalTo(30));
	}
	
	@Test
	public void la_date_est_incrementee_d_un_jour_et_diminuee_de_vingt_minutes() throws ParseException {
		Date date3Juin = new SimpleDateFormat("ddMMyy hhmm").parse("030617 0010");
		
		Date nouvelleDate = dd.augmenterDate(date3Juin, 1, 0, -20);
		
		Calendar calendrier = Calendar.getInstance();
		calendrier.setTime(nouvelleDate);
		assertThat(calendrier.get(Calendar.DAY_OF_MONTH), equalTo(3));
		assertThat(calendrier.get(Calendar.MONTH), equalTo(Calendar.JUNE));
		assertThat(calendrier.get(Calendar.YEAR), equalTo(2017));
		assertThat(calendrier.get(Calendar.HOUR_OF_DAY), equalTo(23));
		assertThat(calendrier.get(Calendar.MINUTE), equalTo(50));
	}
	
	@Test
	public void le_formattage_de_la_date_est_31_01_2017() throws ParseException {
		Date date = new SimpleDateFormat("ddMMyy").parse("310117");
		
		String dateFormatee = dd.formaterUneDate(date);
		
		assertThat(dateFormatee, equalTo("31_01_2017"));
	}
	
	@Test
	public void le_formatage_de_la_date_est_01_12_2017() throws ParseException {
		Date date = new SimpleDateFormat("ddMMyy").parse("011217");
		
		String dateFormatee = dd.formaterUneDate(date);
		
		assertThat(dateFormatee, equalTo("01_12_2017"));
	}
	
	@Test
	public void le_formatage_de_l_heure_est_21h30min25sec() throws ParseException {
		Calendar calendrier = Calendar.getInstance();
		calendrier.set(Calendar.HOUR_OF_DAY, 21);
		calendrier.set(Calendar.MINUTE, 30);
		calendrier.set(Calendar.SECOND, 25);
		
		String heureFormatee = dd.formaterUneHeure(calendrier.getTime());
		
		assertThat(heureFormatee, equalTo("21h30min25sec"));
	}
	
	@Test
	public void le_formatage_de_la_date_en_francais_doit_etre_conforme() throws ParseException {
		Date date = new SimpleDateFormat("ddMMyy").parse("310117");
		
		String dateFormateeFr = dd.formaterDateEnFrancais(date);
		
		assertThat(dateFormateeFr, equalTo("mardi 31 janvier 2017"));
	}
}
