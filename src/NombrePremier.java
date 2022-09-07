public class NombrePremier {
  public interface Recherche {
    public boolean estTrouvee();

    public long getValeurTrouvee();
  }

  public static Recherche nouvelleRecherche(long nombre) {
    return new AlgoRecherche(nombre);
  }

  public static void traiter(Recherche recherche, int tempsLimite) {
    AlgoRecherche r = (AlgoRecherche) recherche;
    long nombre = r.getNombre();
    long diviseur;

    do {
      nombre++;
      diviseur = 2;
      while (diviseur < nombre && nombre % diviseur != 0) {
        diviseur++;
      }
    } while (diviseur < nombre);
    r.setValeurTrouvee(nombre);
  }

  private static class AlgoRecherche implements Recherche {
    private long valeurTrouvee;
    private boolean trouvee = false;
    private final long nombre;

    public AlgoRecherche(long nombre) {
      this.nombre = nombre;
    }

    @Override
    public boolean estTrouvee() {
      return trouvee;
    }

    @Override
    public long getValeurTrouvee() {
      if (!estTrouvee()) {
        throw new IllegalStateException("Aucune valeur trouvée pour l'instant");
      }
      return valeurTrouvee;
    }

    public void setValeurTrouvee(
        long valeurTrouvee) {
      this.valeurTrouvee = valeurTrouvee;
      this.trouvee = true;
    }

    public long getNombre() {
      return nombre;
    }
  }
}
