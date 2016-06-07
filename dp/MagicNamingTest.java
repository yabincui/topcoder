import org.junit.*;
import static org.junit.Assert.*;

public class MagicNamingTest {
  MagicNaming name = new MagicNaming();

  @Test
  public void test() {
    assertEquals(2, name.maxReindeers("aba"));
    assertEquals(2, name.maxReindeers("babbaba"));
    assertEquals(5, name.maxReindeers("philosophersstone"));
    assertEquals(7, name.maxReindeers("knuthmorrispratt"));
    assertEquals(7, name.maxReindeers("acrushpetrtourist"));
    assertEquals(5, name.maxReindeers("zzzzz"));
  }
}
