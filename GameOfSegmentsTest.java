import org.junit.*;
import static org.junit.Assert.*;

public class GameOfSegmentsTest {
  GameOfSegments game = new GameOfSegments();

  @Test
  public void test() {
    game.enumerate(1000);
  }
}
