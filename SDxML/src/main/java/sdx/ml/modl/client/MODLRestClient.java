/**
 *
 */
package sdx.ml.modl.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.internal.util.Base64;
import org.opendaylight.controller.sal.reader.FlowOnNode;

import sdx.ml.jackson.MLJacksonConfig;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author ramiro
 *
 */
public class MODLRestClient {


	private void getCurrentPathSelector() {
		ClientConfig configuration = new ClientConfig();

		Client client = ClientBuilder.newClient(configuration).register(MLJacksonConfig.class);

		HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic("admin", "admin");
		client.register(auth);
//		WebTarget target = client
//				.target("http://localhost:8080/controller/nb/v2/multipath/default");
//		Builder request = target.path("flows").path("LAX").request(
//				MediaType.APPLICATION_JSON);
//		System.out.println(list);

//		WebTarget target = client
//				.target("http://localhost:8080/controller/nb/v2/statistics/default");
//		Builder request = target.path("flow").request(
//				MediaType.APPLICATION_JSON);
//
//
//		AllFlowStatistics allFlowStatistics = request.get(AllFlowStatistics.class);
//		System.out.println(allFlowStatistics);

		try {

		Gson gson = new Gson();
		URLConnection urlConnection = new URL("http://localhost:8080/controller/nb/v2/multipath/default/flows/LAX").openConnection();
		String userNamePass = "admin:admin";
		urlConnection.setRequestProperty("Authorization", "Basic " + Base64.encodeAsString(userNamePass));
		urlConnection.setRequestProperty("Content-type", "application/json");
		urlConnection.connect();
		List<FlowOnNode> allFlowStatistics = gson.fromJson(new BufferedReader(new InputStreamReader(urlConnection.getInputStream())), new TypeToken<List<FlowOnNode>>() {}.getType());
		System.out.println(allFlowStatistics);

		} catch(Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 *
	 */
	public MODLRestClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final MODLRestClient yes = new MODLRestClient();
		yes.getCurrentPathSelector();
	}

}
