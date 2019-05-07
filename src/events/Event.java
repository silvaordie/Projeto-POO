package events;

public interface Event {
	public abstract Event[] simulate();
	public abstract float getTime();
	public abstract int compareTo(Event event);
	public abstract boolean equals(Object obj);
}
