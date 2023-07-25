package infrastructure.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoggingServletService implements LoggingService {

    @Override
    public void logRequest(HttpServletRequest request, Object body) {
        Map<String, String> parameters = buildParametersMap(request);
        String requestLog = "REQUEST " + request.getMethod() + " " + request.getRequestURI() + "\n" +
                "headers=[" + buildHeadersMap(request) + "]";

        if (!parameters.isEmpty()) {
            requestLog += "\n" + "parameters=[" + parameters + "]";
        }
        if (body != null) {
            requestLog += "\n" + "body=[" + body + "]";
        }

        log.info(requestLog);
    }

    @Override
    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
        String stringBuilder = "RESPONSE " + request.getMethod() + request.getRequestURI() + "\n" +
                "responseHeaders=[" + buildHeadersMap(response) + "]" + "\n" +
                "status=" + response.getStatus() + "\n" +
                "responseBody=[" + body + "]";

        log.info(stringBuilder);
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }
}
