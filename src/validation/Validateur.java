package validation;

public interface Validateur<T> {
  T convertir(String valeur) throws ValidationException;
}
