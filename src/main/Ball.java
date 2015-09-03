package main;

import java.awt.Color;

import Interfaces.Collider;
import Interfaces.RenderAble;
import Interfaces.UpdateAble;
import Shapes.Box;
import Shapes.Circle;
import Shapes.Point;

public class Ball extends Circle implements RenderAble,UpdateAble,Collider {
	private float deltaX = 2;
	private float deltaY = 0;

	public Ball(float posx, float posy,float radius, Color color) {
		super(posx, posy, radius, color);
	}

	public void update(double deltaTime){
		if(!CollisionDetection.collision(this).isEmpty()){
			deltaY = -deltaY;
		}
		deltaY -= deltaTime*9.81;
		posx +=deltaX;
		posy +=deltaY;
	}

	public void render() {
		super.render();
	}

	@Override
	public boolean equals(Object that){
		if(that instanceof Ball && super.equals(that)){
			Ball ball2 = (Ball) that;
			if(ball2.getDeltaX() == deltaX && ball2.getDeltaY() == deltaY){
				return true;
			}
		}
		return false;
	}

	//Getters and setters
	public float getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(float deltaX) {
		this.deltaX = deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}

	@Override
	public int collide(Collider col) {
		if(col instanceof Box){
			Box box = (Box)col;
			Point[] corners = box.getCorners();
			if(lineIntersect(corners[0],corners[1])){
				return 1;
			} else	if(lineIntersect(corners[1],corners[2])){
				return 2;
			}else if(lineIntersect(corners[2],corners[3])){
				return 3;
			}else if(lineIntersect(corners[3],corners[0])){
				return 4;
			}
		}
		return 0;
	}

}
