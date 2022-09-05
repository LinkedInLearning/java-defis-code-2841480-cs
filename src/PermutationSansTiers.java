import static org.junit.Assert.*;
import org.junit.Test;

public class PermutationSansTiers {

  @Test
  public void test() {
    byte a = 99;
    byte b = 101;

    a ^= b;
    b ^= a;
    a ^= b;

    assertEquals(99, b);
  }
}
