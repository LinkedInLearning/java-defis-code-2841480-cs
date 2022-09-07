package vues;

import java.io.IOException;
import java.io.OutputStreamWriter;

public interface Vue {
  void rendre(OutputStreamWriter sortie, OutputStreamWriter erreur) throws IOException;
}
