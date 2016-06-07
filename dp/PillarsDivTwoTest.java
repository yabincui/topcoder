import org.junit.*;
import static org.junit.Assert.*;

public class PillarsDivTwoTest {
  PillarsDivTwo pillar = new PillarsDivTwo();

  @Test
  public void test1() {
    assertEquals(5.656854249492381, pillar.maximalLength(new int[]{3, 3, 3}, 2), 1e-9);
    assertEquals(300.0, pillar.maximalLength(new int[]{1, 1, 1, 1}, 100), 1e-9);
    assertEquals(396.32310051270036, pillar.maximalLength(new int[]{100,2,100,2,100}, 4), 1e-9);
    assertEquals(3.82842712474619, pillar.maximalLength(new int[]{2,1,1,2}, 1), 1e-9);
  }
}
