import org.junit.*;
import static org.junit.Assert.*;

public class LittleElephantAndSubsetTest {
  LittleElephantAndSubset subset = new LittleElephantAndSubset();

  @Test
  public void test() {
    assertEquals(7, subset.getNumber(3));
    assertEquals(767, subset.getNumber(10));
    assertEquals(25775, subset.getNumber(47));
    assertEquals(66437071, subset.getNumber(4777447));
  }
}
