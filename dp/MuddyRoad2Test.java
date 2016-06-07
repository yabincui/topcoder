import org.junit.*;
import static org.junit.Assert.*;

public class MuddyRoad2Test {
  MuddyRoad2 road = new MuddyRoad2();

  @Test
  public void test() {
    assertEquals(2, road.theCount(5, 1));
    assertEquals(2, road.theCount(5, 2));
    assertEquals(65, road.theCount(10, 4));
    assertEquals(498142902, road.theCount(314, 78));
    assertEquals(541606281, road.theCount(555, 222));
  }
}
