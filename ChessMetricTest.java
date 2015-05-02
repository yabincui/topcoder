import org.junit.*;
import static org.junit.Assert.*;

public class ChessMetricTest {
  ChessMetric chess = new ChessMetric();

  @Test
  public void test1() {
    assertEquals(1, chess.howMany(3, new int[]{0, 0}, new int[]{1, 0}, 1));
  }

  @Test
  public void test2() {
    assertEquals(1, chess.howMany(3, new int[]{0, 0}, new int[]{1, 2}, 1));
  }

  @Test
  public void test3() {
    assertEquals(0, chess.howMany(3, new int[]{0, 0}, new int[]{2, 2}, 1));
  }

  @Test
  public void test4() {
    assertEquals(5, chess.howMany(3, new int[]{0, 0}, new int[]{0, 0}, 2));
  }

  @Test
  public void test5() {
    assertEquals(243097320072600L, chess.howMany(100, new int[]{0, 0}, new int[]{0, 99}, 50));
  }
}
