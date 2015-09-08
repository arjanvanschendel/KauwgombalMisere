package main;

import Interfaces.RenderAble;
import Interfaces.UpdateAble;

public class MmButton implements RenderAble, UpdateAble{

private String txt;
int nextSt;
	
	public MmButton(String text, int nextState){
		txt = text;
		nextSt = nextState;
	}
	
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	
}
