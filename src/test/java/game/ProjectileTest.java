package game;

import static org.junit.Assert.assertTrue;
import objects.Projectile;

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
    
    @Before
    public void setup() {
	Launcher launcher = new Launcher();
	//projectile = new Projectile(0, 0);
    }
    
    @Test
    public void startTest() {

    }
    
    
}
