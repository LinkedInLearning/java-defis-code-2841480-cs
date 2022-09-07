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

  public static Empereur[] extraireListe(Empereur recent) {
    return null;
  }
}
