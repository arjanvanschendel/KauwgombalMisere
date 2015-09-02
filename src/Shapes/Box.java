package Shapes;

import java.awt.Color;

import Interfaces.RenderAble;
import static org.lwjgl.opengl.GL11.*;

public class Box implements RenderAble{
	protected float posx;
	protected float posy;
	protected float width;
	protected float height;
	private Color color;
	public Box(float posx, float posy,float width,float height, Color color){
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void render(){
		glBegin(GL_QUADS);      
		glColor3f((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255);
		glVertex2f(posx, posy);    // x, y
		glVertex2f( posx+width, posy);  
		glVertex2f( posx+width, posy+height);  
		glVertex2f(posx, posy+height);  
		glEnd();
	}
}
