import org.junit.*;
import static org.junit.Assert.*;

public class FoxAverageSequenceTest {
  FoxAverageSequence fox = new FoxAverageSequence();

  @Test
  public void test() {
    assertEquals(4, fox.theCount(new int[]{3, -1}));
    assertEquals(2, fox.theCount(new int[]{5, 3, -1}));
    assertEquals(0, fox.theCount(new int[]{-1, 0, 40}));
    assertEquals(579347890, fox.theCount(new int[]{-1, 40, -1, -1, -1, 10, -1, -1, -1, 21, -1}));
    assertEquals(58, fox.theCount(new int[]{-1, 12, 25, 0, 18, -1}));
  }
}
