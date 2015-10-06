package utillities;

/**
 * SpriteSheet class
 * @author Luke
 *
 */
public class SpriteSheet {
    
    private Texture texture;
    private int rows;
    private int columns;
    private int row;
    private int column;
    
    /**
     * 
     * @param texture
     * @param width
     * @param height
     */
    public SpriteSheet(Texture texture, int width, int height) {
	this.texture = texture;
	this.rows = width;
	this.columns = height;
	row = 1;
	column = 1;
    }
    
    /**
     * 
     */
    public void nextSprite() {
	if (column != (columns)) {
	    column += 1;
	} else {
	    if (row != (rows)) {
		column = 1;
		row += 1;
	    } else  {
		row = 1;
		column = 1;
	    }
	}
    }
    
    /**
     * 
     * @return
     */
    public float[] returnCoordinates(boolean mirrored) {
	float [] coordinates = new float[4];
	float frameWidth = getTexture().getWidth() / (float) getColumns();
	float frameHeight = getTexture().getHeight() / (float) getRows();
	coordinates[0] = ((getColumn() - 1) * frameWidth) / (float) getTexture().getWidth();
	coordinates[1] = (getColumn() * frameWidth) / (float) getTexture().getWidth();
	if (mirrored) {
	    coordinates[0] = 1 - coordinates[0];    
	    coordinates[1] = 1 - coordinates[1];    
	}
	coordinates[2] = ((getRow() - 1) * frameHeight) / (float) getTexture().getHeight();
	coordinates[3] = (getRow() * frameHeight) / (float) getTexture().getHeight();
	return coordinates;
    }
    
    public int getRow() {
	return row;
    } 
    
    public int getColumn() {
	return column;
    }
    
    public int getRows() {
	return rows;
    } 
    
    public int getColumns() {
	return columns;
    }
    
    public Texture getTexture() {
	return texture;
    }
    
    public void bind() {
	texture.bind();
    }
    
    
}
