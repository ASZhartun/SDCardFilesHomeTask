package com.samples.sdcard.readwritefiles;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

public class FileListAdapter extends ArrayAdapter<String> {
	
	Integer pos = -1;

	public FileListAdapter(Context context, int resource, ArrayList<String> filesList) {
		super(context, R.layout.open_files_list_fragment, filesList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String fileName = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.open_files_list_fragment, null);
		}
		TextView naming = (TextView) convertView.findViewById(R.id.open_file_name);
		naming.setText(fileName);

		RadioButton checkbox = (RadioButton) convertView.findViewById(R.id.open_file_checked);
		if (position == pos) {
			checkbox.setChecked(true);
		} else {
			checkbox.setChecked(false);
		}
		return convertView;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	

}
