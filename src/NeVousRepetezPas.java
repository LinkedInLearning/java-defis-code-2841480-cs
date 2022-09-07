import static org.junit.Assert.*;
import org.junit.Test;

public class NeVousRepetezPas
{

  @Test
	public void testMatchInitialisation() {
		Match match = new Match();
		
		assertEquals(0, match.getScoreJeu(Joueur.A));
		assertEquals(0, match.getScoreJeu(Joueur.B));
		assertFalse(match.avantage(Joueur.A));
		assertFalse(match.avantage(Joueur.B));
		assertEquals(0, match.getScoreManche(Joueur.A, 0));
		assertEquals(0, match.getScoreManche(Joueur.B, 0));
		assertEquals(0, match.getIndiceManche());
	}

  @Test
	public void testMatch15a0() {
		Match match = new Match();
		
		match.marquer(Joueur.A);
		assertEquals(15, match.getScoreJeu(Joueur.A));
		assertEquals( 0, match.getScoreJeu(Joueur.B));
		assertFalse(match.avantage(Joueur.A));
		assertFalse(match.avantage(Joueur.B));		
	}

  @Test
	public void testMatch30a15() {
		Match match = new Match();
		
		match.marquer(Joueur.A );
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		assertEquals(30, match.getScoreJeu(Joueur.A));
		assertEquals(15, match.getScoreJeu(Joueur.B));
	}

  @Test
	public void testMatch0a40() {
		Match match = new Match();
		
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		assertEquals( 0, match.getScoreJeu(Joueur.A));
		assertEquals(40, match.getScoreJeu(Joueur.B));
	}

  @Test
	public void testMatchEgalite() {
		Match match = new Match();
		
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer(Joueur.A );
		
		assertEquals(40, match.getScoreJeu(Joueur.A));
		assertEquals(40, match.getScoreJeu(Joueur.B));	
	}

  @Test
	public void testMatchAvantage() {
	Match match = new Match();
		
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer(Joueur.A );
		
		match.marquer( Joueur.B);
		
		assertEquals(40, match.getScoreJeu(Joueur.A));
		assertEquals(40, match.getScoreJeu(Joueur.B));
		assertFalse(match.avantage(Joueur.A));
		assertTrue (match.avantage(Joueur.B));		
	}

  @Test
	public void testMatchEgaliteMultiple() {
	Match match = new Match();
		
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer(Joueur.A );
		
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer(Joueur.A);
		match.marquer( Joueur.B);
		
		assertEquals(40, match.getScoreJeu(Joueur.A));
		assertEquals(40, match.getScoreJeu(Joueur.B));
		assertFalse(match.avantage(Joueur.A));
		assertFalse(match.avantage(Joueur.B));		
	}

  @Test
	public void testMatchNouveauJeu() {
		Match match = new Match();
		
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer( Joueur.B);
		assertEquals(0, match.getScoreJeu(Joueur.A));
		assertEquals(0, match.getScoreJeu(Joueur.B));
		assertEquals(0, match.getScoreManche(Joueur.A, 0));
		assertEquals(1, match.getScoreManche(Joueur.B, 0));
	}

  @Test
	public void testMatchNouveauJeuApresAvantage() {
		Match match = new Match();
		
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer( Joueur.B);
		match.marquer(Joueur.A );
		match.marquer(Joueur.A );
		
		match.marquer( Joueur.B);
		match.marquer( Joueur.B);
		
		assertEquals(0, match.getScoreJeu(Joueur.A));
		assertEquals(0, match.getScoreJeu(Joueur.B));
		assertEquals(0, match.getScoreManche(Joueur.A, 0));
		assertEquals(1, match.getScoreManche(Joueur.B, 0));
	}

  @Test
	public void testVictoire6a4() {
		Match match = new Match();
		
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		
		assertEquals(6, match.getScoreManche(Joueur.A, 0));
		assertEquals(4, match.getScoreManche(Joueur.B, 0));
		assertEquals(1, match.getIndiceManche());
		assertEquals(0, match.getScoreManche(Joueur.A, 1));
		assertEquals(0, match.getScoreManche(Joueur.B, 1));		
	}

  @Test
	public void testManche6a5() {
		Match match = new Match();
		
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		
		assertEquals(6, match.getScoreManche(Joueur.A, 0));
		assertEquals(5, match.getScoreManche(Joueur.B, 0));
		assertEquals(0, match.getIndiceManche());
	}

  @Test
	public void testManche7a5() {
		Match match = new Match();
		
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		
		assertEquals(7, match.getScoreManche(Joueur.A, 0));
		assertEquals(5, match.getScoreManche(Joueur.B, 0));
		assertEquals(1, match.getIndiceManche());
		assertEquals(0, match.getScoreManche(Joueur.A, 1));
		assertEquals(0, match.getScoreManche(Joueur.B, 1));		
	}

  @Test
	public void testManche8a6() {
		Match match = new Match();
		
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match,  Joueur.B);
		gagnerJeu(match, Joueur.A);
		gagnerJeu(match, Joueur.A);
		
		assertEquals(8, match.getScoreManche(Joueur.A, 0));
		assertEquals(6, match.getScoreManche(Joueur.B, 0));
		assertEquals(1, match.getIndiceManche());
		assertEquals(0, match.getScoreManche(Joueur.A, 1));
		assertEquals(0, match.getScoreManche(Joueur.B, 1));		
	}

  private void gagnerJeu(Match m, Joueur j) {
    m.marquer(j);
    m.marquer(j);
    m.marquer(j);
    m.marquer(j);
  }
}
