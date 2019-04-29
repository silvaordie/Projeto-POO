package events;

import ants.*;

public class EvAntMove extends Event{
	
	Ant ant;
	
	public EvAntMove(float _time, Ant _ant)
	{
		super(_time);
		this.ant = _ant;
	}
	
	public void simulate()
	{
		this.ant.moveAnt();
	}
}
