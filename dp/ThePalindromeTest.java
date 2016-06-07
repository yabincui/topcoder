import org.junit.*;
import static org.junit.Assert.*;

public class ThePalindromeTest {
  ThePalindrome palin = new ThePalindrome();

  @Test
  public void test1() {
    assertEquals(5, palin.find("abab"));
    assertEquals(7, palin.find("abacaba"));
    assertEquals(11, palin.find("qwerty"));
    assertEquals(38, palin.find("abdfhdyrbdbsdfghjkllkjhgfds"));
  }
}
