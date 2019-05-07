package events;

import java.util.*;

/**
 * The base of the solutions' events
 * @author José
 *
 */
public abstract class SimulationEvent implements Event{
	/**
	 * Events duration
	 */
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
	
	/**
	 * Sets some event parameters
	 * @param _alpha Alpha parameter
	 * @param _beta Beta parameter
	 * @param _delta Delta parameter
	 * @param _rho Rho parameter
	 * @param _eta Eta parameter
	 */
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
	 * Returns the event's duration based on it's average duration
	 * @param m Average duration
	 * @return Event's duration
	 */
	public static float expRandom(float m)
	{
		Random random = new Random();
		float next = random.nextFloat();
		return (float)(-m*Math.log(1.0-next));
	}	
}
