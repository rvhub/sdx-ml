package sdx.ml.phedex.pojos;

import java.util.Map;

public class PhedexCircuit {

	private String id;
	private String source;
	private String destination;
	private Map<String, String> parameters;

	private PhedexCircuit() {
		// TODO Auto-generated constructor stub
	}

	public PhedexCircuit(String id, String source, String destiantion,
			Map<String, String> parameters) {
		this.id = id;
		this.source = source;
		this.destination = destiantion;
		this.parameters = parameters;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "PhedexCircuit [id=" + id + ", source=" + source
				+ ", destination=" + destination + ", parameters=" + parameters
				+ "]";
	}

}
