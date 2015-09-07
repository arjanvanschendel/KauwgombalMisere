package main;

import static org.lwjgl.glfw.GLFW.*;

import java.awt.Color;
import java.util.ArrayList;

import Interfaces.Collider;
import Interfaces.RenderAble;
import Interfaces.UpdateAble;
import Shapes.Box;

/**
 * 
 * @author Luke
 *
 */
public class Player extends Box implements RenderAble, UpdateAble  {
	private float deltaX = 0;
	private float deltaY = 0;

	public Player(float posx, float posy) {
		super(posx, posy, 50, 100, new Color(1,1,1));
	}

	public void update(double deltaTime){
		//First handle inputs
		handleInputs(deltaTime);

		posx += deltaX;
		posy += deltaY;

		ArrayList<Collision> collisions = CollisionDetection.collision(this);
		if(!collisions.isEmpty()){
			for(Collision collision : collisions) {
				if (!(collision.getCol() instanceof Projectile)) {
					if(collision.getSide() == 4 ){
						posx = ((Box)collision.getCol()).getPosx() - width;
						deltaX = 0;
					} else if(collision.getSide() == 2 ) {
						posx = ((Box)collision.getCol()).getPosx() 
								+ ((Box)collision.getCol()).getWidth();
						deltaX = 0;
					}
				}
			}
		}
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
		} else {
			walkStop(deltaTime);
		}

		if(Keyboard.isKeyDown(GLFW_KEY_SPACE)){
			shoot();
		}



	}

	private void shoot() {
		Projectile p = new Projectile(this.posx + 0.5f * this.width, this.posy);
		Level.addProjectile(p);
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

	public void die() {
		height = 0;

	}
}
