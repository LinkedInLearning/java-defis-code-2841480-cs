package etats;

import java.util.stream.Stream;

public abstract class EtatCourse {
  private int id;
  private ContexteCourse contexte;

  public EtatCourse(int id, ContexteCourse contexte) {
    this.id = id;
    this.contexte = contexte;
  }

  public int getId() {
    return id;
  }

  protected ContexteCourse getContexte() {
    return contexte;
  }

  public Iterable<Participant> getInscrits() {
    return contexte.getInscrits();
  }

  public abstract Stream<Participant> getClassement();

  public abstract Participant getGagnant();

  public abstract EtatCourse inscrire(Participant participant);

  public abstract EtatCourse demarrer();

  public abstract EtatCourse passerLaLigneDArrivee(Participant participant);
}
