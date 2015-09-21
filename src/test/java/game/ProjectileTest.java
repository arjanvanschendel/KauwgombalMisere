package game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import objects.Ball;
import objects.GameObject;
import objects.Projectile;
import objects.Wall;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.GLContext;

/**
 * 
 * @author Luke
 *
 */
public class ProjectileTest {
    
    private Projectile projectile;
    private ArrayList<GameObject> objects;
    
    /**
     * 
     */
    @Before
    public void setup() {
	objects = new ArrayList<GameObject>();
	
    }
    
    /**
     * 
     */
    @Test
    public void updateEmptyCollisionsTest() {
	Projectile A = new Projectile(0, 0);
	A.update(0);
	assertFalse(A.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateNonEmptyCollisionsTest() {
	Projectile A = new Projectile(0, 0);
	Projectile B = new Projectile(10, 10);
	CollisionDetection.addCollider(B);
	A.update(0);
	assertFalse(A.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateBallCollisionsTest() {
	Projectile A = new Projectile(0, 0);
	Ball B = new Ball(0, 0, 1);
	CollisionDetection.addCollider(B);
	A.update(0);
	assertTrue(A.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateBoxCollisionsTest() {
	Projectile A = new Projectile(0, 0);
	Wall B = new Wall(0, 0, 1, 1, null);
	CollisionDetection.addCollider(B);
	A.update(0);
	assertFalse(A.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateCeilingCollisionsTest() {
	Projectile A = new Projectile(0, 0);
	Wall B = new Wall(0, 1, 1, 10, null);
	A.setHeight(1.1f);
	CollisionDetection.addCollider(B);
	A.update(0);
	assertFalse(A.getHitBall());
    }
    
}
