import org.junit.*;
import static org.junit.Assert.*;

public class AlienAndSetDiv2Test {
  AlienAndSetDiv2 alien = new AlienAndSetDiv2();

  @Test
  public void test() {
    assertEquals(4, alien.getNumber(2, 1));
    assertEquals(8, alien.getNumber(3, 1));
    assertEquals(6, alien.getNumber(2, 2));
    assertEquals(44, alien.getNumber(4, 2));
    assertEquals(184756, alien.getNumber(10, 10));
  }
}
