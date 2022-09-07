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

	/////////////////////////////////////////////////////////////////
	// Constantes du programme
	//
	public static final int TAILLE = 3; // Taille du plateau de jeu (NE PEUT PAS DÉPASSER 9)
	public static final String PIONS = " xo"; // Caractères utilisés pour les pions	
	public static final Direction [] DIRECTIONS = { // Directions pour recherche de victoire
		//            dx, dy 
		new Direction( 1,  0), // Est
		new Direction( 1,  1), // Sud-Est
		new Direction( 0,  1)  // Sud		
	}; 
	
	public static void main(String[] args) {
		int [][] plateau = new int[TAILLE][TAILLE]; // 0 : vide, 1 : joueur 1, 2 : joueur 2
		int gagnant = 0; // Initialisation obligatoire, le compilateur ne détecte pas l'initialisation dans for(:)
		int joueur = 1;
		int casesLibres = TAILLE*TAILLE;
		Scanner clavier = new Scanner(System.in); 
		
		do
		{
			// Dessin du plateau
			for(int i=0; i<TAILLE; i++) {
				System.out.printf("  %c ", 'A'+i);
			}
			System.out.println();
			for(int i=0; i<TAILLE; i++) { // Lignes
				dessinerLigne(TAILLE);
				for(int j=0; j<TAILLE; j++) { // Colonnes
					System.out.printf("| %c ", PIONS.charAt(plateau[j][i]));
				}
				System.out.printf("| %d\n", i+1);
			}
			dessinerLigne(TAILLE);
			
			// Récupération du choix du joueur, on recommence tant que ce n'est pas valide
			int x=-1, y=-1;
			do {
				System.out.printf("Joueur %d, quelle case (ex: A1) ? ", joueur);
				String position = clavier.next();
				
				try {
					x = position.charAt(0)-'A';
					y = position.charAt(1)-'1';
				}
				catch(Exception e) {
					// Mauvais formatage
					System.err.println("Saisie incorrecte");
				}
			}
			while(x < 0 || TAILLE <= x || y < 0 || TAILLE <= y || plateau[x][y]!=0);
			
			// Placement du pion et recherche du gagnant
			plateau[x][y] = joueur;
			casesLibres--;
			for(Direction d : DIRECTIONS) {
				gagnant = d.getJoueurGagnant(plateau);
				if(gagnant > 0) {
					break;
				}
			}
			
			// Changement de joueur
			joueur = 3 - joueur; 
		}
		while(gagnant == 0 && casesLibres > 0);
		
		// TODO : Afficher le plateau en fin de partie
		
		System.out.printf(gagnant==0 
			? "Match nul !" 
			: "Bravo joueur %d !", gagnant
		);
		clavier.close();
	}
	
	///////////////////////////////////////////
	// Classe Direction
	// 
	public static class Direction {
		// Champs privés
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
		
		// Méthodes
		
		/**
		 * Recherche un gagnant dans cette direction
		 * @param plateau Plateau sur lequel chercher le joueur gagnant
		 * @return Numéro du joueur gagnant, 0 si aucun
		 */
		public int getJoueurGagnant(int [][] plateau) {
			int joueur = plateau[0][0];
			
			for(int i=1; i<TAILLE; i++) {
				// ième case selon la direction
				if(plateau[i*dx][i*dy] != joueur) {
					return 0;
				}
			}
			return joueur;
		}
	}
	
	//////////////////////////////////////////////////////
	// Méthodes de dessin
	private static void dessinerLigne(int taille) {
		for(int j=0; j<taille; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
	
}
