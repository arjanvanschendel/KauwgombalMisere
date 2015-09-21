package shapes;

import java.awt.Color;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import shapes.Box;

/**
 * @author Bart
 *
 */

public class BoxTest {

Box _box;
Point _point = new Point(-460, 1900);
Point _point1 = new Point(-499, 1999);
Point _point2 = new Point(-501, 2001);

@Before
public void Before(){
_box = new Box(-500,0,50,2000,Color.BLACK);	
}

@Test
public void posTest(){
	assertEquals((long)_box.getHeight(), 2000);
	assertEquals((long)_box.getWidth(), 50);
	assertEquals((long)_box.getPosx(), -500);
	assertEquals((long)_box.getPosy(), 0);
	_box.setPosx(-400);
	_box.setPosy(-100);
	assertEquals((long)_box.getPosx(), -400);
	assertEquals((long)_box.getPosy(), -100);
}

@Test
public void cornersTest(){
	assertEquals((long)_box.getCorners()[0].getX(), -500);
	assertEquals((long)_box.getCorners()[2].getY(), 2000);
	assertEquals((long)_box.getCorners()[3].getX(), -500);
}

@Test
public void anotherCornersTest(){
	_box.setHeight(1000);
	_box.setWidth(200);
	assertEquals((long)_box.getCorners()[2].getX(), -300);
	assertEquals((long)_box.getCorners()[2].getY(), 1000);
}

@Test
public void colorTest(){
	assertEquals(_box.getColor(), Color.BLACK );
	_box.setColor(Color.RED);
	assertEquals(_box.getColor(), Color.RED );
}

@Test
public void pointInShapeTest(){
	assertTrue(_box.pointInShape(_point));
	assertTrue(_box.pointInShape(_point1));
	assertFalse(_box.pointInShape(_point2));
}
}
