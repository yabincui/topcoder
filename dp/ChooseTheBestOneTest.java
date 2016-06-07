import org.junit.*;
import static org.junit.Assert.*;

public class ChooseTheBestOneTest {
  ChooseTheBestOne best = new ChooseTheBestOne();

  @Test
  public void test1() {
    assertEquals(2, best.countNumber(2));
    assertEquals(2, best.countNumber(3));
    assertEquals(6, best.countNumber(6));
    assertEquals(8, best.countNumber(10));
    assertEquals(341, best.countNumber(1234));
  }
}
