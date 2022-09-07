import java.util.ArrayList;
import java.util.List;

public class Course {
  private final List<Participant> inscrits = new ArrayList<Participant>();
  private List<Participant> classement = null;
  private final Categorie categorie;

  public Course(Categorie categorie) {
    this.categorie = categorie;
  }

  public Course() {
    this(null);
  }

  public Categorie getCategorie() {
    return categorie;
  }

  public Iterable<Participant> getInscrits() {
    return inscrits;
  }

  public Iterable<Participant> getClassement() {
    return classement;
  }

  public Participant getGagnant() {
    return classement == null || classement.size() == 0
        ? null
        : classement.get(0);
  }

  public void inscrire(Participant participant) {
    if (classement != null) {
      throw new IllegalStateException("Inscriptions fermées, la course est commencée");
    } else if (inscrits.contains(participant)) {
      throw new IllegalArgumentException("Le/La participant-e est déjà inscrit-e");
    }
    inscrits.add(participant);
  }

  public void demarrer() {
    if (classement != null) {
      throw new IllegalStateException("La course est déjà démarrée");
    }
    classement = new ArrayList<>();
  }

  public void passerLaLigneDArrivee(Participant participant) {
    if (classement == null) {
      throw new IllegalStateException("La course n'a pas démarré, inscriptions en cours");
    } else if (!inscrits.contains(participant)) {
      throw new IllegalArgumentException("Le/La participant-e n'est pas inscrit-e");
    } else if (classement.contains(participant)) {
      throw new IllegalArgumentException("Le/La participant-e est déjà arrivé-e");
    }
    classement.add(participant);
  }
}
