import java.time.Duration;
import java.time.Instant;

public class NombrePremier {
  public interface Recherche {
    public boolean estTrouvee();

    public long getValeurTrouvee();
  }

  public static Recherche nouvelleRecherche(long nombre) {
    return new AlgoRecherche(nombre);
  }

  public static void traiter(Recherche recherche, int tempsLimite) {
    Instant fin = Instant.now().plus(Duration.ofMillis(tempsLimite));
    AlgoRecherche r = (AlgoRecherche) recherche;

    while (Instant.now().compareTo(fin) < 0 && !r.estTrouvee()) {
      r.iteration();
    }
  }

  private static class AlgoRecherche implements Recherche {
    private static enum Etat {
      Init,
      Recherche,
      Trouvee
    }
    private Etat etat = Etat.Init;

    private long diviseur;
    private long nombre;

    public AlgoRecherche(long nombre) {
      this.nombre = nombre;
    }

    @Override
    public boolean estTrouvee() {
      return etat == Etat.Trouvee;
    }

    @Override
    public long getValeurTrouvee() {
      if (!estTrouvee()) {
        throw new IllegalStateException("Aucune valeur trouvée pour l'instant");
      }
      return nombre;
    }

    public void iteration() {
      switch (etat) {
        case Init:
          nombre++;
          diviseur = 2;
          etat = Etat.Recherche;
          break;
        case Recherche:
          if (diviseur == nombre) {
            etat = Etat.Trouvee;
          } else if (nombre % diviseur == 0) {
            etat = Etat.Init;
          } else {
            diviseur++;
          }
          break;
        case Trouvee:
          break;
      }
    }
  }
}
