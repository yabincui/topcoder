import org.junit.*;
import static org.junit.Assert.*;

public class Polygons2Test {
  @Test
  public void test() {
    Polygons2 polygons = new Polygons2();

    assertEquals(4, polygons.number(new int[]{1, 1, 1, 1}, 3));
    assertEquals(3, polygons.number(new int[]{2,3,4,5}, 3));
    assertEquals(11, polygons.number(new int[]{4,4,4,2,2,2}, 3));
    assertEquals(2, polygons.number(new int[]{10,1,4,9,20}, 4));
    assertEquals(532, polygons.number(new int[]{3310,1660,211,1260,160,213,884,539,17212,2025,105,120,5510}, 7));
  }
}
