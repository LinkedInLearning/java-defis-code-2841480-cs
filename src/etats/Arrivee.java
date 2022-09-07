package etats;

import java.util.List;
import java.util.stream.Stream;

public class Arrivee extends EtatCourse {
  public Arrivee(int id, ContexteCourse contexte) {
    super(id, contexte);
  }

  @Override
  public Stream<Participant> getClassement() {
    return getContexte().getClassement().stream();
  }

  @Override
  public Participant getGagnant() {
    return getContexte().getClassement().get(0);
  }

  @Override
  public EtatCourse inscrire(Participant participant) {
    throw new IllegalStateException("Inscriptions fermées, la course est terminée");
  }

  @Override
  public EtatCourse demarrer() {
    throw new IllegalStateException("La course est déjà démarrée");
  }

  @Override
  public EtatCourse passerLaLigneDArrivee(Participant participant) {
    if (participant == null) {
      throw new NullPointerException("Le participant doit être renseigné");
    }
    List<Participant> classement = getContexte().getClassement();

    if (classement.contains(participant)) {
      throw new IllegalArgumentException("Le/La participant-e est déjé arrivé-e");
    }
    classement.add(participant);
    return this;
  }
}
