package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoadController {
	@FXML 
	private TextField textId;
	
	@FXML
	private Button submit;
	public void initialize() {
	
	}
	public static String stringify(int arr[],String str) {
		for(int i=0; i<4; i++) {
			str+=Integer.toString(arr[i]);
		}
		str+="\n";
		return str;
	}
	
	public void chooseId(ActionEvent event) throws IOException{
		if(event.getSource() == submit) {
			String id = textId.getText();
			String file1 = "medialab/player_SCENARIO-"+id+".txt";
			String file2 = "medialab/enemy_SCENARIO-"+id+".txt";
			String file [] = new String[2];
			file[0] = file1;
			file[1] = file2;
			ReadFile f = new ReadFile();
			f.setInputFromFile(file);
			ArrayList<String>[] input = f.getInputFromFile();
			if(f.idExists()) {
				Table player = new Table();
				player.set(input[0]);
				Table enemy = new Table();
				enemy.set(input[1]);
				boolean t1[][] = player.getTable();
				boolean f1 = false;
				for(int i=0; i<10; i++) {
				  for(int j=0; j<10; j++) {
					  if(t1[i][j]) {
						  f1 = true;
						  break;
					  }
				  }
				  if(f1) {
					  break;
				  }
				}
				boolean t2[][] = enemy.getTable();
				boolean f2 = false;
				for(int i=0; i<10; i++) {
				  for(int j=0; j<10; j++) {
					  if(t2[i][j]) {
						  f2 = true;
						  break;
					  }
				  }
				  if(f2) {
					  break;
				  }
				}
				if(f1 && f2) {
					String yourFile = "medialab/game.txt";
					int[] playerCarrier = player.getCarrier();
					int[] playerBattleship = player.getBattleship();
					int[] playerCruiser = player.getCruiser();
					int[] playerSubmarine = player.getSubmarine();
					int[] playerDestroyer = player.getDestroyer();
					int[] enemyCarrier = enemy.getCarrier();
					int[] enemyBattleship = enemy.getBattleship();
					int[] enemyCruiser = enemy.getCruiser();
					int[] enemySubmarine = enemy.getSubmarine();
					int[] enemyDestroyer = enemy.getDestroyer();
					String yourContent = "";
					yourContent = stringify(playerCarrier,yourContent);
					yourContent = stringify(playerBattleship,yourContent);
					yourContent = stringify(playerCruiser,yourContent);
					yourContent = stringify(playerSubmarine,yourContent);
					yourContent = stringify(playerDestroyer,yourContent);
					yourContent = stringify(enemyCarrier,yourContent);
					yourContent = stringify(enemyBattleship,yourContent);
					yourContent = stringify(enemyCruiser,yourContent);
					yourContent = stringify(enemySubmarine,yourContent);
					yourContent = stringify(enemyDestroyer,yourContent);
					File tmpDir = new File(yourFile);
					boolean exists = tmpDir.exists();

					if(!exists) {
						FileOutputStream fos = new FileOutputStream(yourFile);
						fos.write(yourContent.getBytes());
						fos.flush();
						fos.close();
					}
			  }
			}
			
		}
	}
}
