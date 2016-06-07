import org.junit.*;
import static org.junit.Assert.*;

public class SingingEasyTest {
  SingingEasy easy = new SingingEasy();

  @Test
  public void test() {
    assertEquals(7, easy.solve(new int[]{1, 3, 8, 12, 13}));
    assertEquals(3, easy.solve(new int[]{1, 5, 6, 2, 1}));
    assertEquals(0, easy.solve(new int[]{5,5,5,5,4,4,4,4}));
    assertEquals(0, easy.solve(new int[]{1000000}));
    assertEquals(188, easy.solve(new int[]{24,13,2,4,54,23,12,53,12,23,42,13,53,12,24,12,11,24,42,52,12,32,42}));
  }
}
