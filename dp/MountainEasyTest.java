import org.junit.*;
import static org.junit.Assert.*;

public class MountainEasyTest {
  MountainEasy easy = new MountainEasy();

  @Test
  public void test() {
    assertEquals(1, easy.countPlacements(new String[]
{"X.",
 "XX"}, 1));
    assertEquals(5, easy.countPlacements(new String[]
{"X.",
 "XX"}, 2));
    assertEquals(2, easy.countPlacements(new String[]
{"X.X",
 "XXX"}, 2));
    assertEquals(24, easy.countPlacements(new String[]
{"X.X",
 "XXX"}, 3));
    assertEquals(6, easy.countPlacements(new String[]
{"......",
 "X..X..",
 "XXXXXX",
 "XXXXXX"}, 3));
    assertEquals(300, easy.countPlacements(new String[]
{"......",
 "X..X..",
 "XXXXXX",
 "XXXXXX"}, 4));
    assertEquals(764632413, easy.countPlacements(new String[]
{"....X...X..",
 "...XXX.XXX.",
 "XXXXXXXXXXX"}, 10));
  }
}
