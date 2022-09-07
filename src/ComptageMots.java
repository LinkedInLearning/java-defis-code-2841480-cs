public class ComptageMots {
  private enum Etat {
    Espace,
    Mot
  }

  private final static String SEPARATEURS = "'-.;:?! \t\n\r";

  private static boolean estUnSeparateur(char caractere) {
    return SEPARATEURS.indexOf(caractere) >= 0;
  }

  public static int compter(String phrase) {
    Etat etat = Etat.Espace;
    int nombreMots = 0;

    for (int i = 0; i < phrase.length(); i++) {
      char caractere = phrase.charAt(i);

      switch (etat) {
        case Espace:
          if (!estUnSeparateur(caractere)) {
            etat = Etat.Mot;
            nombreMots++;
          }
          break;
        case Mot:
          if (estUnSeparateur(caractere)) {
            etat = Etat.Espace;
          }
          break;
      }
    }
    return nombreMots;
  }
}
