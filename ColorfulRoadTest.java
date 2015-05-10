import org.junit.*;
import static org.junit.Assert.*;

public class ColorfulRoadTest {
  ColorfulRoad road = new ColorfulRoad();

  @Test
  public void test1() {
    assertEquals(8, road.getMin("RGGGB"));
    assertEquals(8, road.getMin("RGBRGBRGB"));
    assertEquals(-1, road.getMin("RBBGGGRR"));
    assertEquals(50, road.getMin("RBRRBGGGBBBBR"));
    assertEquals(1, road.getMin("RG"));
    assertEquals(52, road.getMin("RBRGBGBGGBGRGGG"));
  }
}
