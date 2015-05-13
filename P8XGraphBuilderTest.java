import org.junit.*;
import static org.junit.Assert.*;

public class P8XGraphBuilderTest {
  P8XGraphBuilder graph = new P8XGraphBuilder();

  @Test
  public void test() {
    assertEquals(8, graph.solve(new int[]{1, 3, 0}));
    assertEquals(10, graph.solve(new int[]{0, 0, 0, 10}));
    assertEquals(12, graph.solve(new int[]{1, 2, 3, 4, 5, 6}));
    assertEquals(15, graph.solve(new int[]{5, 0, 0}));
    assertEquals(20, graph.solve(new int[]{1, 3, 2, 5, 3, 7, 5}));
  }
}
