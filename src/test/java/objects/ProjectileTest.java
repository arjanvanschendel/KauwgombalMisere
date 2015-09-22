package objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.CollisionDetection;

import java.util.ArrayList;

import objects.Ball;
import objects.GameObject;
import objects.Projectile;
import objects.Wall;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Luke
 *
 */
public class ProjectileTest {
    
    /**
     * 
     */
    @Test
    public void updateEmptyCollisionsTest() {
	Projectile a = new Projectile(0, 0);
	a.update(0);
	assertFalse(a.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateNonEmptyCollisionsTest() {
	Projectile a = new Projectile(0, 0);
	Projectile b = new Projectile(10, 10);
	CollisionDetection.addCollider(b);
	a.update(0);
	assertFalse(a.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateBallCollisionsTest() {
	Projectile a = new Projectile(0, 0);
	Ball b = new Ball(0, 0, 1);
	CollisionDetection.addCollider(b);
	a.update(0);
	assertTrue(a.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateBoxCollisionsTest() {
	Projectile a = new Projectile(0, 0);
	Wall b = new Wall(0, 0, 1, 1, null);
	CollisionDetection.addCollider(b);
	a.update(0);
	assertFalse(a.getHitBall());
    }
    
    /**
     * 
     */
    @Test
    public void updateCeilingCollisionsTest() {
	Projectile a = new Projectile(0, 0);
	Wall b = new Wall(0, 1, 1, 10, null);
	a.setHeight(1.1f);
	CollisionDetection.addCollider(b);
	a.update(0);
	assertFalse(a.getHitBall());
    }
    
}
