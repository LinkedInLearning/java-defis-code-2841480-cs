// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

import java.util.Scanner;

public class SansCommentaire {

  public static final int TAILLE = 3; // NE PEUT PAS DÉPASSER 9 car la saisie utilisateur ne supporte pas plus d'un
                                      // chiffre
  public static final String PIONS = " xo";
  public static final Direction[] DIRECTIONS = {
      //            dx, dy
      new Direction(1, 0), // Est
      new Direction(1, 1), // Sud-Est
      new Direction(0, 1) // Sud
  };
  private static final int AUCUN = 0;
  private static final int JOUEUR1 = 1;
  private static final int JOUEUR2 = 2;

  public static void main(String[] args) {
    int[][] plateau = new int[TAILLE][TAILLE];
    int joueur = JOUEUR1;
    int gagnant;
    int casesLibres = TAILLE * TAILLE;
    Scanner clavier = new Scanner(System.in);

    do {
      dessinerPlateau(plateau);

      Position coup = Position.lire(plateau, String.format("Joueur %d", joueur), clavier);

      plateau[coup.getX()][coup.getY()] = joueur;
      casesLibres--;
      gagnant = Direction.getGagnant(plateau, DIRECTIONS);

      joueur = adversaire(joueur);
    } while (gagnant == AUCUN && casesLibres > 0);

    dessinerPlateau(plateau);

    System.out.printf(gagnant == AUCUN
        ? "Match nul !"
        : "Bravo joueur %d !", gagnant);
    clavier.close();
  }

  public static class Direction {
    private final int dx;
    private final int dy;

    /**
     * Initialise une direction utilisée pour la recherche de victoire
     * @param dx Direction horizontale
     * @param dy Direction verticale
     */
    public Direction(int dx, int dy) {
      this.dx = dx;
      this.dy = dy;
    }

    /**
		 * Recherche un gagnant dans cette direction
		 * @param plateau Plateau sur lequel chercher le joueur gagnant
		 * @return Joueur gagnant, "Aucun" si aucun
		 */
		public int getJoueurGagnant(int [][] plateau) {
			int joueur = plateau[0][0];
			
			for(int i=1; i<TAILLE; i++) {
				if(ieme(plateau, i) != joueur) {
					return AUCUN;
				}
			}
			return joueur;
		}

    /**
     * Recherche si un joueur a aligné tous ses pions dans une des directions
     * fournies.
     * 
     * @param plateau    Plateau sur lequel l'alignement doit être cherché
     * @param directions Tableau des directions sur lesquelles chercher l'alignement
     * @return Le joueur gagnant, "Aucun" si aucun
     */
    public static int getGagnant(int[][] plateau, Direction[] directions) {
      int gagnant = AUCUN;

      for (Direction d : directions) {
        gagnant = d.getJoueurGagnant(plateau);
        if (gagnant != AUCUN) {
          break;
        }
      }
      return gagnant;
    }

    private int ieme(int[][] plateau, int i) {
			return plateau[i*dx][i*dy];
		}
  }

  private static class Position {
    private int x, y;

    private Position(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public static Position lire(int[][] plateau, String prompt, Scanner clavier) {
      int x = -1, y = -1;

      do {
        System.out.printf("%s, quelle case (ex: A1) ? ", prompt);
        String position = clavier.next();

        try {
          x = position.charAt(0) - 'A';
          y = position.charAt(1) - '1';
        } catch (Exception e) {
          System.err.println("Saisie incorrecte");
        }
      } while (x < 0 || TAILLE <= x || y < 0 || TAILLE <= y || plateau[x][y] != AUCUN);
      return new Position(x, y);
    }
  }

  private static int adversaire(int joueur) {
    return JOUEUR1 + JOUEUR2 - joueur;
  }

  private static void dessinerLigne(int taille) {
    for (int j = 0; j < taille; j++) {
      System.out.print("+---");
    }
    System.out.println("+");
  }

  private static void dessinerPlateau(int[][] plateau) {
    for (int x = 0; x < TAILLE; x++) {
      System.out.printf("  %c ", 'A' + x);
    }
    System.out.println();
    for (int y = 0; y < TAILLE; y++) {
      dessinerLigne(TAILLE);
      for (int x = 0; x < TAILLE; x++) {
        System.out.printf("| %c ", PIONS.charAt(plateau[x][y]));
      }
      System.out.printf("| %d\n", y + 1);
    }
    dessinerLigne(TAILLE);

  }

}
