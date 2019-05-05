package events;

import java.util.*;

/**
 * Represents an event
 * @author José
 *
 */
public abstract class SimulationEvent implements Event{
	
	private float time;
	static float rho;
	static float eta;
	static float alfa;
	static float beta;
	static float delta;
	/**
	 * Default constructor, sets the Event's end time
	 * @param _time
	 */
	SimulationEvent(float _time)
	{
		this.time=_time;
	}
	/**
	 * Abstract method for event simulation
	 */
	public abstract SimulationEvent[] simulate();
	
	public static void setParams(float _alpha, float _beta, float _delta, float _rho, float _eta)
	{
		alfa = _alpha;
		beta = _beta;
		delta = _delta;
		rho = _rho;
		eta = _eta;
	}
	/**
	 * Returns the Event's end time
	 * @return
	 */
	public float getTime()
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
