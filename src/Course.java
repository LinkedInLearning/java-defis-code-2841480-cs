import etats.Arrivee;
import etats.ContexteCourse;
import etats.EnCours;
import etats.EtatCourse;
import etats.Inscription;

public class Course {
  public final static int INSCRIPTION = 0,
      EN_COURS = 1,
      ARRIVEE = 2;
  private final ContexteCourse contexte = new ContexteCourse();
  private final Categorie categorie;

  private EtatCourse etat = new Inscription(INSCRIPTION, contexte,
      new EnCours(EN_COURS, contexte,
          new Arrivee(ARRIVEE, contexte)));

  public Course(Categorie categorie) {
    if (categorie == null) {
      throw new NullPointerException("La categorie doit être renseignée");
    }
    this.categorie = categorie;
  }

  public Categorie getCategorie() {
    return categorie;
  }

  public int getEtat() {
    return this.etat.getId();
  }

  public Iterable<Participant> getInscrits() {
    return etat.getInscrits().map(p -> (Participant) p).toList();
  }

  public Iterable<Participant> getClassement() {
    return etat.getClassement().map(p -> (Participant) p).toList();
  }

  public Participant getGagnant() {
    return (Participant) etat.getGagnant();
  }

  public void inscrire(Participant participant) {
    etat = etat.inscrire(participant);
  }

  public void demarrer() {
    etat = etat.demarrer();
  }

  public void passerLaLigneDArrivee(Participant participant) {
    etat = etat.passerLaLigneDArrivee(participant);
  }
}
