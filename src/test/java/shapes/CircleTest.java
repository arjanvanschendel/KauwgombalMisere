package shapes;

import java.awt.Color;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import shapes.Circle;


public class CircleTest {

private Circle _circle;
private Circle _circle1;

@Before
public void Before(){
_circle = new Circle(0,400,25, Color.CYAN);
_circle1 = new Circle(0,400,25);
}

@Test
public void firstColorTest(){
	assertEquals(_circle.getColor(), Color.CYAN);
}

@Test
public void secondColorTest(){
	_circle.setColor(Color.GREEN);
	assertNotEquals(_circle.getColor(), Color.CYAN);
	assertEquals(_circle.getColor(), Color.GREEN);
}

@Test
public void radiusTest(){
	assertEquals((long)_circle.getRadius(), 25);
}

@Test
public void positionTest(){
	assertEquals((long)_circle.getPosx(), 0);
	assertEquals((long)_circle.getPosy(), 400);
}

@Test
public void noColorConstructor(){
	assertEquals(_circle1.getColor(), new Color(0,0,0));
}

@Test
public void equalsTest(){
	assertNotEquals(_circle1, _circle);
}
	
	
}
