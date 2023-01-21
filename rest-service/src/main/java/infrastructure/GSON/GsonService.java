package infrastructure.GSON;

import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service
public class GsonService {

    public static String getJson(Object rawData) {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().setLenient().create().toJson(rawData);
    }
}
