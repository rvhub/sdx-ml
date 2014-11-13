package sdx.ml.jackson;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MLJacksonConfig implements ContextResolver<ObjectMapper>{

    private final ObjectMapper customMapper;

	public MLJacksonConfig() {
		customMapper = new ObjectMapper();
		customMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return customMapper;
	}

}
