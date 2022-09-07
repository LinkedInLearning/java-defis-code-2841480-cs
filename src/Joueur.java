public enum Joueur {
  A(0),
  B(1);

  private final int indice;

  private Joueur(int indice) {
    this.indice = indice;
  }

  public int getIndice() {
    return this.indice;
  }
}
