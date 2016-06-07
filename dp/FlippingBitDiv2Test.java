import org.junit.*;
import static org.junit.Assert.*;

public class FlippingBitDiv2Test {
  FlippingBitDiv2 flip = new FlippingBitDiv2();

  @Test
  public void test() {
    assertEquals(2, flip.getmin(new String[]{"00111000"}, 1));
    assertEquals(3, flip.getmin(new String[]{"00111000"}, 2));
    assertEquals(0, flip.getmin(new String[]{"111111"}, 3));
    assertEquals(2, flip.getmin(new String[]{"00100"}, 5));
    assertEquals(31, flip.getmin(new String[]
    {"00010","11010110","1010111","110001010","0110001100"
,"000110110","011010101","00","111","100"}, 13));
  }
}
