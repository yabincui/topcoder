import org.junit.*;
import static org.junit.Assert.*;

public class StrIIRecTest {
  StrIIRec rec = new StrIIRec();

  @Test
  public void test1() {
    assertEquals("ba", rec.recovstr(2, 1, "ab"));
    assertEquals("efcdgabhi", rec.recovstr(9, 1, "efcdgab"));
    assertEquals("kjihgfedcba", rec.recovstr(11, 55, "debgikjfc"));
    assertEquals("eabcdfghijklmno", rec.recovstr(15, 0, "e"));
    assertEquals("fcdehigba", rec.recovstr(9, 20, "fcdebiha"));
  }
}
