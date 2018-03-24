package mfwallace.acserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Entry point into the application.  This starts a server
 * on port 8080, exposing endpoints to manage the queue.
 *
 * @author Mike Wallace
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class AircraftController {

    /** The service layer handling the requests. */
    private final AircraftService aircraftService = new AircraftServiceImpl();

    /**
     * Add a plane to the queue.
     *
     * @param aircraft the aircraft to add to the queue
     * @return the aircraft that was added
     */
    @RequestMapping(value = "/enqueue", method = RequestMethod.POST)
    public Aircraft enqueueAircraft(final @RequestBody Aircraft aircraft) {
        return aircraftService.enqueue(aircraft);
    }

    /**
     * Remove a plane from the queue.
     *
     * @return the plane that was removed
     */
    @RequestMapping(value = "/dequeue", method = RequestMethod.GET)
    public Aircraft dequeue() {
        return aircraftService.dequeue();
    }

    /**
     * List the aircraft in the queue.
     *
     * @return the list of aircraft in the queue
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Aircraft> list() {
        return aircraftService.list();
    }

    /**
     * The main method for the application.
     *
     * @param args arguments to the application
     */
	public static void main(String[] args) {
		SpringApplication.run(AircraftController.class, args);
	}
}
