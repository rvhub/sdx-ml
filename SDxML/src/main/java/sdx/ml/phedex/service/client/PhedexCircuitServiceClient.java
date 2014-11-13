package sdx.ml.phedex.service.client;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdx.ml.phedex.pojos.PhedexCircuit;
import sdx.ml.phedex.pojos.PhedexCircuitRequest;
import sdx.ml.phedex.pojos.PhedexCircuitRequest.Type;

public class PhedexCircuitServiceClient {

    private final static Logger logger = LoggerFactory.getLogger(PhedexCircuitServiceClient.class);

    private static final int DEFAULT_PORT = 80;
    private static final String DEFAULT_URL_PREFIX = "phedex";

    final String host;
    final int port;
    final String urlPrefix;

    private final String clientURL;

    public PhedexCircuitServiceClient(String host) {
        this(host, DEFAULT_PORT, DEFAULT_URL_PREFIX);
    }

    public PhedexCircuitServiceClient(String host, int port) {
        this(host, port, DEFAULT_URL_PREFIX);
    }

    public PhedexCircuitServiceClient(String host, String urlPrefix) {
        this(host, DEFAULT_PORT, urlPrefix);
    }

    public PhedexCircuitServiceClient(String host, int port, String urlPrefix) {
        Objects.requireNonNull(host, "Null host");

        this.host = host;
        this.port = port;
        this.urlPrefix = urlPrefix;

        //no SSL for the time being
        boolean bAppendPort = ((port > 0) && (port != 80));
        boolean bAppendPrefix = ((urlPrefix != null) && !urlPrefix.isEmpty());

        this.clientURL = "http://" + host + ((bAppendPort) ? ":" + port : "") + (bAppendPrefix ? "/" + urlPrefix : "");
    }

    public PhedexCircuit createCircuit(String id, String from, String to, Map<String, String> options) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(clientURL);
        Builder request = target.request(MediaType.APPLICATION_JSON);
        PhedexCircuitRequest pcr = new PhedexCircuitRequest(Type.CREATE, id, from, to, options);
        final Invocation buildPost = request.buildPost(Entity.entity(pcr, MediaType.APPLICATION_JSON_TYPE));
        Response response = buildPost.invoke();
        logger.info("CREATE -- Status from server: {}", response.getStatus());
        return response.readEntity(PhedexCircuit.class);
    }

    public PhedexCircuit deleteCircuit(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(clientURL);
        Builder request = target.request(MediaType.APPLICATION_JSON);
        Response response = request.delete();
        logger.info("DELETE -- Status from server: {}", response.getStatus());
        return response.readEntity(PhedexCircuit.class);
    }

    public Set<PhedexCircuit> getAllCircuits() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(clientURL);
        Builder request = target.request(MediaType.APPLICATION_JSON);
        Response response = request.get();
        logger.info("GET ALL -- Status from server: {}", response.getStatus());

        return response.readEntity(new GenericType<Set<PhedexCircuit>>() {
        });
    }

    public PhedexCircuit getCircuit(String circuitID) {
        Objects.requireNonNull(circuitID, "Null circuitID");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(clientURL).path(circuitID);
        Builder request = target.request(MediaType.APPLICATION_JSON);
        Response response = request.get();
        logger.info("GET CIRCUIT -- Status from server: {}", response.getStatus());

        return response.readEntity(PhedexCircuit.class);
    }

}
