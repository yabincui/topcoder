import org.junit.*;
import static org.junit.Assert.*;

public class EllysCoprimesDiv2Test {
  EllysCoprimesDiv2 div = new EllysCoprimesDiv2();

  @Test
  public void test() {
    assertEquals(3, div.getCount(new int[]{2200, 42, 2184, 17}));
    assertEquals(0, div.getCount(new int[]{1, 100000}));
    assertEquals(0, div.getCount(new int[]{13, 1, 6, 20, 33}));
    assertEquals(1, div.getCount(new int[]{7, 42}));
    assertEquals(15, div.getCount(new int[]
    {55780, 44918, 55653, 4762, 41536, 40083, 79260, 7374, 24124, 91858, 7856,
 12999, 64025, 12706, 19770, 71495, 32817, 79309, 53779, 8421, 97984, 34586,
 893, 64549, 77792, 12143, 52732, 94416, 54207, 51811, 80845, 67079, 14829,
 25350, 22976, 23932, 62273, 58871, 82358, 13283, 33667, 64263, 1337, 42666}));
  }
}
