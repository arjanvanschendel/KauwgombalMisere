package game;

import interfaces.Collider;

/**
 * 
 * JAPSER
 *
 */
public class Collision {
	private Collider col;
	private int side;

	public Collision(Collider col, int side) {
		this.setCol(col);
		this.setSide(side);
	}

	public Collider getCol() {
		return col;
	}

	public void setCol(Collider col) {
		this.col = col;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
}