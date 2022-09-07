import java.util.Arrays;
import java.util.LinkedList;

public class Tirage {
  private LinkedList<Integer> nombres;
  private final int objectif;

  public Tirage(Integer[] nombres, int objectif) {
    this.nombres = new LinkedList<Integer>(Arrays.asList(nombres));
    this.objectif = objectif;
  }

  public int getObjectif() {
    return objectif;
  }

  public int taille() {
    return nombres.size();
  }

  public void ajouter(int indice, int valeur) {
    nombres.add(indice, valeur);
  }

  public void ajouterFin(int valeur) {
    nombres.addLast(valeur);
  }

  public int retirer(int indice) {
    return nombres.remove(indice);
  }

  public int retirerFin() {
    return nombres.removeLast();
  }
}
