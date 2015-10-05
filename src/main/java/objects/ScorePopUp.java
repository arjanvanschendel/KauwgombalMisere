package objects;

import game.Launcher;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

public class ScorePopUp implements GameObject {
	private float _posx;
	private float _posy;
	private String score;
	private Color color;
	
	public float get_posx() {
		return _posx;
	}

	public void set_posx(float _posx) {
		this._posx = _posx;
	}

	public float get_posy() {
		return _posy;
	}

	public void set_posy(float _posy) {
		this._posy = _posy;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ScorePopUp(final float posx, final float posy, final float radius){
	_posx = posx;
	_posy = posy*-1;
	switch ((int) radius) {
	case 50:
		score = 20+ "";
		color = Color.red;
		break;
	case (50 / 2):
		score = 10+ "";
		color = Color.blue;
		break;
	case (50 / 4):
		score = 10+ "";
		color = Color.green;
		break;
	default:
		color = Color.green;
		break;
	}
	}

	@Override
	public void update(double deltaTime) {
		set_posy((float) (get_posy() -  120 * deltaTime));
	}

	@Override
	public void render() {
	GL11.glPushMatrix();
	GL11.glScaled(1, -1, 1);
	Launcher.getFont().drawString(get_posx(), get_posy(), score, getColor());
	GL11.glPopMatrix();
	
	}

}
