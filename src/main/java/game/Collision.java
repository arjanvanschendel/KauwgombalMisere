package game;

import interfaces.Observer;

/**
 * 
 * JAPSER
 *
 */
public class Collision {
	private Observer col;
	private int side;

	public Collision(Observer collider, int side) {
		this.setCol(collider);
		this.setSide(side);
	}

	public Observer getCol() {
		return col;
	}

	public void setCol(Observer collider) {
		this.col = collider;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
}
