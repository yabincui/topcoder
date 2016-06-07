import org.junit.*;
import static org.junit.Assert.*;

public class PalindromicSubstringsDiv2Test {
  PalindromicSubstringsDiv2 palin = new PalindromicSubstringsDiv2();

  @Test
  public void test1() {
    assertEquals(6, palin.count(new String[]{"a","a",""}, new String[]{"a"}));
    assertEquals(4, palin.count(new String[]{"zaz"}, new String[]{}));
    assertEquals(8, palin.count(new String[]{"top"}, new String[]{"coder"}));
    assertEquals(7, palin.count(new String[]{}, new String[]{"daata"}));
  }
}
