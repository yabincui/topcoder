import org.junit.*;
import static org.junit.Assert.*;

public class AlternatingLaneTest {
  AlternatingLane lane = new AlternatingLane();

  @Test
  public void test() {
    assertEquals(0.0, lane.expectedBeauty(new int[]{1}, new int[]{100}), 1e-9);
    assertEquals(1.0, lane.expectedBeauty(new int[]{1, 1, 1}, new int[]{2, 2, 2}), 1e-9);
    assertEquals(8.0, lane.expectedBeauty(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10}),
        1e-9);
    assertEquals(6.171428571428572, lane.expectedBeauty(new int[]{4, 3, 3, 7},
        new int[]{10, 7, 7, 7}), 1e-9);
  }
}
