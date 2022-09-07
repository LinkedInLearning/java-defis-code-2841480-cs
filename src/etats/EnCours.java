package etats;

import java.util.stream.Stream;

public class EnCours extends EtatCourse {

  private EtatCourse suivant;

  public EnCours(int id, ContexteCourse contexte, EtatCourse suivant) {
    super(id, contexte);
    this.suivant = suivant;
  }

  @Override
  public Stream<Participant> getClassement() {
    throw new IllegalStateException("La course n'est pas terminée");
  }

  @Override
  public Participant getGagnant() {
    throw new IllegalStateException("La course n'est pas terminée");
  }

  @Override
  public EtatCourse inscrire(Participant participant) {
    throw new IllegalStateException("Inscriptions fermées, la course est commencée");
  }

  @Override
  public EtatCourse demarrer() {
    throw new IllegalStateException("La course est déjà démarrée");
  }

  @Override
  public EtatCourse passerLaLigneDArrivee(Participant participant) {
    return suivant.passerLaLigneDArrivee(participant);
  }
}
