import org.junit.*;
import static org.junit.Assert.*;

public class NumbersChallengeTest {
  NumbersChallenge challenge = new NumbersChallenge();

  @Test
  public void test1() {
    assertEquals(4, challenge.MinNumber(new int[]{5, 1, 2}));
    assertEquals(8, challenge.MinNumber(new int[]{2, 1, 4}));
    assertEquals(6, challenge.MinNumber(new int[]{2, 1, 2, 7}));
    assertEquals(1092, challenge.MinNumber(new int[]
    {94512, 2, 87654, 81316, 6, 5, 6, 37151, 6, 139, 1, 36, 307, 1, 377, 101, 8, 37, 58, 1}));
    assertEquals(56523, challenge.MinNumber(new int[]
    {883, 66392, 3531, 28257, 1, 14131, 57, 1, 25, 88474, 4, 1, 110, 6, 1769, 220, 442, 7064, 7, 13}));
  }
}
