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
			state = 2;
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
			
		} else if(state == 2){
			//Main menu
			glBegin(GL_TRIANGLES);
	        glColor3f(0.1f, 0.2f, 0.3f);
	        glVertex2f(0, 0);
	        glVertex2f(500, 0);
	        glVertex2f(0, 500);
	        glEnd();
		}
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
