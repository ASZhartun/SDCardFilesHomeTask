package com.samples.sdcard.readwritefiles;

import java.util.ArrayList;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListFilesDialog extends DialogFragment implements OnClickListener {

	ListView filesList;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getDialog().setTitle("Open document");
		View v = inflater.inflate(R.layout.open_dialog, null);
		v.findViewById(R.id.open_confrim).setOnClickListener(this);
		v.findViewById(R.id.open_decline).setOnClickListener(this);
		filesList = (ListView) v.findViewById(R.id.files_list);
		filesList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		FileActionsOperator currentActivity = (FileActionsOperator) getActivity();
		FileListAdapter myAdapter = new FileListAdapter(getActivity(), R.layout.open_files_list_fragment,
				currentActivity.getFilesListFromCurrentDirectory());
		filesList.setAdapter(myAdapter);
		
		filesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				FileListAdapter adapter = (FileListAdapter) parent.getAdapter();
				adapter.setPos(position);
				adapter.notifyDataSetChanged();
			}
			
		});

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.open_confrim:
			try {
				int pos = filesList.getCheckedItemPosition();
				String fileName = (String) filesList.getAdapter().getItem(pos);
				FileActionsOperator currentActivity = (FileActionsOperator) getActivity();
				currentActivity.openFile(fileName);
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
