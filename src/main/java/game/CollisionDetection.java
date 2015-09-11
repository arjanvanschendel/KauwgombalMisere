package game;

import interfaces.Collider;

import java.util.ArrayList;

/**
 * 
 * JAPSER
 *
 */
public class CollisionDetection {
	private static ArrayList<Collider> colliders = new ArrayList<Collider>();

	public static void addCollider(Collider col) {
		colliders.add(col);
	}

	public static void removeCollider(Object col) {
		colliders.remove(col);
	}

	public static void clear() {
		colliders.clear();
	}

	public static ArrayList<Collider> getColliders() {
		return colliders;
	}

	public static void setColliders(ArrayList<Collider> colliders) {
		CollisionDetection.colliders = colliders;
	}

	public static ArrayList<Collision> collision(Collider col) {
		ArrayList<Collision> res = new ArrayList<Collision>();
		for (Collider collider : colliders) {
			if (!collider.equals(col)) {
				if (collider instanceof Ball) {
					if (collider.collide(col) != 0) {
						res.add(new Collision(collider, collider.collide(col)));
					}
				} else if (col.collide(collider) != 0) {
					res.add(new Collision(collider, col.collide(collider)));
				}

			}
		}
		return res;
	}

}