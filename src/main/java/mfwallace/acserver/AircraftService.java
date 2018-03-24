package mfwallace.acserver;

import java.util.List;

/**
 * Service layer methods for the controller.
 */
public interface AircraftService {

    /**
     * Return the list of aircraft in the queue.
     *
     * @return the list of aircraft in the queue, or null if empty
     */
    List<Aircraft> list();

    /**
     * Add the specified aircraft to the queue.
     *
     * @param aircraft the aircraft we want to add
     * @return the aircraft that was added
     */
    Aircraft enqueue(Aircraft aircraft);

    /**
     * Remove the aircraft first in the sort order.
     *
     * @return the aircraft removed from the queue
     */
    Aircraft dequeue();

    /**
     * Clear the queue, and return the number of items in
     * the queue before clearing.
     *
     * @return the number of items in the queue
     */
    int clear();
}
