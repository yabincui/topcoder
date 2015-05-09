import org.junit.*;
import static org.junit.Assert.*;

public class EmoticonsDiv2Test {
  EmoticonsDiv2 emo = new EmoticonsDiv2();

  @Test
  public void test1() {
    assertEquals(2, emo.printSmiles(2));
    assertEquals(5, emo.printSmiles(6));
    assertEquals(11, emo.printSmiles(11));
    assertEquals(8, emo.printSmiles(16));
    assertEquals(21, emo.printSmiles(1000));
  }
}
