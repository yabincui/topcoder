import org.junit.*;
import static org.junit.Assert.*;

public class KingdomAndPasswordTest {
  KingdomAndPassword pass = new KingdomAndPassword();

  @Test
  public void test() {
    /*assertEquals(485, pass.newPassword(548, new int[]{5, 1, 8}));
    assertEquals(-1, pass.newPassword(7777, new int[]{4, 7, 4, 7}));
    assertEquals(58, pass.newPassword(58, new int[]{4, 7}));
    assertEquals(127, pass.newPassword(172, new int[]{4, 7, 4}));
    */
    assertEquals(239676554423331L, pass.newPassword(241529363573463L, new int[]
    {1, 4, 5, 7, 3, 9, 8, 1, 7, 6, 3, 2, 6, 4, 5}));
  }
}
