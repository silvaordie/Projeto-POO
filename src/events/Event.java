package events;

public abstract class Event {
	
	float time;
	
	Event(float _time)
	{
		this.time=_time;
	}
	
	public abstract void simulate();
}
