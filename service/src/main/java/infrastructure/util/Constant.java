package infrastructure.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

    public static final String URL_REST_SERVICE = "http://localhost:8090/rest-car";

    public static final String RESOURCE_CAR_PATH = "/cars";

    public static final String NOT_BLANK_MESSAGE = "This field cannot be empty.";

    public static final String NOT_DIGITS_MESSAGE = "Only digits are accepted.";
}
