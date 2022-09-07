import static org.junit.Assert.*;
import org.junit.Test;

public class LeSensDesPriorites {

  @Test
  public void testChiffre() {
    assertEquals(2.0, Equation.calculer("2"), 0.01);
  }

  @Test
  public void testSomme() {
    assertEquals(2 + 3, Equation.calculer("2+3"), 0.01);
  }

  @Test
  public void testSommes() {
    assertEquals(2 + 3 + 4, Equation.calculer("2+3+4"), 0.01);
  }

  @Test
  public void testProduits() {
    assertEquals(2 * 3 * 4, Equation.calculer("2*3*4"), 0.01);
  }

  @Test
  public void testProduitPuisSomme() {
    assertEquals(2 * 3 + 4, Equation.calculer("2*3+4"), 0.01);
  }

  @Test
  public void testSommePuisProduit() {
    assertEquals(2 + 3 * 4, Equation.calculer("2+3*4"), 0.01);
  }

  @Test
	public void testProduitPuisSommeAvecParentheses() {
		assertEquals(3 * (4 + 5), Equation.calculer("3*(4+5)"), 0.01);
	}

  @Test
	public void testSommeAvecParenthesesPuisProduit() {
		assertEquals((2 + 3) * 4, Equation.calculer("(2+3)*4"), 0.01);
	}

  @Test
	public void testSommePuisSommeAvecParenthesesPuisProduit() {
		assertEquals(1 + (2 + 3) * 4, Equation.calculer("1+(2+3)*4"), 0.01);
	}

  @Test
  public void testEquationComplexe() {
    assertEquals(((3 - 1 + 4 + 2 * 3) / 6 + 9) * (3 - 1), Equation.calculer("((3-1+4+2*3)/6+9)*(3-1)"), 0.01);
  }
}
