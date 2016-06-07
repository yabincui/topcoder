import org.junit.*;
import static org.junit.Assert.*;

public class ChangingSoundsTest {
  ChangingSounds sound = new ChangingSounds();

  @Test
  public void test() {
    assertEquals(10, sound.maxFinal(new int[]{5, 3, 7}, 5, 10));
    assertEquals(-1, sound.maxFinal(new int[]{15, 2, 9, 10}, 8, 20));
    assertEquals(238, sound.maxFinal(new int[]
    {74,39,127,95,63,140,99,96,154,18,137,162,14,88}, 40, 243));
  }
}
