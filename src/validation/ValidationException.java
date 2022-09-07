package validation;

public class ValidationException extends Exception {
  private static final long serialVersionUID = 1L;

  public ValidationException(String raison) {
    super(raison);
  }

  public ValidationException(String raison, Throwable autre) {
    super(raison, autre);
  }
}
