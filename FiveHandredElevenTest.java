import org.junit.*;
import static org.junit.Assert.*;

public class FiveHandredElevenTest {
  FiveHandredEleven eleven = new FiveHandredEleven();

  @Test
  public void test() {
    assertEquals("Fox Ciel", eleven.theWinner(new int[]{3, 5, 7, 9, 510}));
    assertEquals("Toastman", eleven.theWinner(new int[]{0, 0, 0, 0}));
    assertEquals("Toastman", eleven.theWinner(new int[]{511}));
    assertEquals("Fox Ciel", eleven.theWinner(new int[]{5, 58, 192, 256}));
  }
}
