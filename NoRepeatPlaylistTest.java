import org.junit.*;
import static org.junit.Assert.*;

public class NoRepeatPlaylistTest {
  NoRepeatPlaylist play = new NoRepeatPlaylist();

  @Test
  public void test() {
    assertEquals(1, play.numPlaylists(1, 0, 3));
    assertEquals(0, play.numPlaylists(1, 1, 3));
    assertEquals(2, play.numPlaylists(2, 0, 2));
    assertEquals(6, play.numPlaylists(2, 0, 3));
    assertEquals(24, play.numPlaylists(4, 0, 4));
    assertEquals(2, play.numPlaylists(2, 1, 4));
    assertEquals(222288991, play.numPlaylists(50, 5, 100));
  }
}
