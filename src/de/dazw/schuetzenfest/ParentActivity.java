package de.dazw.schuetzenfest;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ParentActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.xml.menu, menu);
		return true;
		// menu.add(0, Menu.FIRST, Menu.NONE, "Beenden" );
		// return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		Toast.makeText(ParentActivity.this, item.getTitle(), 1000).show();
		switch (item.getItemId()) {
		case R.id.menu_beenden:
			this.finish();
			break;
		case R.id.menu_veranstaltungen:
			this.finish();
			break;
		default:
			break;

		// case R.id.
		}
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }).start();
		//
		return super.onOptionsItemSelected(item);
	}

}
