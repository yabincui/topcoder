import org.junit.*;
import static org.junit.Assert.*;

public class FoxPlayingGameTest {
  FoxPlayingGame game = new FoxPlayingGame();

  @Test
  public void test() {
    assertEquals(6.0, game.theMax(1, 1, 3000, 2000), 1e-9);
    assertEquals(240.0, game.theMax(5, 4, 3000, 2000), 1e-9);
    assertEquals(6.0, game.theMax(3, 3, 2000, 100), 1e-9);
    assertEquals(-8.0, game.theMax(4, 3, -2000, 2000), 1e-9);
    assertEquals(160.0, game.theMax(5, 5, 2000, -2000), 1e-9);
    assertEquals(5.62949953421312E17, game.theMax(50, 50, 10000, 2000), 1e-9);
    assertEquals(515323.9982341775, game.theMax(41, 34, 9876, -1234), 1e-9);
  }
}
