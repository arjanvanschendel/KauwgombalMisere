package game;

import java.util.ArrayList;

import objects.Ball;
import objects.GameObject;
import shapes.Box;
import shapes.Point;

/**
 * Class that determines collision.
 * 
 * @author Jasper
 *
 */
public final class CollisionDetection {

	private static ArrayList<GameObject> colliders = new ArrayList<GameObject>();

	private CollisionDetection() {

	}

	/**
	 * Add object to colliders.
	 * 
	 * @param object
	 *            object to add
	 */
	public static void addCollider(GameObject object) {
		colliders.add(object);
	}

	/**
	 * Remove object from colliders.
	 * 
	 * @param col
	 *            object to remove.
	 */
	public static void removeCollider(Object col) {
		colliders.remove(col);
	}

	/**
	 * Clear colliders.
	 */
	public static void clear() {
		colliders.clear();
	}

	/**
	 * 
	 * @return the colliders
	 */
	public static ArrayList<GameObject> getColliders() {
		return colliders;
	}

	/**
	 * 
	 * @param colliders
	 *            colliders to set
	 */
	public static void setColliders(ArrayList<GameObject> colliders) {
		CollisionDetection.colliders = colliders;
	}

	/**
	 * Determine if there is collision.
	 * 
	 * @param col
	 *            collider to check
	 * @return list of collided objects
	 */
	public static ArrayList<Collision> collision(GameObject col) {
		ArrayList<Collision> res = new ArrayList<Collision>();
		if (col instanceof Ball) {

			for (GameObject collider : colliders) {
				if (collider != null && !collider.equals(col)) {
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
				if (collider != null && !collider.equals(col)) {
					if (collider instanceof Ball) {
						int side = collideBoxBall((Ball) collider, (Box) col);
						if (side != 0) {
							switch (side) {
								case 1 : side = 3; break;
								case 2 : side = 4; break;
								case 3 : side = 1; break;
								case 4 : side = 2; break;
							default:
								break;
							}
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

	/**
	 * 
	 * Calculates the side which side box B collides with box A.
	 * 
	 * 
	 * @param a
	 *            box
	 * @param b
	 *            box
	 * @return side
	 */
	private static int collideBoxBox(final Box a, final Box b) {

		int side = 0;

		float aX1 = a.getPosX();
		float aX2 = a.getPosX() + a.getWidth();
		float aY1 = a.getPosY();
		float aY2 = a.getPosY() + a.getHeight();

		float bX1 = b.getPosX();
		float bX2 = b.getPosX() + b.getWidth();
		float bY1 = b.getPosY();
		float bY2 = b.getPosY() + b.getHeight();

		if (aX1 <= bX2 && aX2 >= bX1 && aY1 <= bY2 && aY2 >= bY1) {
			float[] distances = new float[4];

			distances[0] = Math.abs(aY1 - bY2); // Hit ceiling
			distances[1] = Math.abs(aX1 - bX2); // Hit left of B
			distances[2] = Math.abs(bY1 - aY2); // Hit floor
			distances[3] = Math.abs(bX1 - aX2); // Hit Right of B

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

	/**
	 * 
	 * Calculates the side which side box B collides with ball A.
	 * 
	 * 
	 * @param a
	 *            ball
	 * @param b
	 *            box
	 * @return side
	 */
	private static int collideBoxBall(final Ball a, final Box b) {

		int side = 0;

		Point[] corners = b.getCorners();
		if (lineCircleIntersect(corners[0], corners[1], a.getPosX(),
				a.getPosY(), a.getRadius())) {
			side = 3;
		} else if (lineCircleIntersect(corners[1], corners[2], a.getPosX(),
				a.getPosY(), a.getRadius())) {
			side = 2;
		} else if (lineCircleIntersect(corners[2], corners[3], a.getPosX(),
				a.getPosY(), a.getRadius())) {
			side = 1;
		} else if (lineCircleIntersect(corners[3], corners[0], a.getPosX(),
				a.getPosY(), a.getRadius())) {
			side = 4;
		}
		return side;
	}

	/**
	 * Determine intersection between line and circle.
	 * 
	 * @param start
	 *            start point
	 * @param end
	 *            end point
	 * @param posx
	 *            x position
	 * @param posy
	 *            y position
	 * @param radius
	 *            radius circle
	 * @return true if intersect
	 */
	public static boolean lineCircleIntersect(Point start, Point end,
			float posx, float posy, float radius) {
		Point d = new Point(end.getX() - start.getX(), end.getY()
				- start.getY());
		Point f = new Point(start.getX() - posx, start.getY() - posy);

		float a = d.dot(d);
		float b = 2 * f.dot(d);
		float c = f.dot(f) - radius * radius;

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
