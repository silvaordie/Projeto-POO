package events;

public interface Event {
	public abstract Event[] simulate();
	public abstract float getTime();
}
