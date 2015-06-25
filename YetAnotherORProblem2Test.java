import org.junit.*;
import static org.junit.Assert.*;

public class YetAnotherORProblem2Test {
  YetAnotherORProblem2 yet = new YetAnotherORProblem2();

  @Test
  public void test() {
    assertEquals(17, yet.countSequences(2, 4));
    assertEquals(7, yet.countSequences(2, 2));
    assertEquals(9, yet.countSequences(2, 3));
    assertEquals(16, yet.countSequences(3, 3));
    assertEquals(7, yet.countSequences2(2, 2));
    assertEquals(9, yet.countSequences2(2, 3));
    assertEquals(16, yet.countSequences2(3, 3));

    for (int i = 2; i <= 4; ++i) {
      for (int j = 1; j <= 10; ++j) {
        System.out.printf("count for %d, %d\n", i, j);
        assertEquals(yet.countSequences(i, j), yet.countSequences2(i, j));
      }
    }

    assertEquals(73741815, yet.countSequences(7, 1023));
  }
}
