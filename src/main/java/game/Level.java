package game;

import java.util.ArrayList;

import objects.Ball;
import objects.GameObject;
import objects.Player;
import objects.Projectile;
import objects.ScorePopUp;
import objects.Wall;
import powerups.PowerUp;
import utillities.Logger;
import utillities.ObjectGenerator;
import utillities.Sound;
import filehandling.FileEntry;
import filehandling.FileParser;

/**
 * Level Class: an object of this class represents a level in the game,
 * containing all objects in the level.
 * 
 * @author Luke, Jasper, Bart
 *
 */
public class Level {

	private static Game game = Game.getInstance();
	private String loc;
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private static ArrayList<GameObject> removeObjects = new ArrayList<GameObject>();
	private static ArrayList<GameObject> popUpObjects = new ArrayList<GameObject>();
	private Player player;
	private String name = "";
	private String music = "Derezzed";

	private static Projectile pro;

	/**
	 * Level: constructor.
	 * 
	 * @param location
	 *            location of level file
	 */
	public Level(String location) {
		loc = location;
		readLevel();

	}

	/**
	 * Determine if level is completed.
	 * 
	 * @return true if completed
	 */
	public static boolean levelComplete() {
		for (GameObject temp : objects) {
			if (temp instanceof Ball) {
				return false;
			}
		}
		return true;
	}

	/**
	 * clear: clear the static lists/objects and deactivate any powerup.
	 */
	public static void clear() {

		for (GameObject obj : objects) {
			if (obj instanceof PowerUp) {
				((PowerUp) obj).deactivate();
			}
		}
		popUpObjects.clear();
		objects.clear();
		pro = null;
		removeObjects.clear();
		CollisionDetection.clear();

	}

	/**
	 * Add object to remove list.
	 * 
	 * @param object
	 *            object to be removed
	 */
	public static void remove(GameObject object) {
		removeObjects.add(object);
	}

	/**
	 * Remove objects from remove list.
	 */
	private void remove() {
		for (GameObject object : removeObjects) {
			if (objects.contains(object)) {
				objects.remove(object);
			}
			CollisionDetection.removeCollider(object);
		}
		removeObjects.clear();
	}

	/**
	 * 
	 * @param projectile
	 *            projectile to set.
	 */
	public static void setProjectile(Projectile projectile) {

		if (pro == null) {
			Logger.add("projectile shot");
			pro = projectile;
			CollisionDetection.addCollider(pro);
			if (!game.getSoundFX().isEmpty()) {
				game.getSoundFX().get("arrowShoot").play();
			}
		} else if (projectile == null) {
			CollisionDetection.removeCollider(pro);
			pro = null;
		}

	}

	/**
	 * Add ball to level.
	 * 
	 * @param ball
	 *            ball to add
	 */
	public static void addBall(Ball ball) {
		objects.add(ball);
		CollisionDetection.addCollider(ball);
	}

	/**
	 * Add power up to level.
	 * 
	 * @param pu
	 *            power up to add
	 */
	public static void addPowerUp(PowerUp pu) {
		objects.add(pu);
		CollisionDetection.addCollider(pu);
	}

	/**
	 * update: update the level-object's state.
	 * 
	 * @param deltaTime
	 *            time between frames
	 */
	public void update(double deltaTime) {
		for (GameObject update : objects) {
			update.update(deltaTime);
		}
		for (GameObject popup : popUpObjects) {
			popup.update(deltaTime);
		}
		if (pro != null) {
			pro.update(deltaTime);
		}
		if (player == null || !player.isAlive()) {
			if (GameVariables.getLives() > 1) {
				game.decreaseLives();
				readLevel();
			} else {
				Logger.add("Game reset");
				game.setState(5);
			}
		}
		remove();
	}

	/**
	 * render: render the level-object's graphics.
	 */
	public void render() {
		if (pro != null) {
			pro.render();
		}
		for (GameObject render : objects) {
			render.render();
		}
		for (GameObject render : popUpObjects) {
			render.render();
		}
	}

	/**
	 * Read level file.
	 */
	public void readLevel() {
		clear();

		FileParser fp = new FileParser(loc);
		for (FileEntry entry : fp.getEntries()) {
			String type = entry.getName();
			ArrayList<String> para = entry.getParameters();
			if (type.equals("gravity")) {
				GameVariables.setGravity(Float.parseFloat(para.get(0)));
			} else if (type.equals("box")) {
				Wall wall = ObjectGenerator.genWall(para);
				objects.add(wall);
				CollisionDetection.addCollider(wall);
			} else if (type.equals("ball")) {
				Ball ball = ObjectGenerator.genBall(para);
				objects.add(ball);
				CollisionDetection.addCollider(ball);
			} else if (type.equals("player")) {
				player = ObjectGenerator.genPlayer(para);
				objects.add(0, player);
				CollisionDetection.addCollider(player);
			} else if (type.equals("name")) {
				name = para.get(0);
			} else if (type.equals("song")) {
				music = para.get(0);
			}
		}
	}

	/**
	 * Add pop up.
	 * 
	 * @param popUp
	 *            pop up to be added
	 */
	public static void addPopUp(ScorePopUp popUp) {
		if (popUpObjects.size() > 3) {
			for (int i = 0; i < popUpObjects.size() - 1; ++i) {
				popUpObjects.remove(0);
			}
		}
		popUpObjects.add(popUp);
	}

	/**
	 * Get name of level.
	 * 
	 * @return the name of level
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the projectile
	 */
	public static Projectile getProjectile() {
		return pro;
	}

	/**
	 * @return the popUpObjects
	 */
	public static final ArrayList<GameObject> getPopUpObjects() {
		return popUpObjects;
	}

	/**
	 * @param popUpObjects
	 *            the popUpObjects to set
	 */
	public static final void setPopUpObjects(ArrayList<GameObject> popUpObjects) {
		Level.popUpObjects = popUpObjects;
	}

	/**
	 * returns the levels song.
	 * 
	 * @return the levels song
	 */
	public Sound getLevelSong() {
		return game.getMusic().get(music);
	}

}
