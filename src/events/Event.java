package events;

/**
 * Defines the behavior of an Event
 * @author José
 *
 */
public interface Event {
	/**
	 * Simualtes the event and returns the next ones to simulate
	 * @return Array of events to simulate
	 */
	public abstract Event[] simulate();
	/**
	 * Returns the event's final time instant
	 * @return Event's
	 */
	public abstract float getTime();
}
