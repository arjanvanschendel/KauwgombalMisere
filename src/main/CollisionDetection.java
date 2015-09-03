package main;

import java.util.ArrayList;

import Interfaces.Collider;

public class CollisionDetection {
	private static ArrayList<Collider> colliders = new ArrayList<Collider>();

	public static void addCollider(Collider col){
		colliders.add(col);
	}

	public static void removeCollider(Collider col){
		colliders.remove(col);
	}

	public static void clear(){
		colliders.clear();
	}

	public static ArrayList<Collider> getColliders() {
		return colliders;
	}

	public static void setColliders(ArrayList<Collider> colliders) {
		CollisionDetection.colliders = colliders;
	}

	public static ArrayList<Collider> collision(Collider col){
		ArrayList<Collider> res = new ArrayList<Collider>();
		for(Collider collider : colliders){
			if(!collider.equals(col)){
				if(col.collide(collider)!=0){
					res.add(collider);
				}
			}
		}
		return res;
	}

}
