package de.dazw.schuetzenfest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;

import org.xml.sax.InputSource;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.dazw.schuetzenfest.wetter.CurrentConditionBean;
import de.dazw.schuetzenfest.wetter.ForecastConditionBean;
import de.dazw.schuetzenfest.wetter.LadeWetterDaten;

public class WetterActivity extends ParentActivity {

	public LadeWetterDaten wetter = null;
	public LoadViewTask loadView = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try{
			loadView = new LoadViewTask();
			loadView.execute();
		}catch(RuntimeException e){
			showFehler();
		}

		// setContentView(R.layout.wetterdaten);
		//
		// while(progressDialog.getProgress() < 100){
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(progressDialog.getProgress());
		// }

		//
		// try {
		// URL url = new
		// URL("http://www.google.com/ig/images/weather/sunny.gif");
		// InputSource inputSource = new InputSource(url.openStream());
		// ImageView iv = (ImageView) findViewById(R.id.wetterdatenimageView1);
		// Bitmap decodeStream =
		// BitmapFactory.decodeStream(inputSource.getByteStream());
		// //iv.setImageURI(Uri.parse("http://www.google.com/ig/images/weather/sunny.gif"));
		// iv.setImageBitmap(decodeStream);
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//

	}

	@Override
	protected void onResume() {
		super.onResume();
		// new LoadViewTask().execute();
	}

	private void showFehler() {
		Toast.makeText(
				getApplicationContext(),
				"Beim Abruf des Wetters ist ein Fehler aufgetreten! Bitte versuchen Sie es erneut!",
				3000).show();
		finish();
		// Intent i = new Intent(this, MainActivity.class);
		// startActivity(i);
	}

	private void updateWetter() {
		// Heute updaten

		CurrentConditionBean currentBean = wetter.currentBean;

		// Image
		ImageView imageHeute = (ImageView) findViewById(R.id.wetterdatenimageHeute);
		imageHeute.setImageBitmap(makeBitmap(currentBean.getIcon()));

		TextView textViewWannHeute = (TextView) findViewById(R.id.wetterdatenWannHeute);
		// SimpleDateFormat sf = new SimpleDateFormat("HH:MM:ss");
		// String formatedTime =
		// sf.format(GregorianCalendar.getInstance().getTime());
		textViewWannHeute.setText("Aktuell");

		TextView textViewConditionHeute = (TextView) findViewById(R.id.wetterdatenConditionHeute);
		textViewConditionHeute.setText(currentBean.getCondition());

		TextView textViewTemperaturHeute = (TextView) findViewById(R.id.wetterdatenTemperaturHeute);
		textViewTemperaturHeute.setText(currentBean.getTemp_c() + " °C");

		TextView textViewHumidityHeute = (TextView) findViewById(R.id.wetterdatenHumidityHeute);
		textViewHumidityHeute.setText(currentBean.getHumidity());

		TextView textViewWindHeute = (TextView) findViewById(R.id.wetterdatenWindHeute);
		textViewWindHeute.setText(currentBean.getWind_condition());

		// Morgen updaten

		ForecastConditionBean foreCastConditions = wetter.foreCastConditions[0];

		// Image
		ImageView imageMorgen = (ImageView) findViewById(R.id.wetterdatenImageMorgen);
		imageMorgen.setImageBitmap(makeBitmap(foreCastConditions.getIcon()));

		TextView textViewWannMorgen = (TextView) findViewById(R.id.wetterdatenWannMorgen);
		textViewWannMorgen.setText(foreCastConditions.getDay_of_week());

		TextView textViewTemperaturMorgen = (TextView) findViewById(R.id.wetterdatenTemperaturMorgen);
		textViewTemperaturMorgen.setText(foreCastConditions.getLow_data() + "/"
				+ foreCastConditions.getHigh_data() + " °C");

		TextView textViewConditionMorgen = (TextView) findViewById(R.id.wetterdatenConditionMorgen);
		textViewConditionMorgen.setText(foreCastConditions.getCondition());

		// Übermorgen updaten

		foreCastConditions = wetter.foreCastConditions[1];

		// Image
		ImageView imageUebermorgen = (ImageView) findViewById(R.id.wetterdatenImageUebermorgen);
		imageUebermorgen.setImageBitmap(makeBitmap(foreCastConditions.getIcon()));

		TextView textViewWannUebermorgen = (TextView) findViewById(R.id.wetterdatenWannUebermorgen);
		textViewWannUebermorgen.setText(foreCastConditions.getDay_of_week());

		TextView textViewTemperaturUebermorgen = (TextView) findViewById(R.id.wetterdatenTemperaturUebermorgen);
		textViewTemperaturUebermorgen.setText(foreCastConditions.getLow_data() + "/"
				+ foreCastConditions.getHigh_data() + " °C");

		TextView textViewConditionUebermorgen = (TextView) findViewById(R.id.wetterdatenConditionUebermorgen);
		textViewConditionUebermorgen.setText(foreCastConditions.getCondition());

		// ÜberÜbermorgen updaten

		foreCastConditions = wetter.foreCastConditions[2];

		// Image
		ImageView imageUeberUebermorgen = (ImageView) findViewById(R.id.wetterdatenImageUeberUebermorgen);
		imageUeberUebermorgen.setImageBitmap(makeBitmap(foreCastConditions.getIcon()));

		TextView textViewWannUeberUebermorgen = (TextView) findViewById(R.id.wetterdatenWannUeberUebermorgen);
		textViewWannUeberUebermorgen.setText(foreCastConditions.getDay_of_week());

		TextView textViewTemperaturUeberUebermorgen = (TextView) findViewById(R.id.wetterdatenTemperaturUeberUebermorgen);
		textViewTemperaturUeberUebermorgen.setText(foreCastConditions.getLow_data() + "/"
				+ foreCastConditions.getHigh_data() + " °C");

		TextView textViewConditionUeberUebermorgen = (TextView) findViewById(R.id.wetterdatenConditionUeberUebermorgen);
		textViewConditionUeberUebermorgen.setText(foreCastConditions.getCondition());

		// Last-Day updaten

		foreCastConditions = wetter.foreCastConditions[3];

		// Image
		ImageView imageLast = (ImageView) findViewById(R.id.wetterdatenImageLast);
		imageLast.setImageBitmap(makeBitmap(foreCastConditions.getIcon()));

		TextView textViewWannLast = (TextView) findViewById(R.id.wetterdatenWannLast);
		textViewWannLast.setText(foreCastConditions.getDay_of_week());

		TextView textViewTemperaturLast = (TextView) findViewById(R.id.wetterdatenTemperaturLast);
		textViewTemperaturLast.setText(foreCastConditions.getLow_data() + "/"
				+ foreCastConditions.getHigh_data() + " °C");

		TextView textViewConditionLast = (TextView) findViewById(R.id.wetterdatenConditionLast);
		textViewConditionLast.setText(foreCastConditions.getCondition());
	}

	private Bitmap makeBitmap(String url) {
		InputSource inputSource;
		try {
			inputSource = new InputSource(new URL(url).openStream());
			return BitmapFactory.decodeStream(inputSource.getByteStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// A ProgressDialog object
	private ProgressDialog progressDialog;
	private boolean fehler = false;

	// To use the AsyncTask, it must be subclassed
	private class LoadViewTask extends AsyncTask<Void, Integer, Void> {

		// Before running code in separate thread
		@Override
		protected void onPreExecute() {
			// Create a new progress dialog
			progressDialog = new ProgressDialog(WetterActivity.this);
			// Set the progress dialog to display a horizontal progress bar
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// Set the dialog title to 'Loading...'
			progressDialog.setTitle("Loading...");
			// Set the dialog message to 'Loading application View, please
			// wait...'
			progressDialog.setMessage("Lade Wetterdaten, bitte warten...");
			// This dialog can't be canceled by pressing the back key
			progressDialog.setCancelable(false);
			// This dialog isn't indeterminate
			progressDialog.setIndeterminate(false);
			// The maximum number of items is 100
			progressDialog.setMax(100);
			// Set the current progress to zero
			progressDialog.setProgress(0);
			// Display the progress dialog
			progressDialog.show();
		}

		// The code to be executed in a background thread.
		@Override
		protected Void doInBackground(Void... params) {
			/*
			 * This is just a code that delays the thread execution 4 times,
			 * during 850 milliseconds and updates the current progress. This is
			 * where the code that is going to be executed on a background
			 * thread must be placed.
			 */
			try {
				// Get the current thread's token
				synchronized (this) {
					wetter = LadeWetterDaten.getInstance();
					wetter.refreshDaten();

					long anfang = GregorianCalendar.getInstance().getTimeInMillis();
					long jetzt = GregorianCalendar.getInstance().getTimeInMillis();

					while (LadeWetterDaten.fortschritt <= 6 && (jetzt < (anfang + 15000))) {
						// Wait 850 milliseconds
						this.wait(500);
						// Set the current progress.
						// This value is going to be passed to the
						// onProgressUpdate() method.
						publishProgress((LadeWetterDaten.fortschritt) * (100 / 6));
						jetzt = GregorianCalendar.getInstance().getTimeInMillis();
					}

					if (jetzt > (anfang + 10000) || !(wetter.isErrorFree())) {
						progressDialog.dismiss();
						fehler = true;
					}

					//System.out.println("Ende: " + wetter.fortschritt);

					// //Initialize an integer (that will act as a counter) to
					// zero
					// int counter = 0;
					// //While the counter is smaller than four
					// while(counter <= 4)
					// {
					// //Wait 850 milliseconds
					// this.wait(850);
					// //Increment the counter
					// counter++;
					// //Set the current progress.
					// //This value is going to be passed to the
					// onProgressUpdate() method.
					// publishProgress(counter*25);
					// }
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		// Update the progress
		@Override
		protected void onProgressUpdate(Integer... values) {
			// set the current progress of the progress dialog
			progressDialog.setProgress(values[0]);
		}

		// after executing the code in the thread
		@Override
		protected void onPostExecute(Void result) {
			// close the progress dialog
			progressDialog.dismiss();
			if (!fehler) {
				// initialize the View
				setContentView(R.layout.wetterdaten);
				updateWetter();
			} else {
				showFehler();
			}
		}
	}

    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	this.finish();
    }
	
}