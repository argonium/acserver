package mfwallace.acserver;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AircraftServiceTest {

    /**
     * Test the dequeuing strategy for model type.
     */
	@Test
	public void testDequeuingByType() {

        final Aircraft plane1 = new Aircraft(1, Aircraft.ModelType.Passenger, Aircraft.ModelSize.Small);
        final Aircraft plane2 = new Aircraft(2, Aircraft.ModelType.Emergency, Aircraft.ModelSize.Small);
        final Aircraft plane3 = new Aircraft(3, Aircraft.ModelType.VIP, Aircraft.ModelSize.Small);
        final Aircraft plane4 = new Aircraft(4, Aircraft.ModelType.Cargo, Aircraft.ModelSize.Small);

        AircraftService service = new AircraftServiceImpl();
        service.enqueue(plane1);
        service.enqueue(plane2);
        service.enqueue(plane3);
        service.enqueue(plane4);

        final List<Aircraft> list = service.list();
        Assert.assertEquals(4, list.size());

        Assert.assertEquals(2, service.dequeue().getId());
        Assert.assertEquals(3, service.dequeue().getId());
        Assert.assertEquals(1, service.dequeue().getId());
        Assert.assertEquals(4, service.dequeue().getId());
    }

    /**
     * Test the dequeuing strategy for model size and date it was enqueued.
     */
    @Test
    public void testDequeueingBySizeAndDate() {

        final Aircraft plane1 = new Aircraft(1, Aircraft.ModelType.Passenger, Aircraft.ModelSize.Small);
        final Aircraft plane2 = new Aircraft(2, Aircraft.ModelType.Passenger, Aircraft.ModelSize.Large);

        // Sleep for a bit so we have a noticeable difference in the time the plane was enqueued
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Aircraft plane3 = new Aircraft(3, Aircraft.ModelType.Passenger, Aircraft.ModelSize.Small);

        AircraftService service = new AircraftServiceImpl();
        service.enqueue(plane1);
        service.enqueue(plane2);
        service.enqueue(plane3);

        final List<Aircraft> list = service.list();
        Assert.assertEquals(3, list.size());

        // The large aircraft comes first, followed by the earlier small craft,
        // and then the later small craft
        Assert.assertEquals(2, service.dequeue().getId());
        Assert.assertEquals(1, service.dequeue().getId());
        Assert.assertEquals(3, service.dequeue().getId());
    }
}
