package game;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import objects.GameObject;

public class MainMenu {
	
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	/**
	 * update: update the level-object's state.
	 * 
	 * @param deltaTime
	 */
	public final void update(double deltaTime) {
		//for (GameObject update : objects) {
		//	update.update(deltaTime);
		//}
	}

	/**
	 * render: render the level-object's graphics.
	 */
	public final void render() {
		Launcher.font.drawString(-50, -100, "SAMPLE TEXT" , Color.white);
		
		//for (GameObject render : objects) {
		//	render.render();
		//}
	}
}
