package events;
import java.util.*;

public interface Event {
	public abstract Event[] simulate();
	public abstract float getTime();
}
