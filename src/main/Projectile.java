package main;

import java.awt.Color;
import java.util.ArrayList;

import Interfaces.UpdateAble;
import Shapes.Box;

/**
 * Projectile class
 * @author Luke
 *
 */
public class Projectile extends Box implements UpdateAble {

	private float speed = 500;
	private boolean hitBall = false;
	
	/**
	 * 
	 * @param posx
	 * @param posy
	 */
	public Projectile(float posx, float posy) {
		super(posx, posy, 10, 50, new Color(205, 205, 205));
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public void update( double deltaTime ) {
		

		ArrayList<Collision> collisions = CollisionDetection.collision(this);
		Ball ball = null;
		if(!collisions.isEmpty()){
			for(Collision collision : collisions){
				if (collision.getCol() instanceof Box && collision.getSide() == 3){
					Level.setProjectile(null);					
				}else if (collision.getCol() instanceof Ball){
					ball = (Ball)collision.getCol();
					hitBall = true;
				}
			}
		}
		
		if(hitBall){
			ball.hit();
			Level.setProjectile(null);
		}
		
		height += speed * deltaTime;
		super.update();
		
	}

	/**
	 * 
	 */
	@Override
	public void render() {
		super.render();
		// TODO Auto-generated method stub
		
	}

}
