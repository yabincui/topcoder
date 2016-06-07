import org.junit.*;
import static org.junit.Assert.*;

public class NumberofFiboCallsTest {
  NumberofFiboCalls fibo = new NumberofFiboCalls();

  @Test
  public void test() {
    assertArrayEquals(new int[]{1, 0}, fibo.fiboCallsMade(0));
    assertArrayEquals(new int[]{1, 1}, fibo.fiboCallsMade(2));
    assertArrayEquals(new int[]{1, 2}, fibo.fiboCallsMade(3));
    assertArrayEquals(new int[]{5, 8}, fibo.fiboCallsMade(6));
    assertArrayEquals(new int[]{10946, 17711}, fibo.fiboCallsMade(22));
  }
}
