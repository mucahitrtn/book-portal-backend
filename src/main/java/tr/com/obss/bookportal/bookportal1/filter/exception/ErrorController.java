package tr.com.obss.bookportal.bookportal1.filter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tr.com.obss.bookportal.bookportal1.dto.ErrorDto;

import javax.servlet.http.HttpServletRequest;


@ResponseBody
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(UserNotFound userNotFound, HttpServletRequest request){
        return new ErrorDto(10000L,userNotFound.getMessage());
    }

}
