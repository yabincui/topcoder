import org.junit.*;
import static org.junit.Assert.*;

public class RelativelyPrimeSubsetTest {
  RelativelyPrimeSubset prime = new RelativelyPrimeSubset();

  @Test
  public void test() {
    assertEquals(4, prime.findSize(new int[]{2, 3, 7, 11, 4}));
    assertEquals(1, prime.findSize(new int[]{4, 8, 12, 16}));
    assertEquals(3, prime.findSize(new int[]{100, 17, 81, 82}));
    assertEquals(3, prime.findSize(new int[]{2, 3, 4, 5, 6}));
  }
}
