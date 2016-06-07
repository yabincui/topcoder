import org.junit.*;
import static org.junit.Assert.*;

public class PrimeSoccerTest {
  PrimeSoccer soccer = new PrimeSoccer();

  @Test
  public void test() {
    assertEquals(0.5265618908306351, soccer.getProbability(50, 50), 1e-9);
    assertEquals(0.0, soccer.getProbability(100, 100), 1e-9);
    assertEquals(0.6772047168840167, soccer.getProbability(12, 89), 1e-9);
  }
}
