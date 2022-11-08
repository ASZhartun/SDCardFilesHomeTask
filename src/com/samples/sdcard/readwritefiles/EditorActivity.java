package com.samples.sdcard.readwritefiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import com.samples.sdcard.readwritefiles.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditorActivity extends Activity implements FileActionsOperator {
	private static final int IDM_NEW = 200;
	private static final int IDM_OPEN = 201;
	private static final int IDM_SAVE = 202;
	private static final int IDM_EXIT = 203;

	private static final String DIRECTORY_DOCUMENTS = "/docs";
	private static final String FILE_EXT = ".txt";

	private EditText editText;
	private String curFileName = "";
	private String dir;
	private int pos = 0;

	private SaveDialog saveDialog;
	
	private ListFilesDialog listFilesDialog;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		saveDialog = new SaveDialog();
		listFilesDialog = new ListFilesDialog();

		getActionBar();

		editText = (EditText) findViewById(R.id.edit);

		dir = Environment.getExternalStorageDirectory().toString() + DIRECTORY_DOCUMENTS;
		File folder = new File(dir);

		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_NEW, Menu.NONE, R.string.menu_new).setIcon(R.drawable.menu_new)
				.setAlphabeticShortcut('n').setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, R.string.menu_open).setIcon(R.drawable.menu_open)
				.setAlphabeticShortcut('o').setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		;
		menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, R.string.menu_save).setIcon(R.drawable.menu_save)
				.setAlphabeticShortcut('s').setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		;
		menu.add(Menu.NONE, IDM_EXIT, Menu.NONE, R.string.menu_exit).setIcon(R.drawable.menu_exit)
				.setAlphabeticShortcut('x').setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		;

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case IDM_NEW:
			curFileName = "";
			editText.setText("");
			this.setTitle(R.string.app_name);
			break;
		case IDM_OPEN:
			callOpenDialog();
			break;
		case IDM_SAVE:
			callSaveDialog();
			break;
		case IDM_EXIT:
			finish();
			break;
		default:
			return false;
		}
		return true;
	}

	private void callSaveDialog() {
		saveDialog.show(getFragmentManager(), "save");
	}

	private void callOpenDialog() {
		listFilesDialog.show(getFragmentManager(), "open");
	}

	public void saveFile(String fileName) {
		try {
			String root = Environment.getExternalStorageDirectory().toString() + DIRECTORY_DOCUMENTS;
			File myFile = new File(root, fileName);
			FileWriter writer = new FileWriter(myFile);
			writer.write(editText.getText().toString());
			writer.close();
			editText.setText("");
		} catch (FileNotFoundException e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openFile(String fileName) {
		try {
			File needed = new File(fileName);
			FileInputStream fis = new FileInputStream(needed);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buff = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String str = "";
			while((str=buff.readLine()) != null) {
				sb.append(str).append('\n');
			}
			editText.setText(sb.toString());
			Toast.makeText(this, sb.toString(), 3000).show();
			this.setTitle(fileName);
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), 3000).show();
		}
	}

	@Override
	public ArrayList<String> getFilesListFromCurrentDirectory() {
		ArrayList<String> items = new ArrayList<String>();
		File currentDir = new File(dir);
		File[] listFiles = currentDir.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			items.add(listFiles[i].toString());
		}
		return items;
	}
	
	

}