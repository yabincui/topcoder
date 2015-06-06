import org.junit.*;
import static org.junit.Assert.*;

public class FoxAndMountainTest {
  FoxAndMountain fox = new FoxAndMountain();

  @Test
  public void test() {
    assertEquals(1, fox.count(4, "UUDD"));
    assertEquals(0, fox.count(4, "DUUD"));
    assertEquals(1, fox.count(4, "UU"));
    assertEquals(0, fox.count(49, "U"));
    assertEquals(2, fox.count(4, "UD"));
    assertEquals(990, fox.count(20, "UUUDUUU"));
    assertEquals(3718, fox.count(30, "DUDUDUDUDUDUDUDU"));
    assertEquals(946357703, fox.count(50, "U"));
  }
}
