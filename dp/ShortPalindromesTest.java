import org.junit.*;
import static org.junit.Assert.*;

public class ShortPalindromesTest {
  ShortPalindromes palin = new ShortPalindromes();

  @Test
  public void test1() {
    assertEquals("AA", palin.shortest("AA"));
  }
  
  @Test
  public void test2() {
    assertEquals("ARA", palin.shortest("AR"));
  }
  
  @Test
  public void test3() {
    assertEquals("ACECA", palin.shortest("ACE"));
  }
  
  @Test
  public void test4() {
    assertEquals("REDTOCPCOTDER", palin.shortest("TOPCODER"));
  }

  @Test
  public void test5() {
    assertEquals("Q", palin.shortest("Q"));
  }

  @Test
  public void test6() {
    assertEquals("MADAMIMADAM", palin.shortest("MADAMIMADAM"));
  }

  @Test
  public void test7() {
    assertEquals("AFLRCAGIOEOUAEOCEGRURGECOEAUOEOIGACRLFA", palin.shortest("ALRCAGOEUAOEURGCOEUOOIGFA"));
  }
}
