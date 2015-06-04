import org.junit.*;
import static org.junit.Assert.*;

public class DistinctRemaindersTest {
  DistinctRemainders rem = new DistinctRemainders();

  @Test
  public void test() {
    assertEquals(5, rem.howMany(3, 2));
    assertEquals(9, rem.howMany(3, 3));
    assertEquals(1, rem.howMany(58, 1));
    assertEquals(922572833L, rem.howMany(572, 7));
    assertEquals(240297629L, rem.howMany(1000000000000000000L, 20));    
  }
}
