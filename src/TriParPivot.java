public class TriParPivot {
  public <T extends Comparable<? super T>> void trier(T[] tableau) {
    trier(tableau, 0, tableau.length);
  }

  private <T extends Comparable<? super T>> void trier(T[] tableau, int premier, int dernier) {
    if (premier >= dernier) {
      return;
    }
    int pos = premier;

    for (int fin = dernier - 1; pos < fin;) {
      permuter(
          tableau, pos + 1,
          tableau[pos + 1].compareTo(tableau[pos]) < 0 ? pos++ : fin--);
    }
    trier(tableau, premier, pos);
    trier(tableau, pos + 1, dernier);
  }

  private static <T> void permuter(T[] tableau, int indice1, int indice2) {
    T tmp = tableau[indice1];

    tableau[indice1] = tableau[indice2];
    tableau[indice2] = tmp;
  }
}
