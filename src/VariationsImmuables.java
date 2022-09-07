public class VariationsImmuables {

  public static void main(String[] args) {
    Tirage tirage = new Tirage(new Integer[] { 4, 1, 101, 2 }, 33);
    CompteEstBon c = new CompteEstBon(tirage);

    c.afficherSolutions();
  }
}
