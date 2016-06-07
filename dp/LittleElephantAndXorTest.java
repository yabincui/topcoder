import org.junit.*;
import static org.junit.Assert.*;

public class LittleElephantAndXorTest {
  LittleElephantAndXor xor = new LittleElephantAndXor();

  @Test
  public void test() {
    assertEquals(5, xor.getNumber(2, 2, 1));
    assertEquals(20, xor.getNumber(4, 7, 3));
    assertEquals(57, xor.getNumber(10, 10, 5));
    assertEquals(214144, xor.getNumber(774, 477, 447));
    assertEquals(468566946385621507L, xor.getNumber(1000000000, 1000000000, 500000000));
  }
}
