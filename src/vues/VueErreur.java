package vues;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class VueErreur implements Vue {
  private String message;

  public VueErreur(String message) {
    this.message = message;
  }

  @Override
  public void rendre(OutputStreamWriter sortie, OutputStreamWriter erreur) throws IOException {
    erreur.write(message);
    erreur.write("\n");
  }

}
