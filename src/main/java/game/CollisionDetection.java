package game;


import java.util.ArrayList;

import objects.Ball;
import objects.GameObject;
import shapes.Box;
import shapes.Point;

/**
 * 
 * JAPSER
 *
 */
public class CollisionDetection {
	private static ArrayList<GameObject> colliders = new ArrayList<GameObject>();

	public static void addCollider(GameObject object) {
		colliders.add(object);
	}

	public static void removeCollider(Object col) {
		colliders.remove(col);
	}

	public static void clear() {
		colliders.clear();
	}

	public static ArrayList<GameObject> getColliders() {
		return colliders;
	}

	public static void setColliders(ArrayList<GameObject> colliders) {
		CollisionDetection.colliders = colliders;
	}

	public static ArrayList<Collision> collision(GameObject col) {
		ArrayList<Collision> res = new ArrayList<Collision>();
		if (col instanceof Ball) {

			for (GameObject collider : colliders) {
				if (!collider.equals(col)) {
					if (collider instanceof Box) {
						int side = collideBoxBall((Ball) col, (Box) collider);
						if (side != 0) {
							res.add(new Collision(collider, side));
						}
					}

				}
			}
		} else if (col instanceof Box) {

			for (GameObject collider : colliders) {
				if (!collider.equals(col)) {
					if (collider instanceof Ball) {
						int side = collideBoxBall((Ball) collider, (Box) col);
						if (side != 0) {
							res.add(new Collision(collider, side));
						}
					} else if (collider instanceof Box) {

						int side = collideBoxBox((Box) col, (Box) collider);
						if (side != 0) {
							res.add(new Collision(collider, side));
						}
					}

				}
			}
		}
		return res;
	}

	private static int collideBoxBox(Box A, Box B) {

		int side = 0;

		float AX1 = A.getPosx();
		float AX2 = A.getPosx() + A.getWidth();
		float AY1 = A.getPosy();
		float AY2 = A.getPosy() + A.getHeight();

		float BX1 = B.getPosx();
		float BX2 = B.getPosx() + B.getWidth();
		float BY1 = B.getPosy();
		float BY2 = B.getPosy() + B.getHeight();

		if (AX1 < BX2 && AX2 > BX1 && AY1 < BY2 && AY2 > BY1) {
			float[] distances = new float[4];

			distances[0] = Math.abs(AY1 - BY2); // Hit ceiling
			distances[1] = Math.abs(AX1 - BX2); // Hit left of wall
			distances[2] = Math.abs(BY1 - AY2); // Hit floor
			distances[3] = Math.abs(BX1 - AX2); // Hit Right of wall

			float smallest = distances[0];
			int index = 0;
			for (int i = 0; i < 4; i++) {
				float num = distances[i];
				if (num < smallest) {
					smallest = num;
					index = i;
				}
			}
			side = index + 1;
		}

		return side;
	}

	private static int collideBoxBall(Ball A, Box B) {

		int side = 0;

		Point[] corners = B.getCorners();
		if (lineCircleIntersect(corners[0], corners[1], A.getPosx(),
				A.getPosy(), A.getRadius())) {
			side = 1;
		} else if (lineCircleIntersect(corners[1], corners[2], A.getPosx(),
				A.getPosy(), A.getRadius())) {
			side = 2;
		} else if (lineCircleIntersect(corners[2], corners[3], A.getPosx(),
				A.getPosy(), A.getRadius())) {
			side = 3;
		} else if (lineCircleIntersect(corners[3], corners[0], A.getPosx(),
				A.getPosy(), A.getRadius())) {
			side = 4;
		}
		return side;
	}

	public static boolean lineCircleIntersect(Point start, Point end,
			float posx, float posy, float radius) {
		Point d = new Point(end.getX() - start.getX(), end.getY()
				- start.getY());
		Point f = new Point(start.getX() - posx, start.getY() - posy);

		float a = d.Dot(d);
		float b = 2 * f.Dot(d);
		float c = f.Dot(f) - radius * radius;

		float discriminant = b * b - 4 * a * c;
		if (discriminant >= 0) {
			discriminant = (float) Math.sqrt(discriminant);

			float t1 = (-b - discriminant) / (2 * a);
			float t2 = (-b + discriminant) / (2 * a);
			if (t1 >= 0 && t1 <= 1) {
				return true;
			}
			if (t2 >= 0 && t2 <= 1) {
				return true;
			}
			return false;
		}
		return false;
	}
}
