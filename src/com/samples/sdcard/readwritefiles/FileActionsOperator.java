package com.samples.sdcard.readwritefiles;

import java.util.ArrayList;

public interface FileActionsOperator {
	public void saveFile(String filename);
	public void openFile(String filename);
	public ArrayList<String> getFilesListFromCurrentDirectory();
}