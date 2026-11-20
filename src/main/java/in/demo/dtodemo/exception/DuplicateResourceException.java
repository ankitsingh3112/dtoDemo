package in.demo.dtodemo.exception;

public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String message){
        super(message);
    }
}
