package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;



public class Game {
	private int state;
	private Level lvl;
    
	public Game(){
		loadLevel("levels/level1.lvl");
	}
	
	public void loadLevel(String location){
		lvl = new Level(location);
	}
	
	public void update(double deltaTime){
		if(Keyboard.isKeyDown(GLFW_KEY_ESCAPE)){
			if(state == 0){
				state = 1;
			} else if (state == 1){
				state = 0;
			}
		}

		if(state == 0){
			//Playing
			lvl.update(deltaTime);
		} else if(state == 1){
			//Pauzed
			
		} else if(state == 2){
			//Main menu
		}
	}
	
	public void render(){
		if(state == 0){
			//Playing
			lvl.render();
		} else if(state == 1){
			//Pauzed
			lvl.render();
			
		} else if(state == 2){
			//Main menu
		}
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
