package main;

import java.util.ArrayList;

import interfaces.Collider;

public class CollisionDetection {
	private static ArrayList<Collider> colliders = new ArrayList<Collider>();

	public static void addCollider(Collider col) {
		colliders.add(col);
	}

	public static void removeCollider(Collider col) {
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
				if (col.collide(collider) != 0) {
					res.add(new Collision(collider, col.collide(collider)));
				}
			}
		}
		return res;
	}

}
