package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadFile {
	private ArrayList<String>[] input;
	private boolean readFile ;
	public ReadFile() {
		input = new ArrayList[2];
		for (int i = 0; i < 2; i++) { 
            input[i] = new ArrayList<String>(); 
        }
		readFile = false;
	}
	
	public void setInputFromFile(String[] fileList) {
		try {
			for(int i=0; i<2; i++) {
				File Obj = new File(fileList[i]);
				Scanner Reader = new Scanner(Obj);
				while (Reader.hasNextLine()) {
					String data = Reader.nextLine();
					String[] boat = data.split(",");
					for (String a : boat) 
			            input[i].add(a);
					input[i].add("\n");
				}
				Reader.close();
			}
			readFile = true;
			
		}catch (FileNotFoundException e) {
			    readFile = false;
				System.out.println("This ID doesn't exist.");
			}
	}
	public ArrayList<String>[] getInputFromFile() {
		return input;
	}
	public boolean idExists() {
		return readFile;
	}
}
