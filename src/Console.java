import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import validation.Validateur;
import validation.ValidationException;
import vues.Vue;

public class Console {
  private Scanner clavier = new Scanner(System.in);
  private OutputStreamWriter cout, cerr;

  public Console() {
    cout = new OutputStreamWriter(System.out);
    cerr = new OutputStreamWriter(System.err);
  }

  protected void fermer() {
    clavier.close();
  }

  public <T> T lire(Validateur<T> validateur) throws ValidationException {
    return validateur.convertir(clavier.next());
  }

  public void afficher(Vue vue) {
    try {
      vue.rendre(cout, cerr);
      cout.flush();
      cerr.flush();
    } catch (IOException e) {
      System.err.println(e.getLocalizedMessage());
    }
  }
}
