import org.junit.*;
import static org.junit.Assert.*;

public class NineEasyTest {
  NineEasy easy = new NineEasy();

  @Test
  public void test() {
    assertEquals(4, easy.count(2, new int[]{1, 2}));
    assertEquals(12, easy.count(2, new int[]{3, 3}));
    assertEquals(16, easy.count(2, new int[]{1, 3, 2}));
    assertEquals(893703876, easy.count(5, new int[]
    {1,2,4,8,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}));
    assertEquals(200, easy.count(1, new int[]{0, 0, 1}));
  }
}
