import static org.junit.Assert.*;
import org.junit.Test;

public class DesintegrationAudioactive
{

  @Test
	public void test_11_suit_1() {
		assertEquals(11L, Audioactive.valeurSuivante(1L));
	}

  @Test
	public void test_21_suit_11() {
		assertEquals(21L, Audioactive.valeurSuivante(11L));
	}

  @Test
	public void test_1211_suit_21() {
		assertEquals(1211L, Audioactive.valeurSuivante(21L));
	}

  @Test
	public void test_31231924_suit_11133944() {
		assertEquals(31231924L, Audioactive.valeurSuivante(11133944L));
	}

  @Test
	public void test_10_suit_0() {
		assertEquals(10L, Audioactive.valeurSuivante(0L));
	}

}
