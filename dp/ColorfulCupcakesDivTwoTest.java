import org.junit.*;
import static org.junit.Assert.*;

public class ColorfulCupcakesDivTwoTest {
  ColorfulCupcakesDivTwo cup = new ColorfulCupcakesDivTwo();

  @Test
  public void test() {
    assertEquals(2, cup.countArrangements("ABAB"));
    assertEquals(0, cup.countArrangements("ABABA"));
    assertEquals(6, cup.countArrangements("ABC"));
    assertEquals(2, cup.countArrangements("ABABABABABABABABABABABABABABABABABABABABABABABABAB"));
    assertEquals(741380640, cup.countArrangements("BCBABBACBABABCCCCCAABBAACBBBBCBCAAA"));
  }
}
