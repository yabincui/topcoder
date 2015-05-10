import org.junit.*;
import static org.junit.Assert.*;

public class StampTest {
  Stamp stamp = new Stamp();

  @Test
  public void test1() {
    assertEquals(2, stamp.getMinimumCost("R", 1, 1));
    assertEquals(5, stamp.getMinimumCost("RRGGBB", 1, 1));
    assertEquals(5, stamp.getMinimumCost("R**GB*", 1, 1));
    assertEquals(30, stamp.getMinimumCost("BRRB", 2, 7));
    assertEquals(204, stamp.getMinimumCost("R*RR*GG", 10, 58));
    assertEquals(33, stamp.getMinimumCost("*B**B**B*BB*G*BBB**B**B*", 5, 2));
    assertEquals(30, stamp.getMinimumCost("*R*RG*G*GR*RGG*G*GGR***RR*GG", 7, 1));
  }
}
