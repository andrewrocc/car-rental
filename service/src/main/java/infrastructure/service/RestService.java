package infrastructure.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import infrastructure.config.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;

@Service
public class RestService<T> {

    private String path;

    private Type sourceType;

    private String rawData;

    private final RestTemplate restTemplate;

    public RestService() {
        restTemplate = new RestTemplate();
    }

    public void getResponse(String path) {
        ResponseEntity<String> entity = restTemplate.getForEntity(Constant.URL_REST_SERVICE + path, String.class);
        System.out.println("delete status code: " + entity.getStatusCode());
    }

    public T getData(String path, Type sourceType) {
        this.path = path;
        this.sourceType = sourceType;
        getRawDataFromRestService();
        return deserializeData();
    }

    private void getRawDataFromRestService() {
        try {
            rawData = restTemplate.getForObject(Constant.URL_REST_SERVICE + path, String.class);
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "rest service at " + Constant.URL_REST_SERVICE + path + " is unavailable\n");
        }
    }

    private T deserializeData() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).serializeNulls().create();
        return gson.fromJson(rawData, sourceType);
    }
}
