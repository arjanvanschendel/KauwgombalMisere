package shapes;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import utillities.Texture;

/**
 * An extension of a box, which allows it to show a texture instead of just a
 * color.
 * 
 * @author Arjan
 *
 */
public class TextureBox extends Box {

	/**
	 * The texture the box should show.
	 */
	private Texture texture;

	/**
	 * Constructor for a texturebox which does not hold a texture yet.
	 * 
	 * @param pos
	 *            the starting position of the box in Point format
	 * @param width
	 *            The width of the box
	 * @param height
	 *            The height of the box
	 * @param color
	 *            The base color of the box
	 */
	public TextureBox(final Point pos, final float width, final float height,
			final Color color) {
		super(pos, width, height, color);
		texture = null;
	}

	/**
	 * Constructor for a texturebox which does not hold a texture yet.
	 * 
	 * @param pos
	 *            the starting position of the box in Point format
	 * @param width
	 *            The width of the box
	 * @param height
	 *            The height of the box
	 * @param color
	 *            The base color of the box
	 * @param txt
	 *            The texture that the box should show
	 */
	public TextureBox(final Point pos, final float width, final float height,
			final Color color, final Texture txt) {
		super(pos, width, height, color);
		texture = txt;
	}

	/**
	 * Constructor for a texturebox which does not hold a texture yet.
	 * 
	 * @param pos
	 *            the starting position of the box in Point format
	 * @param width
	 *            The width of the box
	 * @param height
	 *            The height of the box
	 * @param color
	 *            The base color of the box
	 * @param txtloc
	 *            The location where the texture the box should show is located
	 *            at
	 */
	public TextureBox(final Point pos, final float width, final float height,
			final Color color, final String txtloc) {
		super(pos, width, height, color);
		texture = new Texture(txtloc, GL11.GL_NEAREST, GL11.GL_CLAMP);
	}

	@Override
	public void render() {
		if (texture != null) {
			texture.bind();
		}

		glBegin(GL11.GL_QUADS);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		if (texture != null) {
			glTexCoord2f(0, 1);
		}
		glVertex2f(getCorners()[0].getX(), getCorners()[0].getY());
		if (texture != null) {
			glTexCoord2f(1, 1);
		}
		glVertex2f(getCorners()[1].getX(), getCorners()[1].getY());
		if (texture != null) {
			glTexCoord2f(1, 0);
		}
		glVertex2f(getCorners()[2].getX(), getCorners()[2].getY());
		if (texture != null) {
			glTexCoord2f(0, 0);
		}
		glVertex2f(getCorners()[3].getX(), getCorners()[3].getY());
		glEnd();
		if (texture != null) {
			Texture.disable();
		}
	}

	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @param texture
	 *            the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

}
