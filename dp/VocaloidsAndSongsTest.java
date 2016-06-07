import org.junit.*;
import static org.junit.Assert.*;

public class VocaloidsAndSongsTest {
  VocaloidsAndSongs song = new VocaloidsAndSongs();

  @Test
  public void test() {
    assertEquals(6, song.count(3, 1, 1, 1));
    assertEquals(9, song.count(3, 3, 1, 1));
    assertEquals(0, song.count(50, 10, 10, 10));
    assertEquals(81451692, song.count(18, 12, 8, 9));
    assertEquals(198591037, song.count(50, 25, 25, 25));
  }
}
