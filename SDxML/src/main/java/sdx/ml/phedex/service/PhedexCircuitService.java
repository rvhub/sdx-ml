package sdx.ml.phedex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sdx.ml.phedex.dao.PhedexCircuitsHolder;
import sdx.ml.phedex.pojos.PhedexCircuit;

@Path("/phedex")
public class PhedexCircuitService {

	final PhedexCircuitsHolder circuitHolder;

	public PhedexCircuitService() {
		circuitHolder = new PhedexCircuitsHolder();

		circuitHolder.saveCircuit(new PhedexCircuit("1", "GVA", "AMS", null));
		Map<String, String> optionsMap = new HashMap<>();
		optionsMap.put("transfer_bw", "1G");
		optionsMap.put("transfer_files", "10000");
		circuitHolder.saveCircuit(new PhedexCircuit("2", "NYC", "CHI", optionsMap));
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCircuit(PhedexCircuit circuit){
		final PhedexCircuit ret = circuitHolder.saveCircuit(circuit);
		System.out.println("Create circuit: " + circuit + " --- response: " + ret);
		return Response.ok(ret).build();
	}


	@DELETE
	@Path("/{id}")
	public Response deleteCircuit(final @PathParam("id") String id) {
		final PhedexCircuit deleteCircuit = circuitHolder.deleteCircuit(id);
		return Response.ok(deleteCircuit).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<PhedexCircuit> circuits = circuitHolder.getAllCircuits();
		GenericEntity<List<PhedexCircuit>> allResponse = new GenericEntity<List<PhedexCircuit>>(
				circuits) {
		};
		return Response.ok(allResponse).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(final @PathParam("id") String id) {
		PhedexCircuit circuit = circuitHolder.getCircuit(id);
		return Response.ok(circuit).build();
	}
}
