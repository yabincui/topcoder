import org.junit.*;
import static org.junit.Assert.*;

public class ProblemsToSolveTest {
  ProblemsToSolve solve = new ProblemsToSolve();

  @Test
  public void test() {
    assertEquals(2, solve.minNumber(new int[]{1, 2, 3}, 2));
    assertEquals(3, solve.minNumber(new int[]{1, 2, 3, 4, 5}, 4));
    assertEquals(3, solve.minNumber(new int[]{10, 1, 12, 101}, 100));
    assertEquals(2, solve.minNumber(new int[]{10, 1}, 9));
    assertEquals(2, solve.minNumber(new int[]{6, 2, 6, 2, 6, 3, 3, 3, 7}, 4));
  }
}
