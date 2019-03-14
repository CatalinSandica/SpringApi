package ro.sandica.app.SpringApi.Helpers;

public class ExceptionNotFoundObject extends RuntimeException {

	public ExceptionNotFoundObject() {
		super();
	}
	public ExceptionNotFoundObject(String message) {
		super(message);
	}
	public ExceptionNotFoundObject(String message, Throwable cause) {
		super(message, cause);
	}
}
