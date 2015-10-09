package objects;
import interfaces.Observer;

import java.awt.Color;

import shapes.Box;

public class Wall extends Box implements Observer {

	private float deltaY = 0;
	private float deltaX = 0;

	public Wall(float posx, float posy, float width, float height, Color color) {
		super(posx, posy, width, height, color);
	}

	@Override
	public void update(double deltaTime) {
		setPosx(getPosx() + (float) (deltaX * deltaTime));
		setPosy(getPosy() + (float) (deltaY * deltaTime));
	}

}
