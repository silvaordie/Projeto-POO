package events;

import ants.*;

public class EvAntMove extends Event{
	
	private Ant ant;
	private boolean cycle = false;
	
	public EvAntMove(float _time, Ant _ant)
	{
		super(_time);
		this.ant = _ant;
	}
	
	public void simulate()
	{
		cycle = this.ant.moveAnt();
	}
	
	public boolean foundCycle()
	{
		return cycle;
	}
	
	public Ant getAnt()
	{
		return this.ant;
	}
}
