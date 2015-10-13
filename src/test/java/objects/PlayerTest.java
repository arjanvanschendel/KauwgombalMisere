package objects;

/**
 * Still has some weird Travis errors. Can be uncommented when fixed.
 * 
 * @author Jasper
 *
 */
public class PlayerTest {

	// private Player player;
	// private Ball ball;
	// private Wall wall;
	// private Wall wall2;
	// private Keyboard keyboard;
	//
	// @Before
	// public void setup(){
	// System.setProperty("org.lwjgl.librarypath", new
	// File("natives").getAbsolutePath());
	// CollisionDetection.clear();
	// Game.textures.add(mock(Texture.class));
	// Game.textures.add(mock(Texture.class));
	// Game.sounds.add(mock(Sound.class));
	// Game.sounds.add(mock(Sound.class));
	// Game.sounds.add(mock(Sound.class));
	// Game.sounds.add(mock(Sound.class));
	// Game.sounds.add(mock(Sound.class));
	//
	// player = new Player(250, 100);
	// ball = new Ball(250, 100, 20);
	// wall = new Wall(520, 100, 500, 500, null);
	// wall2 = new Wall(120, 100, 50, 500, null);
	// }
	//
	// @Test
	// public void playerWalkTest(){
	// keyboard = new Keyboard();
	// //Walk left
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 1, 0);
	// player.update(1);
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 0, 0);
	// //Walks 300 units to the left
	// assertEquals(player.getPosx(),-50,0);
	//
	// //Walk right
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 1, 0);
	// player.update(1);
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 0, 0);
	// //Walks 300 units to the right
	// assertEquals(player.getPosx(),250,0);
	//
	// //Walk stop
	// player.update(1);
	// //No keys are pressed player stands still
	// assertEquals(player.getPosx(),250,0);
	// }
	//
	//
	// @Test
	// public void playerHitBallTest(){
	// CollisionDetection.addCollider(ball);
	//
	// //Player is alive
	// assertTrue(player.isAlive());
	// player.update(1);
	// //Player died
	// assertFalse(player.isAlive());
	// }
	//
	//
	// @Test
	// public void playerHitWallTest(){
	// keyboard = new Keyboard();
	// //Walk right into wall
	// CollisionDetection.addCollider(wall);
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 1, 0);
	// player.update(1);
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_RIGHT, 0, 0, 0);
	// //Player tries to walk 300 units but stops after 210 because of the wall
	// assertEquals(player.getPosx(),460,0);
	//
	// CollisionDetection.addCollider(wall2);
	//
	// //Walk left into wall
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 1, 0);
	// player.update(1);
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_LEFT, 0, 0, 0);
	// //Player tries to walk 300 units but stops after 290 because of the wall
	// assertEquals(player.getPosx(),170,0);
	// }
	//
	// @Test
	// public void playerShootsProjectile(){
	// keyboard = new Keyboard();
	// //Before the player shoots there is no projectile in the level
	// assertEquals(Level.getProjectile(),null);
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_SPACE, 0, 1, 0);
	// player.update(1);
	// keyboard.invoke(Launcher.getWindow(), GLFW_KEY_SPACE, 0, 0, 0);
	//
	// //After the player shoots there is a projectile in the level at the
	// current position of the player
	// Projectile pro = Level.getProjectile();
	// assertNotEquals(pro,null);
	// assertEquals(pro.getPosx(),player.getPosx()+player.getWidth()/2,0);
	// assertEquals(pro.getPosy(),player.getPosy(),0);
	// }
	//
}
