package simulation;

/**
 * Defines the simulation behaviour
 * @author José
 *
 */
public interface SimulationInterface {
	/**
	 * Runs the simulation
	 * @param value simulation parameter
	 */
	public abstract void run(float value);
	/**
	 * Prints the simulation state
	 * @param time current time
	 * @param obs current observation
	 * @return current time
	 */
	public abstract float printSim(float time, int obs);
}
