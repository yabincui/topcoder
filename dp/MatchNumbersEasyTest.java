import org.junit.*;
import static org.junit.Assert.*;

public class MatchNumbersEasyTest {
  MatchNumbersEasy match = new MatchNumbersEasy();

  @Test
  public void test() {
    assertEquals("210", match.maxNumber(new int[]{6, 7, 8}, 21));
    assertEquals("20", match.maxNumber(new int[]{5, 23, 24}, 30));
    assertEquals("0", match.maxNumber(new int[]{1, 5, 3, 2}, 1));
    assertEquals("99999999999999999999999999999999999999999999999999",
                 match.maxNumber(new int[]{1,1,1,1,1,1,1,1,1,1}, 50));
  }
}
