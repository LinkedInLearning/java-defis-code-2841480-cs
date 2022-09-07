package etats;

import java.util.ArrayList;
import java.util.List;

public class ContexteCourse {
  private final List<Participant> inscrits = new ArrayList<Participant>();
  private List<Participant> classement = new ArrayList<>();

  public List<Participant> getInscrits() {
    return inscrits;
  }

  public List<Participant> getClassement() {
    return classement;
  }
}
