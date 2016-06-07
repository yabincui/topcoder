import org.junit.*;
import static org.junit.Assert.*;

public class StringWeightDiv2Test {
  StringWeightDiv2 weight = new StringWeightDiv2();

  @Test
  public void test() {
    assertEquals(26, weight.countMinimums(1));
    assertEquals(650, weight.countMinimums(2));
    assertEquals(488801539, weight.countMinimums(50));
  }
}
