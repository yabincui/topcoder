import org.junit.*;
import static org.junit.Assert.*;

public class TrafficCongestionDivTwoTest {
  TrafficCongestionDivTwo traffic = new TrafficCongestionDivTwo();

  @Test
  public void test1() {
    assertEquals(1L, traffic.theMinCars(1));
    assertEquals(3L, traffic.theMinCars(2));
    assertEquals(683L, traffic.theMinCars(10));
    assertEquals(768614336404564651L, traffic.theMinCars(60));
  }
}
