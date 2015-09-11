package game;

import objects.GameObject;

/**
 * 
 * JAPSER
 *
 */
public class Collision {
	private GameObject col;
	private int side;

	public Collision(GameObject collider, int side) {
		this.setCol(collider);
		this.setSide(side);
	}

	public GameObject getCol() {
		return col;
	}

	public void setCol(GameObject collider) {
		this.col = collider;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
}
