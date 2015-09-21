package objects;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import game.Level;

public class Score implements GameObject{

private int currentScore;
private float posX;
private float posY;
private Font awtFont = new Font("Times New Roman", Font.BOLD, 20);
private TrueTypeFont font = new TrueTypeFont(awtFont, true);

public Score(float posX, float posY, int s){
	
}

@Override
public void update(double deltaTime) {
currentScore = Level.getScore();
}

@Override
public void render() {
font.drawString(-50, -400, "10", Color.black);
	
}
}
