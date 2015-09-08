package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;



public class Game {
	private int state;
	private Level lvl;
    
	
	public Game(){
		//loadTextures();
		loadLevel("levels/level1.lvl");
	}
	
	private void loadTextures() {
		try {
			TextureLoader.getTexture("PNG", new FileInputStream(new File("res/image.png")));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void loadLevel(String location){
		lvl = new Level(location);
	}
	
	public void update(double deltaTime){
		if(Keyboard.isKeyReleased(GLFW_KEY_ESCAPE)){
			if(state == 0){
				state = 1;
			} else if (state == 1){
				state = 0;
			}
		}
		if(Keyboard.isKeyReleased(GLFW_KEY_SPACE)){
			if(state == 2){
				state = 0;
			}
		}

		switch(state){
		case(0):
			//Playing
			lvl.update(deltaTime);break;
		case(1):
			//Paused
			break;
		
		case(2):
			//Main Menu
			//mm.update(deltaTime);
			break;
		default:
			System.out.println("INVALID STATE: "+state+". (Game.update method)");System.exit(-1);
		
		
		}
	}
	
	public void render(){
		switch(state){
			case(0):
				//game
				lvl.render();
				break;
			case(1):
				//paused
				lvl.render();
				break;
			case(2):
				//Main Menu
				mm.render();
				break;
			default:
				System.out.println("INVALID STATE: "+state+". (Game.render method)");System.exit(-1);
		}
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
