import org.junit.*;
import static org.junit.Assert.*;

public class MutaliskEasyTest {
  MutaliskEasy easy = new MutaliskEasy();

  @Test
  public void test1() {
    assertEquals(1, easy.minimalAttacks(new int[]{9, 3, 1}));
    assertEquals(2, easy.minimalAttacks(new int[]{9, 3, 2}));
    assertEquals(2, easy.minimalAttacks(new int[]{12, 10, 4}));
    assertEquals(6, easy.minimalAttacks(new int[]{54, 18, 6}));
    assertEquals(13, easy.minimalAttacks(new int[]{55, 60, 53}));
    assertEquals(1, easy.minimalAttacks(new int[]{1, 1, 1}));
    assertEquals(9, easy.minimalAttacks(new int[]{60, 40}));
    assertEquals(7, easy.minimalAttacks(new int[]{60}));
  }
}
