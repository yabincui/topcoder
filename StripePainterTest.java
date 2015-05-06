import org.junit.*;
import static org.junit.Assert.*;

public class StripePainterTest {
  StripePainter painter = new StripePainter();

  @Test
  public void test1() {
    assertEquals(1, painter.minStrokes("RRRRR"));
  }

  @Test
  public void test2() {
    assertEquals(3, painter.minStrokes("RGBGR"));
  }

  @Test
  public void test3() {
    assertEquals(3, painter.minStrokes("RGRG"));
  }

  @Test
  public void test4() {
    assertEquals(4, painter.minStrokes("ABACADA"));
  }

  @Test
  public void test5() {
    assertEquals(7, painter.minStrokes("AABBCCDDCCBBAABBCCDD"));
  }

  @Test
  public void test6() {
    assertEquals(26, painter.minStrokes("BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD"));
  }
}
