package sdx.ml.phedex.service;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdx.ml.circuits.loopback.CircuitLoopbackUtils;
import sdx.ml.phedex.dao.PhedexCircuitsHolder;
import sdx.ml.phedex.pojos.PhedexCircuit;
import sdx.ml.phedex.pojos.PhedexCircuitRequest;
import sdx.ml.phedex.pojos.PhedexCircuitRequest.Type;

@Path("/phedex")
public class PhedexCircuitService {
    private final static Logger logger = LoggerFactory.getLogger(PhedexCircuitService.class);

	static final PhedexCircuitsHolder circuitHolder = new PhedexCircuitsHolder();

	public PhedexCircuitService() {

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response circuitRequest(PhedexCircuitRequest circuitRequest){
		final Type reqType = circuitRequest.getType();
		switch(reqType) {
		case CREATE: {
			final PhedexCircuit circuit = PhedexCircuit.fromCircuitRequest(circuitRequest);
			try {
				circuit.setToIP(InetAddress.getByName("127.0.0.2"));
				circuit.setFromIP(InetAddress.getByName("127.0.0.3"));
				circuit.setStatus(PhedexCircuit.Status.REQUESTING);
			}catch(Throwable t) {
				t.printStackTrace();
			}
			final PhedexCircuit ret = circuitHolder.saveCircuit(circuit);
			CircuitLoopbackUtils.changeStatus((ret == null)?circuit:ret, PhedexCircuit.Status.ESTABLISHED, 10, TimeUnit.SECONDS);
			logger.info("Create circuit: {} --- response: {}", circuit, ret);
			return Response.ok(circuit).build();
		}
		case TEARDOWN: {
			final String id = circuitRequest.getId();
			final PhedexCircuit deleteCircuit = circuitHolder.deleteCircuit(id);
			logger.info("Delete circuit: {}", deleteCircuit);
			return Response.ok(deleteCircuit).build();
		}
		default: {
		}
		}
		return Response.status(Status.METHOD_NOT_ALLOWED).build();
	}


	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCircuit(final @PathParam("id") String id) {
		final PhedexCircuit deleteCircuit = circuitHolder.deleteCircuit(id);
		return Response.ok(deleteCircuit).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@QueryParam("ID") String id) {
		if(id == null || id.isEmpty()) {
			List<PhedexCircuit> circuits = circuitHolder.getAllCircuits();
			GenericEntity<List<PhedexCircuit>> allResponse = new GenericEntity<List<PhedexCircuit>>(
					circuits) {
			};
			return Response.ok(allResponse).build();
		}

		final PhedexCircuit reqCircuit = circuitHolder.getCircuit(id);
		return Response.ok(reqCircuit).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(final @PathParam("id") String id) {
		PhedexCircuit circuit = circuitHolder.getCircuit(id);
		return Response.ok(circuit).build();
	}
}
