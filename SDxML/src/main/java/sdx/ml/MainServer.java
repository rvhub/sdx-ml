/**
 *
 */
package sdx.ml;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import sdx.ml.phedex.service.PhedexCircuitService;

/**
 * @author ramiro
 *
 */
public class MainServer {

	/**
	 *
	 */
	public MainServer() {

	}


	private static final HttpServer startServer() throws IOException {
		//bind on all intefaces -- this will bind on IPv6 as well
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0").port(9998).build();
	    ResourceConfig config = new ResourceConfig(PhedexCircuitService.class);
	    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
	    server.start();
	    return server;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			HttpServer server = startServer();

		}catch(Throwable t) {
			t.printStackTrace();
		}

		try {
			for(;;) {

				Thread.sleep(1000);
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}


	}

}
