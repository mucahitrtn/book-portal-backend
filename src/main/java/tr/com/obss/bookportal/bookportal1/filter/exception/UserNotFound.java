package tr.com.obss.bookportal.bookportal1.filter.exception;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserNotFound extends RuntimeException{
    private String message;

    public UserNotFound(){
        super();
    }
    public UserNotFound(String message){
        super(message);
    }
}
