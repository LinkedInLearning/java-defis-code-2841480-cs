package validation;

import vues.Menu;

public class ValidateurMenu implements Validateur<Integer> {
  private Menu menu;

  public ValidateurMenu(Menu menu) {
    this.menu = menu;
  }

  @Override
  public Integer convertir(String valeur) throws ValidationException {
    try {
      int n = Integer.parseInt(valeur)-1;
      Menu.Element element = menu.getElement(n);

      if (!element.estActif()) {
        throw new ValidationException("Élément de menu inactif.");
      }
      return n;
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new ValidationException("Élément de menu inexistant.");
    } catch (NumberFormatException e) {
      throw new ValidationException("Nombre non valide", e);
    }

  }

}
