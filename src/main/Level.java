package main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Interfaces.RenderAble;
import Shapes.Box;

public class Level {
	private String loc;
	private ArrayList<RenderAble> renderAbles = new ArrayList<RenderAble>();
	private Player player;
	public Level(String location) {
			
		loc = location;
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream (location) );
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {

				int parameterStart = line.indexOf('(');
				int parameterEnd = line.indexOf(')');

				if(parameterStart != -1 && parameterEnd != -1){
					String type = line.substring(0,parameterStart);
					String param = line.substring(parameterStart+1, parameterEnd);
					String[] para = param.split("\\,"); 
					if( type.equals("box")){
						addBox(para);				
					} else if( type.equals("player")){
						player = new Player(Float.parseFloat(para[0]),Float.parseFloat(para[1]));
						renderAbles.add(player);
						System.out.println("Player");
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addBox(String[] para) {
		renderAbles.add(new Box(Float.parseFloat(para[0]),Float.parseFloat(para[1]),Float.parseFloat(para[2]),Float.parseFloat(para[3]),new Color(Float.parseFloat(para[4]),Float.parseFloat(para[5]),Float.parseFloat(para[6]))));
	}

	public void update(double deltaTime){
		player.update(deltaTime);
	}

	public void render(){
		for(RenderAble render : renderAbles){
			render.render();
		}
	}

}
