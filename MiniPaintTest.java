import org.junit.*;
import static org.junit.Assert.*;

public class MiniPaintTest {
  MiniPaint paint = new MiniPaint();

  @Test
  public void test1() {
    assertEquals(0, paint.leastBad(new String[]{
"BBBBBBBBBBBBBBB",
"WWWWWWWWWWWWWWW",
"WWWWWWWWWWWWWWW",
"WWWWWBBBBBWWWWW"}, 6));
  }

  @Test
  public void test2() {
    assertEquals(5, paint.leastBad(new String[]{
"BBBBBBBBBBBBBBB",
"WWWWWWWWWWWWWWW",
"WWWWWWWWWWWWWWW",
"WWWWWBBBBBWWWWW"}, 4));
  }

  @Test
  public void test3() {
    assertEquals(60, paint.leastBad(new String[]{
"BBBBBBBBBBBBBBB",
"WWWWWWWWWWWWWWW",
"WWWWWWWWWWWWWWW",
"WWWWWBBBBBWWWWW"}, 0));
  }

  @Test
  public void test4() {
    assertEquals(40, paint.leastBad(new String[]{
"BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
"BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
"BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
"BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
"BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
"BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW"}, 100));
  }

  @Test
  public void test5() {
    assertEquals(0, paint.leastBad(new String[]{"B"}, 1));
  }
}
