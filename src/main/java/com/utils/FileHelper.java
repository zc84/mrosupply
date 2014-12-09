package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Sherstobitov
 *         QA LSE at intetics.apollo
 *         skype: dmitry_sherstobitov
 * 
 */
public class FileHelper {

	public String filename = null;
	FileWriter writableFile = null;
	File file;

	public FileHelper(String filename, boolean isNew) throws IOException {
		if(isNew)
            new File(filename).delete();
        this.file = new File(filename);
		this.filename = filename;
		getFile();
	}

	public FileHelper(File file) {
		this.file = file;
		this.filename = file.getAbsolutePath();
	}

	public void deleteFolder(File folder) {
		if (folder.isDirectory()) {
			File[] list = folder.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					File file = list[i];
					if (file.isDirectory()) {
						deleteFolder(file);
					}
					folder.delete();
				}
			}
			if (!folder.delete()) {
				System.out.println("can't delete folder : " + folder);
			}
		}
	}

	public void getFile() throws IOException {
		writableFile = new FileWriter(filename, true);
	}

	public void writeTofile(Object text) throws IOException {
		BufferedWriter out = new BufferedWriter(writableFile);
		out.write(text + "\n");
		out.close();
	}

	public List<String> readFile() throws IOException {
		String strLine;
		List<String> content = new ArrayList<String>();
		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		while ((strLine = br.readLine()) != null) {
			content.add(strLine);
		}
		fstream.close();
		in.close();
		br.close();
		return content;
	}

	public String readFileAsString() throws IOException {
		String strLine;
		String content = "";
		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		while ((strLine = br.readLine()) != null) {
			content += strLine;
		}
		fstream.close();
		in.close();
		br.close();
		return content;
	}

	public String getPath() {
		return new File(filename).getAbsolutePath();
	}

	public String getName() {
		return this.file.getName();
	}
}
