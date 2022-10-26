package domain.exception;

public class NoTaskException extends RuntimeException {

    public NoTaskException (String message){
        super(message);
    }

    public NoTaskException (){

    }
}
