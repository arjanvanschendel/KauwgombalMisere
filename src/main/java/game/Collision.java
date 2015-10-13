package game;

import objects.GameObject;

/**
 * Class for collision.
 * 
 * @author Jasper
 *
 */
public class Collision {
	private GameObject col;
	private int side;

	/**
	 * 
	 * @param collider
	 *            the gameobject
	 * @param side
	 *            the side hit
	 */
	public Collision(GameObject collider, int side) {
		this.setCol(collider);
		this.setSide(side);
	}

	/**
	 * Get collider.
	 * 
	 * @return the col
	 */
	public GameObject getCol() {
		return col;
	}

	/**
	 * Set collider.
	 * 
	 * @param collider
	 *            the col
	 */
	public void setCol(GameObject collider) {
		this.col = collider;
	}

	/**
	 * Get side.
	 * 
	 * @return the side
	 */
	public int getSide() {
		return side;
	}

	/**
	 * Set side.
	 * 
	 * @param side
	 *            the side
	 */
	public void setSide(int side) {
		this.side = side;
	}
}
