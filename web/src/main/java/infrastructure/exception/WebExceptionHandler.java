package infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(WebServiceException.class)
    public String handleException(Model model, WebServiceException ex) {
        log.error(ex.getHttpStatus() + ex.getMessage());
        model.addAttribute("exceptionCaption", ex.getClass().getSimpleName());
        model.addAttribute("status", ex.getHttpStatus());
        model.addAttribute("exceptionBody", ex.getMessage());
        return "exception";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleError(Model model, Exception ex) {
        log.error(ex.getMessage());
        model.addAttribute("exceptionCaption", ex.getClass().getSimpleName());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("exceptionBody", ex.getMessage());
        return "exception";
    }
}
