package shapes;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import shapes.Point;

/**
 * @author Bart
 *
 */
public class PointTest {


Point _point; 
Point _point1;

@Before

public void before(){
	_point = new Point(-200,150);
	_point1 = new Point(-400,-150);
}

@Test
public void dotProductTest(){
	assertEquals( (long)_point.dot(_point1), 57500);
}
}
