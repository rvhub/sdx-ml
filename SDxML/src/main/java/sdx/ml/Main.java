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
public class Main {

	/**
	 *
	 */
	public Main() {

	}


	private static final void startServer() throws IOException {
		//bind on all intefaces -- this will bind on IPv6 as well
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0").port(9998).build();
	    ResourceConfig config = new ResourceConfig(PhedexCircuitService.class);
	    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
	    server.start();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			startServer();
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

//		final NEResponse allNEsResponse = getAllNEsResponse();
//		List<NE> result = allNEsResponse.getResult();
//
//		for(NE ne: result) {
//			System.out.println(ne.getIdNetworkElement());
//			System.out.println(getInterfacesForNE(ne));
//		}
	}

}
