import org.junit.*;
import static org.junit.Assert.*;

public class MovingRooksDiv2Test {
  MovingRooksDiv2 rook = new MovingRooksDiv2();

  @Test
  public void test() {
    assertEquals("Possible", rook.move(new int[]{0}, new int[]{0}));
    assertEquals("Possible", rook.move(new int[]{1, 0}, new int[]{0, 1}));
    assertEquals("Impossible", rook.move(new int[]{0, 1}, new int[]{1, 0}));
    assertEquals("Possible", rook.move(new int[]{3,1,2,0}, new int[]{0,2,1,3}));
    assertEquals("Impossible", rook.move(new int[]{3,1,2,0}, new int[]{3,2,0,1}));
  }
}
