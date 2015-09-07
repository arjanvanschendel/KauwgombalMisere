package main;

import java.awt.Color;

import Interfaces.UpdateAble;
import Shapes.Box;

/**
 * Projectile class
 * @author Luke
 *
 */
public class Projectile extends Box implements UpdateAble {

	private float speed = 500;
	
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
	public void update(double deltaTime) {
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
