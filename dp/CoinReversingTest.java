import org.junit.*;
import static org.junit.Assert.*;

public class CoinReversingTest {
  CoinReversing coin = new CoinReversing();

  @Test
  public void test() {
    assertEquals(1.6666666666666667, coin.expectedHeads(3, new int[]{2, 2}), 1e-9);
    assertEquals(0.0, coin.expectedHeads(10, new int[]{10, 10, 10}), 1e-9);
    assertEquals(4.792639999999999, coin.expectedHeads(10, new int[]{2,7,1,8,2,8}), 1e-9);
    assertEquals(498.1980774932278, coin.expectedHeads(1000, new int[]{916,153,357,729,183,848,61,672,295,936}), 1e-9);
  }
}
