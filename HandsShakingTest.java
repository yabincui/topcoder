import org.junit.*;
import static org.junit.Assert.*;

public class HandsShakingTest {
  HandsShaking shake = new HandsShaking();

  @Test
  public void test() {
    assertEquals(1, shake.countPerfect(2));
    assertEquals(2, shake.countPerfect(4));
    assertEquals(14, shake.countPerfect(8));
  }
}
