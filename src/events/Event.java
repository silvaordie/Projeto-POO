package events;

import java.util.*;

public abstract class Event {
	
	float time;
	
	Event(float _time)
	{
		this.time=_time;
	}
	
	public abstract void simulate();
	
	public float geTime()
	{
		return this.time;
	}
	
	public static float expRandom(float m)
	{
		Random random = new Random();
		float next = random.nextFloat();
		return (float)(-m*Math.log(1.0-next));
	}
}
