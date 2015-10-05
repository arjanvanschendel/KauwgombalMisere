package game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import objects.Ball;
import objects.GameObject;
import objects.Player;
import objects.Projectile;
import objects.Wall;
import utillities.Logger;
import utillities.ObjectGenerator;

/**
 * Level Class: an object of this class represents a level in the game,
 * containing all objects in the level.
 * 
 *
 */
public class Level {
    private String loc;
    private static ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private Player player;
    private String name = "";

    /**
     * Gravity in this level.
     */
    private static float gravity;
    private static Projectile pro;
    private static int score;

    /**
     * Level: constructor.
     * 
     * @param location
     */
    public Level(String location) {
	loc = location;
	loadLevel();

    }

    public static boolean levelComplete() {
	for (GameObject temp : objects) {
	    if (temp instanceof Ball)
		return false;
	}
	return true;
    }

    /**
     * clear: clear the static lists and objects.
     */
    public static void clear() {
	objects.clear();
	pro = null;

	CollisionDetection.clear();

    }

    public static void remove(GameObject object) {
	if (objects.contains(object)) {
	    objects.remove(object);
	}
	CollisionDetection.removeCollider(object);
    }

    public static void setProjectile(Projectile projectile) {

	if (pro == null) {
	    Logger.add("projectile shot");
	    pro = projectile;
	    try {
		if (!Game.sounds.isEmpty()) {
		    Game.sounds.get(2).play();
		}
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else if (projectile == null) {
	    pro = null;
	}

    }

    public static void addBall(Ball ball) {
	objects.add(ball);
	CollisionDetection.addCollider(ball);
    }

    /**
     * @return the gravity
     */
    public static float getGravity() {
	return gravity;
    }

    /**
     * update: update the level-object's state.
     * 
     * @param deltaTime
     */
    public void update(double deltaTime) {
	for (GameObject update : objects) {
	    update.update(deltaTime);
	}
	if (pro != null)
	    pro.update(deltaTime);

	if (!player.isAlive()) {
	    if (Game.getLives() > 1) {
		Game.decreaseLives();
		loadLevel();
	    } else {
		Game.setState(2);
		Game.setLives(3);
	    }
	}
    }

    /**
     * render: render the level-object's graphics.
     */
    public void render() {
	if (pro != null)
	    pro.render();
	for (GameObject render : objects) {
	    render.render();
	}
    }

    /**
     * Load level
     */
    public void loadLevel() {
	clear();
	InputStreamReader inputStreamReader;
	try {
	    inputStreamReader = new InputStreamReader(new FileInputStream(loc),
		    StandardCharsets.UTF_8);
	    BufferedReader bufferedReader = new BufferedReader(
		    inputStreamReader);
	    String line;
	    while ((line = bufferedReader.readLine()) != null) {

		int parameterStart = line.indexOf('(');
		int parameterEnd = line.indexOf(')');

		if (parameterStart != -1 && parameterEnd != -1) {
		    String type = line.substring(0, parameterStart);
		    String param = line.substring(parameterStart + 1,
			    parameterEnd);
		    String[] para = param.split("\\,");
		    if (type.equals("gravity")) {
			setGravity(Float.parseFloat(para[0]));
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
			name = para[0];
		    }
		}
	    }
	    bufferedReader.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * Get name of level.
     * 
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     * Set gravity of level.
     * 
     * @param g
     */
    public static void setGravity(float g) {
	Level.gravity = g;
    }

    public static int getScore() {
	return score;
    }

    public static void setScore(int s) {
	score = s;
    }

    public static Projectile getProjectile() {
	return pro;
    }

}
