import org.junit.*;
import static org.junit.Assert.*;

public class WinterAndReindeersTest {
  WinterAndReindeers winter = new WinterAndReindeers();

  @Test
  public void test() {
    assertEquals(1, winter.getNumber(new String[]{"X"},
        new String[]{"X", "Y"}, new String[]{"X"}));
    assertEquals(3, winter.getNumber(new String[]{"ABCXD"},
        new String[]{"BCDEF"}, new String[]{"CD"}));
    assertEquals(0, winter.getNumber(new String[]{"WE", "LOVE"},
        new String[]{"WORKING", "FOR", "SANTA"},
        new String[]{"JK"}));
    assertEquals(2, winter.getNumber(new String[]{"ABCDE"},
        new String[]{"CDEAB"},
        new String[]{"B"}));
    assertEquals(23, winter.getNumber(
        new String[]
        {"ABCABDEGAXAHDJBAAHFJDXBB", "ABHFHCDALFDJDBBA", "BABBAXAXXX"},
        new String[]{"ABLOCBAXBAHAJDXBIJKA", "JBABCDAHKFIUDALPJAH", "AABACX"},
        new String[]{"AXBADXBBAB", "CDD"}));
  }
}
