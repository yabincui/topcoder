import org.junit.*;
import static org.junit.Assert.*;

public class TheCowDivTwoTest {
  TheCowDivTwo cow = new TheCowDivTwo();

  @Test
  public void test() {
    assertEquals(5, cow.find(7, 4));
    assertEquals(1, cow.find(1, 1));
    assertEquals(7322, cow.find(58, 4));
    assertEquals(704466492, cow.find(502, 7));
    assertEquals(219736903, cow.find(1000, 47));
  }
}
