import org.junit.*;
import static org.junit.Assert.*;

public class MegaFactorialDiv2Test {
  MegaFactorialDiv2 mega = new MegaFactorialDiv2();

  @Test
  public void test() {
    assertEquals(4, mega.countDivisors(3, 1));
    assertEquals(6, mega.countDivisors(3, 2));
    assertEquals(18, mega.countDivisors(4, 2));
    assertEquals(1392, mega.countDivisors(6, 3));
    assertEquals(321266186, mega.countDivisors(100, 2));
  }
}
