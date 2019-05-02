package events;

import java.util.*;

/**
 * Represents an event
 * @author José
 *
 */
public abstract class Event {
	
	float time;
	/**
	 * Default constructor, sets the Event's end time
	 * @param _time
	 */
	Event(float _time)
	{
		this.time=_time;
	}
	/**
	 * Abstract method for event simulation
	 */
	public abstract void simulate();
	
	/**
	 * Returns the Event's end time
	 * @return
	 */
	public float geTime()
	{
		return this.time;
	}
	/**
	 * voltar a este ponto mais tarde
	 * @param m Mean value
	 * @return 
	 */
	public static float expRandom(float m)
	{
		Random random = new Random();
		float next = random.nextFloat();
		return (float)(-m*Math.log(1.0-next));
	}
}
