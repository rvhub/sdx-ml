/**
 *
 */
package sdx.ml.phedex.service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import sdx.ml.phedex.pojos.PhedexCircuit;
import sdx.ml.phedex.service.client.PhedexCircuitServiceClient;

/**
 * @author ramiro
 *
 */
public class PhedexCircuitServiceTest {

    @Test
    public void testAllCircuits() {
        PhedexCircuitServiceClient client = new PhedexCircuitServiceClient("localhost", 9998);

        Set<PhedexCircuit> allCircuitsInit = client.getAllCircuits();

        PhedexCircuit circuit1 = client.createCircuit(UUID.randomUUID().toString(), "GVA", "AMS",
                new HashMap<String, String>());

        Set<PhedexCircuit> allCircuits1 = client.getAllCircuits();

        Assert.assertFalse(allCircuitsInit.contains(circuit1));

        Assert.assertTrue(allCircuits1.contains(circuit1));

        PhedexCircuit circuit2 = client.createCircuit(UUID.randomUUID().toString(), "GVA", "AMS",
                new HashMap<String, String>());

        PhedexCircuit circuit2Get = client.getCircuit(circuit2.getId());
        Assert.assertEquals(circuit2Get, circuit2);
    }

}
