import org.junit.*;
import static org.junit.Assert.*;

public class TheKingsTreeTest {
  TheKingsTree tree = new TheKingsTree();

  @Test
  public void test() {
    assertEquals(4, tree.getNumber(new int[]{0, 0, 0}));
    assertEquals(12, tree.getNumber(new int[]{0, 1, 2, 3, 4}));
    assertEquals(10, tree.getNumber(new int[]{0, 1, 2, 3, 1}));
    assertEquals(26, tree.getNumber(new int[]
    {0, 0, 1, 0, 4, 3, 5, 2, 0, 7, 9, 2, 4, 5, 3, 1}));
    assertEquals(1, tree.getNumber(new int[]{}));
  }
}
