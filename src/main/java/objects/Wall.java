package objects;
import java.awt.Color;
import shapes.Box;

public class Wall extends Box implements GameObject {

	private float deltaY = 0;
	private float deltaX = 0;

	public Wall(float posx, float posy, float width, float height, Color color) {
		super(posx, posy, width, height, color);
	}

	@Override
	public void update(double deltaTime) {
		posx += (float) (deltaX * deltaTime);
		posy += (float) (deltaY * deltaTime);
		super.update();
	}

}
