package mfwallace.acserver;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Implementation of the AircraftService interface.
 */
public class AircraftServiceImpl implements AircraftService {

    /** Use Vector as it's thread-safe. */
    private final List<Aircraft> queue = new Vector<>(20);

    /**
     * Return the list of aircraft in the queue.
     *
     * @return the list of aircraft in the queue, or null if empty
     */
    @Override
    public List<Aircraft> list() {
        // TODO Return an iterator instead?
        return queue;
    }

    /**
     * Add the specified aircraft to the queue.
     *
     * This method is synchronized since multiple threads may add
     * aircraft concurrently, and we sort the queue on addition.
     *
     * @param aircraft the aircraft we want to add
     * @return the aircraft that was added
     */
    @Override
    public synchronized Aircraft enqueue(final Aircraft aircraft) {

        // Add the aircraft to the queue, sort it and then return the instance
        queue.add(aircraft);
        Collections.sort(queue);
        return aircraft;
    }

    /**
     * Remove the aircraft first in the sort order.
     *
     * @return the aircraft removed from the queue
     */
    @Override
    public Aircraft dequeue() {

        // If the list is empty, return null.  Else, remove the
        // first element in the list and return it.  We can
        // remove the first element because the aircraft are
        // sorted on insertion.
        return ((queue.isEmpty() ? null : queue.remove(0)));
    }


    /**
     * Clear the queue, and return the number of items in
     * the queue before clearing.
     *
     * @return the number of items in the queue
     */
    public int clear() {
        final int size = queue.size();
        queue.clear();
        return size;
    }
}
