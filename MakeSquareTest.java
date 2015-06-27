import org.junit.*;
import static org.junit.Assert.*;

public class MakeSquareTest {
  MakeSquare square = new MakeSquare();

  @Test
  public void test() {
    assertEquals(1, square.minChanges("abcdabgcd"));
    assertEquals(1, square.minChanges("abcdeabce"));
    assertEquals(1, square.minChanges("abcdeabxde"));
    assertEquals(0, square.minChanges("aabcaabc"));
    assertEquals(2, square.minChanges("aaaaabaaaaabaaaaa"));
  }
}
