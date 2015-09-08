package main;

import interfaces.RenderAble;
import interfaces.UpdateAble;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import shapes.Box;

/**
 * Level Class: an object of this class represents a level in the game, containing all objects in the level.
 * 
 *
 */
public class Level {
	private String loc;
	private static ArrayList<RenderAble> renderAbles = new ArrayList<RenderAble>();
	private static ArrayList<UpdateAble> updateAbles = new ArrayList<UpdateAble>();

	private Player player;
	
	/**
	 * Gravity in this level.
	 */
	private static float gravity;
	private static Projectile pro;

	/**
	 * Level: constructor.
	 * @param location
	 */
	public Level(String location) {

		clear();

		loc = location;
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(location));
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
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
						gravity = Float.parseFloat(para[0]);
					}else if (type.equals("box")) {
						addBox(para);
					} else if (type.equals("ball")) {
						addBall(para);
					} else if (type.equals("player")) {
						player = new Player(Float.parseFloat(para[0]),
								Float.parseFloat(para[1]));
						renderAbles.add(player);
						CollisionDetection.addCollider(player);
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static boolean levelComplete(){
		for( UpdateAble temp : updateAbles){
			if(temp instanceof Ball)return false;
		}
		return true;
	}

	/**
	 * clear: clear the static lists and objects.
	 */
	public static void clear() {
		renderAbles.clear();
		updateAbles.clear();
		pro = null;

		CollisionDetection.clear();

	}
	
	public static void remove(RenderAble object) {
		if (renderAbles.contains(object)) {
			renderAbles.remove(object);
		}
		if (updateAbles.contains(object)) {
			updateAbles.remove(object);
		}
		CollisionDetection.removeCollider(object);
	}

	public static void addProjectile(Projectile projectile) {
		
		if (pro == null) {
			pro = projectile;
		}
		
	}

	public static void setProjectile(Projectile projectile) {
		
		pro = projectile;
		
	}

	/**
	 * addBox: add a box to the level.
	 * @param para
	 */
	private void addBox(String[] para) {
		Box box = new Box(Float.parseFloat(para[0]), Float.parseFloat(para[1]),
				Float.parseFloat(para[2]), Float.parseFloat(para[3]),
				new Color(Float.parseFloat(para[4]), Float.parseFloat(para[5]),
						Float.parseFloat(para[6])));
		renderAbles.add(box);
		CollisionDetection.addCollider(box);
	}

	/**
	 * addBall: add a ball to the level.
	 * @param para
	 */
	private void addBall(String[] para) {
		Ball ball = new Ball(Float.parseFloat(para[0]),
				Float.parseFloat(para[1]), Float.parseFloat(para[2]),
				new Color(Float.parseFloat(para[3]), Float.parseFloat(para[4]),
						Float.parseFloat(para[5])));
		renderAbles.add(ball);
		updateAbles.add(ball);
		CollisionDetection.addCollider(ball);
	}

	public static void addBall(Ball ball) {
		renderAbles.add(ball);
		updateAbles.add(ball);
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
	 * @param deltaTime
	 */
	public void update(double deltaTime) {
		player.update(deltaTime);
		for (UpdateAble update : updateAbles) {
			update.update(deltaTime);
		}
		if(pro != null)
		pro.update(deltaTime);
	}

	/**
	 * render: render the level-object's graphics.
	 */
	public void render() {
		if(pro != null)
		pro.render();
		for (RenderAble render : renderAbles) {
			render.render();
		}
	}


}
