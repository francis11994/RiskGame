
package model;

public class Armies {
	int totalArmy;
	int group;
	int individual;
public Armies(){
	totalArmy=0;
	group=0;
	individual=0;
}

public int getArmies(){
	return totalArmy;
}

public int getGroup(){
	return group;
}
public int getIndividual(){
	return individual;
}
public void addsoldier(int a){
	totalArmy+= a;
	calculateGroupAndIndividual();
}
private void calculateGroupAndIndividual(){
	group=totalArmy/10;
	individual= totalArmy%10;
	
}
}
