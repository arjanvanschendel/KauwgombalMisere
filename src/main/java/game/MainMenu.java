package game;

import java.awt.Font;
import java.util.ArrayList;

import objects.GameObject;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.TrueTypeFont;


public class MainMenu {
	

	
	Font awtFont = new Font("Comic Sans MS", Font.PLAIN, 48);
	TrueTypeFont font = new TrueTypeFont(awtFont, true);
	
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	/**
	 * update: update the level-object's state.
	 * @param deltaTime
	 */
	public final void update(final double deltaTime) {
		//for (GameObject update : objects) {
		//	update.update(deltaTime);
		//}
	}

	/**
	 * render: render the level-object's graphics.
	 */
	public final void render() {

		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		font.drawString(0, -500, "Kauwgombal Misere" , Color.cyan);
		
		//for (GameObject render : objects) {
		//	render.render();
		//}
	}
}
