package utillities;

import java.awt.Color;

import objects.Ball;
import objects.Player;
import objects.Wall;

public class ObjectGenerator {

	public static Player genPlayer(String[] args){
		Player res = new Player(Float.parseFloat(args[0]),
				Float.parseFloat(args[1]));
		return res;
	}
	
	public static Wall genWall(String[] args){
		Wall res = new Wall(Float.parseFloat(args[0]), Float.parseFloat(args[1]),
				Float.parseFloat(args[2]), Float.parseFloat(args[3]),
				new Color(Float.parseFloat(args[4]), Float.parseFloat(args[5]),
						Float.parseFloat(args[6])));
		return res;
	}
	
	public static Ball genBall(String[] args){
		Ball res = new Ball(Float.parseFloat(args[0]),
				Float.parseFloat(args[1]), Float.parseFloat(args[2]));
		return res;
	}
}
