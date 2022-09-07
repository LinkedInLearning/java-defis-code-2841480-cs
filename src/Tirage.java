public final class Tirage {
  private final Integer[] nombres;
  private final int objectif;

  public Tirage(Integer[] nombres, int objectif) {
    this.nombres = nombres;
    this.objectif = objectif;
  }

  public int getObjectif() {
    return objectif;
  }

  public int taille() {
    return nombres.length;
  }

  public int get(int indice) {
    return nombres[indice];
  }

  public Tirage modifier(int terme1, int terme2, int total) {
    Integer[] nbrs = new Integer[nombres.length - 1];
    int cur = 0;

    for (int i = 0; i < nombres.length; i++) {
      if (i != terme1 && i != terme2) {
        nbrs[cur++] = nombres[i];
      }
    }
    nbrs[cur] = total;
    return new Tirage(nbrs, objectif);
  }
}
