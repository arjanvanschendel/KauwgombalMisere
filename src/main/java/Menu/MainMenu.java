package Menu;

import game.Game;
import game.Launcher;

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
	ArrayList<Button> buttons  = new ArrayList<Button>();
	Button playBtn;
	
	public MainMenu(){
		playBtn = new Button(0, Launcher.getCAMHEIGHT()/2, 75, 25, java.awt.Color.white, "Play");
		buttons.add(playBtn);
	}
	
	/**
	 * update: update the level-object's state.
	 * @param deltaTime
	 */
	public final void update(final double deltaTime) {
		for (Button btn : buttons) {
			btn.update(deltaTime);
		}
		if(playBtn.isClicked()){
			Game.loadLevel(1);
			Game.setState(0);
		}
	}

	/**
	 * render: render the level-object's graphics.
	 */
	public final void render() {
		for (Button btn : buttons) {
			btn.render();
		}
		GL11.glScalef(1, -1, 1);
		TextureImpl.bindNone();
		font.drawString(0, -500, "Kauwgombal Misere" , Color.cyan);
		GL11.glScalef(1, -1, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
