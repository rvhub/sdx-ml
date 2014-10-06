package sdx.ml.phedex.pojos;

import java.net.InetAddress;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class PhedexCircuit {

	public enum Status {
		REQUESTING,
		ESTABLISHED,
		FAILED
	}

	@XmlElement(required=true, name="ID")
	private String id;

	@XmlElement(required=false, name="FROM")
	private String source;

	@XmlElement(required=false, name="TO")
	private String destination;

	@XmlElement(required=false, name="OPTIONS")
	private Map<String, String> parameters;

	@XmlElement(required=false, name="FROM_IP")
	private InetAddress fromIP;

	@XmlElement(required=false, name="TO_IP")
	private InetAddress toIP;

	@XmlElement(required=false, name="BANDWIDTH")
	private long bandwidth;

	@XmlElement(required=false, name="STATUS")
	private Status status;

	private PhedexCircuit() {
		// TODO Auto-generated constructor stub
	}

	public static final PhedexCircuit fromCircuitRequest(PhedexCircuitRequest circuitRequest) {
		return new PhedexCircuit(circuitRequest.getId(), circuitRequest.getSource(), circuitRequest.getDestination(), circuitRequest.getParameters());
	}

	public PhedexCircuit(String id, String source, String destination,
			Map<String, String> parameters) {
		this.id = id;
		this.source = source;
		this.destination = destination;
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

	public InetAddress getFromIP() {
		return fromIP;
	}

	public void setFromIP(InetAddress fromIP) {
		this.fromIP = fromIP;
	}

	public InetAddress getToIP() {
		return toIP;
	}

	public void setToIP(InetAddress toIP) {
		this.toIP = toIP;
	}

	public long getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(long bandwidth) {
		this.bandwidth = bandwidth;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PhedexCircuit))
			return false;
		PhedexCircuit other = (PhedexCircuit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhedexCircuit [id=").append(id).append(", source=")
				.append(source).append(", destination=").append(destination)
				.append(", parameters=").append(parameters).append(", fromIP=")
				.append(fromIP).append(", toIP=").append(toIP)
				.append(", bandwidth=").append(bandwidth).append(", status=")
				.append(status).append("]");
		return builder.toString();
	}

}
