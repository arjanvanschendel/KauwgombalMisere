package game;

/**
 * Collider interface used in CollisionDetection to calculate collisions.
 * 
 * @author Jasper
 *
 */
public interface Collider {
	/**
	 * 
	 * Calculates if there is a collision between this object and 'col'.
	 * 
	 * @param col Test collision with col.
	 * @return 0 = no hit, 1 = hit top, 2 = hit right, 3 = hit bottom, 4 = hit
	 *         left.
	 */
	int collide(Collider col);

}
