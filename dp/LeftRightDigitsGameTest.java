import org.junit.*;
import static org.junit.Assert.*;

public class LeftRightDigitsGameTest {
  LeftRightDigitsGame game = new LeftRightDigitsGame();

  @Test
  public void test() {
    assertEquals("556", game.minNumber("565"));
    assertEquals("1234567890", game.minNumber("9876543210"));
    assertEquals("1086352", game.minNumber("8016352"));
  }
}
