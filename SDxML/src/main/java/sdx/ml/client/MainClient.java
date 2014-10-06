package sdx.ml.client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sdx.ml.phedex.pojos.PhedexCircuit;
import sdx.ml.phedex.pojos.PhedexCircuitRequest;
import sdx.ml.phedex.pojos.PhedexCircuitRequest.Type;

public class MainClient {

	static final PhedexCircuit createCircuit(String id, String from, String to, Map<String, String> options) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:9998/phedex");
		Builder request = target.request(MediaType.APPLICATION_JSON);
		PhedexCircuitRequest pcr = new PhedexCircuitRequest(Type.CREATE, id, from, to, options);
		final Invocation buildPost = request.buildPost(Entity.entity(pcr, MediaType.APPLICATION_JSON_TYPE));
		Response response = buildPost.invoke();
		System.out.println("CREATE -- Status from server: " + response.getStatus());
		return response.readEntity(PhedexCircuit.class);
	}

	static final PhedexCircuit deleteCircuit(String id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:9998/phedex");
		Builder request = target.request(MediaType.APPLICATION_JSON);
		PhedexCircuitRequest pcr = new PhedexCircuitRequest(id);
		final Invocation buildPost = request.buildPost(Entity.entity(pcr, MediaType.APPLICATION_JSON_TYPE));
		Response response = buildPost.invoke();
		System.out.println("DELETE -- Status from server: " + response.getStatus());
		return response.readEntity(PhedexCircuit.class);
	}

	public static void main(String[] args) {
		PhedexCircuit c = createCircuit(UUID.randomUUID().toString(), "GVA", "AMS", new HashMap<String, String>());
		System.out.println();
		System.out.println(createCircuit(UUID.randomUUID().toString(), "GVA", "AMS", new HashMap<String, String>()));
		System.out.println();
		PhedexCircuit deleteCircuit = deleteCircuit(c.getId());
	}
}
