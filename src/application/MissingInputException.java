package application;

public class MissingInputException extends Exception{
	public MissingInputException(String errorMessage) {
        super(errorMessage);
    }
}
