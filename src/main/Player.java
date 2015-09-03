package main;

import static org.lwjgl.glfw.GLFW.*;

import java.awt.Color;

import Interfaces.Collider;
import Interfaces.RenderAble;
import Interfaces.UpdateAble;
import Shapes.Box;

public class Player extends Box implements RenderAble,UpdateAble  {
	private float deltaX = 0;
	private float deltaY = 0;
	
	public Player(float posx, float posy) {
		super(posx, posy, 50, 100, new Color(1,1,1));
	}
	
	public void update(double deltaTime){
		//First handle inputs
		handleInputs(deltaTime);
		posx +=deltaX;
		posy +=deltaY;
		super.update();
	}
	
	public void render() {
		super.render();
	}
	
	private void handleInputs(double deltaTime){
		
		if(Keyboard.isKeyDown(GLFW_KEY_LEFT) || Keyboard.isKeyDown(GLFW_KEY_A)){
			walkLeft(deltaTime);
		}else if(Keyboard.isKeyDown(GLFW_KEY_RIGHT) || Keyboard.isKeyDown(GLFW_KEY_D)){
			walkRight(deltaTime);
		} else{
			walkStop(deltaTime);
		}

		if(Keyboard.isKeyDown(GLFW_KEY_SPACE)){
			shoot();
		}

		

	}

	private void shoot() {
		// TODO Auto-generated method stub
		
	}

	private void walkRight(double deltaTime) {
		deltaX += 30*deltaTime;
		if(deltaX > 5){
			deltaX =(float) (5);
		}
	}

	public void walkLeft(double deltaTime) {
		
		deltaX -= 30*deltaTime;
		if(deltaX < -5){
			deltaX =(float) (-5);
		}
	}

	public void walkStop(double deltaTime) {
		if (deltaX < 0){
			deltaX += 30 * deltaTime;
			if(deltaX > 0){
				deltaX = 0;
			}
		}else if (deltaX > 0){
			deltaX -= 30 * deltaTime;
			if(deltaX < 0){
				deltaX = 0;
			}
		}
	}
}
