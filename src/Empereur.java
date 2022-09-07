public class Empereur {
  private final String nom;
  private final Empereur predecesseur;

  public Empereur(String nom, Empereur predecesseur) {
    if (predecesseur == null) {
      throw new NullPointerException("Prédécesseur non valide");
    }
    this.nom = nom;
    this.predecesseur = predecesseur;
  }

  public Empereur(String nom) {
    this.nom = nom;
    this.predecesseur = null;
  }

  public String getNom() {
    return this.nom;
  }

  public boolean estLePremier() {
    return this.predecesseur == null;
  }

  public Empereur getPredecesseur() {
    if (predecesseur == null) {
      throw new IllegalStateException("Pas de prédécesseur pour le premier empereur !");
    }
    return this.predecesseur;
  }

  private static Empereur[] extraireListe(Empereur courant, int nombre) {
    Empereur[] resultat = courant.estLePremier()
        ? new Empereur[nombre]
        : extraireListe(courant.getPredecesseur(), nombre + 1);

    resultat[resultat.length - nombre] = courant;
    return resultat;
  }

  public static Empereur[] extraireListe(Empereur recent) {
    return extraireListe(recent, 1);
  }
}
