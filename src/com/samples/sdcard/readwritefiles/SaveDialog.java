package com.samples.sdcard.readwritefiles;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class SaveDialog extends DialogFragment implements OnClickListener {

	EditText path;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getDialog().setTitle("Save document");
		View v = inflater.inflate(R.layout.savedialog, null);
		v.findViewById(R.id.save_confrim).setOnClickListener(this);
		v.findViewById(R.id.save_decline).setOnClickListener(this);
		path = (EditText) v.findViewById(R.id.edit_filename);
		return v;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save_confrim:
			try {
				FileActionsOperator currentActivity = (FileActionsOperator) getActivity();
				currentActivity.saveFile(path.getText().toString());
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		dismiss();
	}

	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
	}

	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
	}

}
