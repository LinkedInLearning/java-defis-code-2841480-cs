package vues;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.Supplier;

public class VueObjet implements Vue {

  private Supplier<Object> obj;

  public VueObjet(Supplier<Object> obj) {
    this.obj = obj;
  }

  @Override
  public void rendre(OutputStreamWriter sortie, OutputStreamWriter erreur) throws IOException {
    sortie.write(String.format("%s\n", obj.get()));
  }

}