import org.junit.*;
import static org.junit.Assert.*;

public class RandomGraphTest {
  RandomGraph graph = new RandomGraph();

  @Test
  public void test() {
    //assertEquals(0.0, graph.probability(7, 0), 1e-9);
    //assertEquals(0.0, graph.probability(3, 620), 1e-9);
    assertEquals(0.59375, graph.probability(4, 500), 1e-9);
    assertEquals(0.33566851611343496, graph.probability(8, 100), 1e-9);
    assertEquals(0.5686761670525845, graph.probability(15, 50), 1e-9);
    assertEquals(0.7494276522159893, graph.probability(50, 10), 1e-9);
    assertEquals(1.0, graph.probability(50, 1000), 1e-9);
  }
}
