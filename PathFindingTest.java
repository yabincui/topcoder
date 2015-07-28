import org.junit.*;
import static org.junit.Assert.*;

public class PathFindingTest {
  PathFinding path = new PathFinding();

  @Test
  public void test() {
    assertEquals(2, path.minTurns(new String[]
    {"....",
 ".A..",
 "..B.",
 "...."}));
    assertEquals(-1, path.minTurns(new String[]
{"XXXXXXXXX",
 "A...X...B",
 "XXXXXXXXX"}));    
    assertEquals(-1, path.minTurns(new String[]
{"XXXXXXXXX",
 "A.......B",
 "XXXXXXXXX"}));    
    assertEquals(8, path.minTurns(new String[]
{"XXXXXXXXX",
 "A.......B",
 "XXXX.XXXX"}));    
    assertEquals(13, path.minTurns(new String[]
    {"...A.XXXXX.....",
 ".....XXXXX.....",
 "...............",
 ".....XXXXX.B...",
 ".....XXXXX....."}));
    assertEquals(379, path.minTurns(new String[]
    {"AB.................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "...................X",
 ".XXXXXXXXXXXXXXXXXXX"}));
  }
}
