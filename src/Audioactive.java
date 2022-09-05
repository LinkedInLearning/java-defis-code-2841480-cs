public class Audioactive {

  public static long valeurSuivante(long valeur) {
    long resultat = 0;
    long prec = valeur % 10;
    long puissance100 = 1;
    int compteur = 1;

    do {
      valeur /= 10;

      long chiffre = valeur % 10;

      if (chiffre != prec || valeur == 0) {
        resultat += (10 * compteur + prec) * puissance100;
        prec = chiffre;
        compteur = 0;
        puissance100 *= 100;
      }
      compteur++;
    } while (valeur > 0);
    return resultat;
  }
}
