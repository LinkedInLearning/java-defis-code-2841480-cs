import static org.junit.Assert.*;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class ItererParIntermittence {

  @Test
	public void testPetitNombreEn1s() {
		NombrePremier.Recherche recherche = NombrePremier.nouvelleRecherche(7);
		
		NombrePremier.traiter(recherche, 1000); 
		
		assertTrue(recherche.estTrouvee());
		assertEquals(11, recherche.getValeurTrouvee());
	}

  @Test
	public void testGrandNombreEnPlusieursIntervallesDe100ms() {
		NombrePremier.Recherche recherche = NombrePremier.nouvelleRecherche(179424691L);
		long compteur = 0;
		
		do {
			Instant debut = Instant.now();
			
			NombrePremier.traiter(recherche, 100);
			Duration duree = Duration.between( debut , Instant.now() ) ;
			
			assertTrue(duree.toMillis() <= 100L);
			compteur ++;			
		}
		while(!recherche.estTrouvee());
		assertTrue(compteur>1);
		assertEquals(179424697L, recherche.getValeurTrouvee());
	}

}
