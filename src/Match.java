import java.util.ArrayList;
import java.util.List;

public class Match {
  private int[] jeu = { 0, 0 };
  private List<Manche> manches = new ArrayList<>();

  private final static int[] SEQUENCE_JEU = { 0, 15, 30, 40 };
  private final static int INDICE_40 = SEQUENCE_JEU.length - 1;

  public Match() {
    manches.add(new Manche());
  }

  public void marquer(Joueur joueur) {
    int gagnant = ++jeu[joueur.getIndice()];

    if (gagnant > INDICE_40) {
      int perdant = jeu[1 - joueur.getIndice()];

      if (gagnant - perdant >= 2) {
        jeu[0] = jeu[1] = 0;
        if (manches.get(manches.size() - 1).gagner(joueur)) {
          manches.add(new Manche());
        }
      }
    }
  }

  public int getScoreJeu(Joueur joueur) {
    return SEQUENCE_JEU[Math.min(
        jeu[joueur.getIndice()],
        INDICE_40)];
  }

  public int getScoreManche(Joueur joueur, int manche) {
    return manches.get(manche).getScore(joueur);
  }

  public boolean avantage(Joueur joueur) {
    return jeu[joueur.getIndice()] > Math.max(INDICE_40, jeu[1 - joueur.getIndice()]);
  }

  public int getIndiceManche() {
    return manches.size() - 1;
  }

  private class Manche {
    private int[] scores = { 0, 0 };

    public boolean gagner(Joueur j) {
      int score = ++scores[j.getIndice()];
      int autre = scores[1 - j.getIndice()];

      return score >= 6 && score - autre >= 2;
    }

    public int getScore(Joueur j) {
      return scores[j.getIndice()];
    }
  }
}
