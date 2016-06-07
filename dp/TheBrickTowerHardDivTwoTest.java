import org.junit.*;
import static org.junit.Assert.*;

public class TheBrickTowerHardDivTwoTest {
  TheBrickTowerHardDivTwo tower = new TheBrickTowerHardDivTwo();

  @Test
  public void test() {
    assertEquals(4, tower.find(2, 0, 2));
    assertEquals(1, tower.find(1, 7, 19));
    assertEquals(14, tower.find(2, 3, 1));
    assertEquals(1008981254, tower.find(4, 7, 47));
  }
}
