import static org.junit.Assert.*;
import org.junit.Test;

public class CompterAllouerRemplir {

  @Test
  public void testInitialisationPremier() {
    Empereur augustus = new Empereur("Auguste");

    assertEquals("Auguste", augustus.getNom());
    assertTrue(augustus.estLePremier());
  }

  @Test
	public void testInitialisationSecond() {
		Empereur augustus = new Empereur("Auguste"); 
		Empereur tiberius = new Empereur("Tibère"  , augustus);
		
		assertFalse(tiberius.estLePremier());
		assertEquals(augustus, tiberius.getPredecesseur());
	}

  @Test
  public void testExtraireListe() {
    Empereur augustus = new Empereur("Auguste");
    Empereur tiberius = new Empereur("Tibère", augustus);
    Empereur caius = new Empereur("Caligula", tiberius);
    Empereur claudius = new Empereur("Claude", caius);
    Empereur nero = new Empereur("Néron", claudius);

    assertArrayEquals(new Empereur[] {
        augustus, tiberius, caius, claudius, nero
    }, Empereur.extraireListe(nero));
  }

}
