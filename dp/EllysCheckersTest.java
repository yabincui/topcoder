import org.junit.*;
import static org.junit.Assert.*;

public class EllysCheckersTest {
  EllysCheckers checker = new EllysCheckers();

  @Test
  public void test1() {
    assertEquals("YES", checker.getWinner("o."));
    assertEquals("YES", checker.getWinner("..o..o"));
    assertEquals("NO", checker.getWinner(".o...ooo..oo.."));
    assertEquals("YES", checker.getWinner("......o.ooo.o......"));
    assertEquals("NO", checker.getWinner(".o..o...o....o.....o"));
  }
}
