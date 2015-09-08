package main;

import java.awt.Color;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;

import Interfaces.RenderAble;
import Interfaces.UpdateAble;
import Shapes.Box;

@SuppressWarnings("deprecation")
public class MainMenu {
	private String loc;
	private static ArrayList<RenderAble> renderAbles = new ArrayList<RenderAble>();
	private static ArrayList<UpdateAble> updateAbles = new ArrayList<UpdateAble>();
	
	private Player player;
	private TrueTypeFont font;
	
	
	public MainMenu() {
		// load a default java font
		java.awt.Font awtFont = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24);
	    font = new TrueTypeFont((java.awt.Font)awtFont, false);
		
	}
	
	public static void remove(RenderAble object) {
		if (renderAbles.contains(object)) {
			renderAbles.remove(object);
		}
		if (updateAbles.contains(object)) {
			updateAbles.remove(object);
		}
		CollisionDetection.removeCollider(object);
	}
	
	private void addButton(String str, int st) {
		MmButton button = new MmButton(str,st);
		renderAbles.add(button);
	}

	public void update(double deltaTime){
		player.update(deltaTime);
		for(UpdateAble update : updateAbles){
			update.update(deltaTime);
		}
	}

	public void render(){
		TextureDrawer michelangelo = new TextureDrawer("res/KMmain.png",200,200);
		
	}

}

