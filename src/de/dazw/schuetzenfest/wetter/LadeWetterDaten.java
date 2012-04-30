package de.dazw.schuetzenfest.wetter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.GregorianCalendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class LadeWetterDaten {

	public static ForecastInformationBean foreCastBean = new ForecastInformationBean();
	public static CurrentConditionBean currentBean = new CurrentConditionBean();
	public static ForecastConditionBean[] foreCastConditions = new ForecastConditionBean[4];
	public static int foreCastZaehler = 0;
	public static int fortschritt = 0;

	private static LadeWetterDaten instance = null;
	
	private static long lastUpdate = 0L;
	
	public static LadeWetterDaten getInstance(){
		if(instance == null){
			instance = new LadeWetterDaten();
		}
		
		return instance;
	}
	
	private LadeWetterDaten() {
		// refreshDaten();
	}

//	@Override
//	public synchronized void run() {
//		super.run();
//		if(lastUpdate == 0L || (lastUpdate + 60000) > GregorianCalendar.getInstance().getTimeInMillis()){
//			refreshDaten();
//			fortschritt++;
//		}
//	}

	public boolean isErrorFree() {
		boolean errorFree = (foreCastBean.isErrorFree() && currentBean.isErrorFree()
				&& foreCastConditions[0].isErrorFree() && foreCastConditions[1].isErrorFree()
				&& foreCastConditions[2].isErrorFree() && foreCastConditions[3].isErrorFree());
		Log.i("LadeWetterDaten", "Errorfree: " + errorFree);
		return errorFree;
	}

	public synchronized void refreshDaten() {

		if(!(lastUpdate == 0L || (lastUpdate + 60000) > GregorianCalendar.getInstance().getTimeInMillis())){
			return;
		} 
		
		lastUpdate = GregorianCalendar.getInstance().getTimeInMillis();
		
		foreCastZaehler = 0;
		fortschritt = 0;

		for (int i = 0; i < foreCastConditions.length; i++) {
			foreCastConditions[i] = new ForecastConditionBean();
		}

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean forecast_information = false;
				boolean current_conditions = false;
				boolean forecast_conditions = false;

				public void startElement(String uri, String localName, String qName,
						Attributes attributes) throws SAXException {

					// System.out.println("Start Element: " + qName);

					if ("forecast_information".equals(qName)) {
						forecast_information = true;
						fortschritt++;
					}

					if ("current_conditions".equals(qName)) {
						current_conditions = true;
						forecast_information = false;
						fortschritt++;
					}

					if ("forecast_conditions".equals(qName)) {
						forecast_conditions = true;
						current_conditions = false;
						forecast_information = false;
						fortschritt++;
					}

					if (forecast_information) {
						handleForecastInformation(foreCastBean, qName, attributes);
					}
					if (current_conditions) {
						handleCurrentConditions(currentBean, qName, attributes);
					}
					if (forecast_conditions) {
						handleForeCastConditions(foreCastConditions[foreCastZaehler], qName,
								attributes);
					}
				}

				private void handleForecastInformation(ForecastInformationBean bean, String qName,
						Attributes attributes) {
					// System.out.println("qName: " + qName);

					bean = (ForecastInformationBean) bean;

					String value = attributes.getValue(0);

					if ("city".equals(qName)) {
						bean.setCity(value);
					}
					if ("postal_code".equals(qName)) {
						bean.setPostalCode(value);
					}
					if ("latitude_e6".equals(qName)) {
						bean.setLatitude_e6(value);
					}
					if ("longitude_e6".equals(qName)) {
						bean.setLongitude_e6(value);
					}
					if ("forecast_date".equals(qName)) {
						bean.setForecast_Date(value);
					}
					if ("current_date_time".equals(qName)) {
						bean.setCurrent_date_time(value);
					}
					if ("unit_system".equals(qName)) {
						bean.setUnit_system(value);
					}
				}

				private void handleCurrentConditions(CurrentConditionBean bean, String qName,
						Attributes attributes) {
					// System.out.println("qName: " + qName);

					bean = (CurrentConditionBean) bean;

					String value = attributes.getValue(0);

					if ("condition".equals(qName)) {
						bean.setCondition(value);
					}
					if ("temp_f".equals(qName)) {
						bean.setTemp_f(value);
					}
					if ("temp_c".equals(qName)) {
						bean.setTemp_c(value);
					}
					if ("humidity".equals(qName)) {
						bean.setHumidity(value);
					}
					if ("icon".equals(qName)) {
						bean.setIcon(value);
					}
					if ("wind_condition".equals(qName)) {
						bean.setWind_condition(value);
					}
				}

				private void handleForeCastConditions(ForecastConditionBean bean, String qName,
						Attributes attributes) {
					// System.out.println("qName: " + qName);

					String value = attributes.getValue(0);

					bean.setValue(foreCastZaehler);
					
					if ("day_of_week".equals(qName)) {
						bean.setDay_of_week(value);
					}
					if ("low".equals(qName)) {
						bean.setLow_data(value);
					}
					if ("high".equals(qName)) {
						bean.setHigh_data(value);
					}
					if ("icon".equals(qName)) {
						bean.setIcon(value);
					}
					if ("condition".equals(qName)) {
						bean.setCondition(value);
						foreCastZaehler++;
					}
				}

				public void endElement(String uri, String localName, String qName)
						throws SAXException {

					// System.out.println("End Element : " + localName);

				}

			};

//			HTTPDownloader down = new HTTPDownloader();
//			File download = down.download("http://www.google.com/ig/api?hl=de&weather=59387&hl=de");
//			
			
			URL url = new URL("http://www.google.com/ig/api?hl=de&weather=59387&hl=de");

			//create the new connection
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			//set up some things on the connection
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);

			//and connect!
			urlConnection.connect();

			//this will be used in reading the data from the internet
			InputStream inputStream = urlConnection.getInputStream();
			
			//InputSource is = new InputSource(new FileInputStream(download));
			InputSource is = new InputSource(inputStream);
			is.setEncoding("ISO-8859-1");
			saxParser.parse(is, handler);

			// download.delete();
			// System.out.println("");
		} catch (Exception e) {
			Log.e("LadeWetterDaten_refreshDaten", "Fehler beim Parsen: " + e.getMessage());
		}

		fortschritt++;
		
	}

}