import org.junit.*;
import static org.junit.Assert.*;

public class FibonacciDiv2Test {
  FibonacciDiv2 fib = new FibonacciDiv2();

  @Test
  public void test1() {
    assertEquals(0, fib.find(1));
    assertEquals(0, fib.find(13));
    assertEquals(2, fib.find(15));
    assertEquals(2, fib.find(19));
    assertEquals(167960, fib.find(1000000));
  }
}
