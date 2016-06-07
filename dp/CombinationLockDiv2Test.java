import org.junit.*;
import static org.junit.Assert.*;

public class CombinationLockDiv2Test {
  CombinationLockDiv2 lock = new CombinationLockDiv2();

  @Test
  public void test() {
    assertEquals(1, lock.minimumMoves("123", "112"));
    assertEquals(4, lock.minimumMoves("1", "7"));
    assertEquals(0, lock.minimumMoves("607", "607"));
    assertEquals(3, lock.minimumMoves("1234", "4567"));
    assertEquals(2, lock.minimumMoves("020", "909"));
    // move in one direction for more than 9.
    assertEquals(18, lock.minimumMoves("4423232218340", "6290421476245"));
  }
}
