import org.junit.*;
import static org.junit.Assert.*;

public class LittleElephantAndArrayTest {
  LittleElephantAndArray array = new LittleElephantAndArray();

  @Test
  public void test() {
    // assertEquals(1, array.getNumber(1, 9));
    assertEquals(3, array.getNumber(10, 0));
    assertEquals(15, array.getNumber(10, 2));
    assertEquals(8369, array.getNumber(4747774, 1));
    assertEquals(977836619, array.getNumber(6878542150015L, 74));
  }
}
