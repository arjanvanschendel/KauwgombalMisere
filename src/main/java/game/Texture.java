package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

/**
 * Class for texture
 * 
 * @author Luke
 *
 */
public class Texture {

    private int width;
    private int height;
    private int id;

    /**
     * 
     * @param src
     * @param filter
     * @param wrap
     */
    public Texture(String src, int filter, int wrap) {
	
	InputStream input = null; 
	try {
	    input = new FileInputStream(src);
	    PNGDecoder decoder = new PNGDecoder(input);
	    ByteBuffer buffer = ByteBuffer.allocateDirect(4
		    * decoder.getWidth() * decoder.getHeight());
	    decoder.decode(buffer, decoder.getWidth() * 4, Format.RGBA);
	    buffer.flip();
	    
	    width = decoder.getWidth();
	    height = decoder.getHeight();
	    id = GL11.glGenTextures();

	    bind();

	    GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D,
		    GL11.GL_TEXTURE_MAG_FILTER, filter);
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D,
		    GL11.GL_TEXTURE_MIN_FILTER, filter);
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
		    wrap);
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
		    wrap);
	    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA,
		    decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA,
		    GL11.GL_UNSIGNED_BYTE, buffer);

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if(input != null) {
		try {
		    input.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public int getWidth() {
	return width;
    }
    
    public int getHeight() {
	return height;
    }
    
    /**
     * Bind texture
     */
    public void bind() {
	GL11.glEnable(GL11.GL_TEXTURE_2D);
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
    }
    
    public static void disable() {
	GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

}
