/**
 *
 */
package sdx.ml.phedex.pojos;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author ramiro
 *
 */
public class PhedexCircuitRequest {
	public enum Type {
		CREATE, TEARDOWN
	}

	@XmlElement(required=true, name="REQUEST_TYPE")
	private Type type;

	@XmlElement(required=true, name="ID")
	private String id;

	@XmlElement(required=false, name="FROM")
	private String source;

	@XmlElement(required=false, name="TO")
	private String destination;

	@XmlElement(required=false, name="OPTIONS")
	private Map<String, String> parameters;

	private PhedexCircuitRequest() {
	}

	public PhedexCircuitRequest(Type type, String id, String source, String destination,
			Map<String, String> parameters) {
		this.type = type;
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.parameters = parameters;
	}

	public PhedexCircuitRequest(String id) {
		this(Type.TEARDOWN, id, null, null, null);
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhedexCircuit [type=").append(type).append(", id=")
				.append(id).append(", source=").append(source)
				.append(", destination=").append(destination)
				.append(", parameters=").append(parameters).append("]");
		return builder.toString();
	}
}
