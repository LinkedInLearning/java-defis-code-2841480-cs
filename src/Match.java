import java.util.ArrayList;
import java.util.List;

public class Match {
  private final static int[] SEQUENCE_JEU = { 0, 15, 30, 40 };
  private final static int INDICE_40 = SEQUENCE_JEU.length - 1;
  private final static int MINIMUM_JEUX_GAGNANTS = 6;

  private Confrontation jeu = new Confrontation(INDICE_40 + 1);
  private List<Confrontation> manches = new ArrayList<>();

  private void NouvelleManche() {
    manches.add(new Confrontation(MINIMUM_JEUX_GAGNANTS));
  }

  public Match() {
    NouvelleManche();
  }

  public void marquer(Joueur joueur) {
    if (jeu.gagner(joueur)) {
      jeu.raz();
      if (getMancheCourante().gagner(joueur)) {
        NouvelleManche();
      }
    }
  }

  private Confrontation getMancheCourante() {
    return manches.get(getIndiceManche());
  }

  public int getScoreJeu(Joueur joueur) {
    return SEQUENCE_JEU[Math.min(
        jeu.getScore(joueur),
        INDICE_40)];
  }

  public int getScoreManche(Joueur joueur, int manche) {
    return manches.get(manche).getScore(joueur);
  }

  public boolean avantage(Joueur joueur) {
    return jeu.estDevant(joueur, 1);
  }

  public int getIndiceManche() {
    return manches.size() - 1;
  }

  private class Confrontation {
    private int[] scores = { 0, 0 };
    private int minVictoire;

    public Confrontation(int minVictoire) {
      this.minVictoire = minVictoire;
    }

    public void raz() {
      scores[0] = scores[1] = 0;
    }

    public int getScore(Joueur j) {
      return scores[j.getIndice()];
    }

    private int getScoreAdverse(Joueur j) {
      return scores[1 - j.getIndice()];
    }

    public boolean estDevant(Joueur j, int delta) {
      return getScore(j) >= Math.max(minVictoire, getScoreAdverse(j) + delta);
    }

    public boolean gagner(Joueur j) {
      scores[j.getIndice()]++;
      return estDevant(j, 2);
    }
  }
}
