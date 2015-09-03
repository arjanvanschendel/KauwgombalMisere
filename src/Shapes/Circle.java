package Shapes;

import java.awt.Color;

import Interfaces.RenderAble;
import static org.lwjgl.opengl.GL11.*;

public class Circle implements RenderAble{
	protected float posx;
	protected float posy;
	protected float radius;
	private Color color;
	
	public Circle(float posx, float posy,float radius, Color color){
		this.posx = posx;
		this.posy = posy;
		this.radius = radius;
		this.color = color;
	}
	
	public void render(){
		//Draw Circle
		glBegin(GL_POLYGON);
		glColor3f((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255);
			for(double i = 0; i < 2 * Math.PI; i += Math.PI / 12)
					glVertex2f((float)Math.cos(i) * radius + posx, (float)Math.sin(i) * radius + posy);
		glEnd();
	}
	
	@Override
	public boolean equals(Object that){
		
		if(that instanceof Circle){
			Circle circle2 = (Circle) that;
			if(circle2.getColor().equals(color) && circle2.getPosx() == posx && circle2.getPosy() == posy && circle2.getRadius() == radius){
				return true;
			}
		}
		
		return false;
	}

	//Line Circle intersect needed
	public boolean lineIntersect(Point start,Point end){
		Point d = new Point(end.getX() - start.getX(), end.getY()-start.getY());
		Point f = new Point(start.getX() - posx, start.getY() - posy);
		
		float a = d.Dot( d ) ;
		float b = 2*f.Dot( d ) ;
		float c = f.Dot( f ) - radius*radius ;

		float discriminant = b*b-4*a*c;
		if( discriminant >= 0 )
		{
		  discriminant = (float) Math.sqrt( discriminant );

		  float t1 = (-b - discriminant)/(2*a);
		  float t2 = (-b + discriminant)/(2*a);
		  if( t1 >= 0 && t1 <= 1 )
		  {
		    return true ;
		  }
		  if( t2 >= 0 && t2 <= 1 )
		  {
		    return true ;
		  }
		  return false ;
		}
		return false;
	}
	
	public boolean pointInShape(float pointX,float pointY){
		float x = Math.abs(pointX - posx);
		float y = Math.abs(pointY - posy);
		float distance = (float) Math.sqrt(x*x + y*y);
		return distance <= radius;
	}

	public float getPosx() {
		return posx;
	}

	public void setPosx(float posx) {
		this.posx = posx;
	}

	public float getPosy() {
		return posy;
	}

	public void setPosy(float posy) {
		this.posy = posy;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
