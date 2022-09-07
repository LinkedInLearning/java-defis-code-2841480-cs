package modeles;

public class Categorie {
  public final static Categorie HORS_CATEGORIE = new Categorie("Hors catégorie");

  private final String titre;

  public Categorie(String titre) {
    if (titre == null) {
      throw new NullPointerException("Titre ne peut être nul");
    }
    this.titre = titre;
  }

  public String getTitre() {
    return titre;
  }
}
