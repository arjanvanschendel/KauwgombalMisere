package utillities;

/**
 * SpriteSheet class.
 * 
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
     *            texture to set
     * @param rows
     *            amount of rows
     * @param columns
     *            amount of columns
     */
    public SpriteSheet(Texture texture, int rows, int columns) {
	this.texture = texture;
	this.rows = rows;
	this.columns = columns;
	row = 1;
	column = 1;
    }

    /**
     * Go to next sprite.
     */
    public void nextSprite() {
	if (column != (columns)) {
	    column += 1;
	} else {
	    if (row != (rows)) {
		column = 1;
		row += 1;
	    } else {
		row = 1;
		column = 1;
	    }
	}
    }

    /**
     * Return render coordinates.
     * 
     * @return the coordinates
     * @param mirrored
     *            true if texture is mirrored
     */
    public float[] returnCoordinates(boolean mirrored) {
	float[] coordinates = new float[4];
	float frameWidth = getTexture().getWidth() / (float) getColumns();
	float frameHeight = getTexture().getHeight() / (float) getRows();
	coordinates[0] = ((getColumn() - 1) * frameWidth)
		/ (float) getTexture().getWidth();
	coordinates[1] = (getColumn() * frameWidth)
		/ (float) getTexture().getWidth();
	if (mirrored) {
	    coordinates[0] = 1 - coordinates[0];
	    coordinates[1] = 1 - coordinates[1];
	}
	coordinates[2] = ((getRow() - 1) * frameHeight)
		/ (float) getTexture().getHeight();
	coordinates[3] = (getRow() * frameHeight)
		/ (float) getTexture().getHeight();
	return coordinates;
    }

    /**
     * Get currently located row.
     * 
     * @return the row
     */
    public int getRow() {
	return row;
    }

    /**
     * Get currently located column.
     * 
     * @return the column
     */
    public int getColumn() {
	return column;
    }

    /**
     * Get amount of rows.
     * 
     * @return the rows
     */
    public int getRows() {
	return rows;
    }

    /**
     * Get amount of columns.
     * 
     * @return the columns
     */
    public int getColumns() {
	return columns;
    }

    /**
     * Get the texture.
     * 
     * @return the texture
     */
    public Texture getTexture() {
	return texture;
    }

    /**
     * Bind texture.
     */
    public void bind() {
	texture.bind();
    }

}
