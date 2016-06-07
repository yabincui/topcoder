import org.junit.*;
import static org.junit.Assert.*;

public class GameOfSegmentsTest {
  GameOfSegments game = new GameOfSegments();

  @Test
  public void test() {
    assertEquals(1, game.winner(3));
    assertEquals(1, game.winner(4));
    assertEquals(2, game.winner(15));
    assertEquals(2, game.winner(191));
  }
}
