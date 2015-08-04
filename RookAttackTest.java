import org.junit.*;
import static org.junit.Assert.*;

public class RookAttackTest {
  RookAttack attack = new RookAttack();

  @Test
  public void test() {
    assertEquals(8, attack.howMany(8, 8, new String[]{}));
    assertEquals(0, attack.howMany(2, 2, new String[]
    {"0 0","0 1","1 1","1 0"}));
    assertEquals(2, attack.howMany(3, 3, new String[]
    {"0 0","1 0","1 1","2 0","2 1","2 2"}));
    assertEquals(3, attack.howMany(3, 3, new String[]
    {"0 0","1 2","2 2"}));
    assertEquals(200, attack.howMany(200, 200, new String[]{}));
  }
}
