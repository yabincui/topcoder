import org.junit.*;
import static org.junit.Assert.*;

public class FoxAndShogiTest {
  FoxAndShogi fox = new FoxAndShogi();

  @Test
  public void test() {
    assertEquals(3, fox.differentOutcomes(new String[]
{".D.",
 "...",
 "..."}));    
    assertEquals(3, fox.differentOutcomes(new String[]
    {".D.",
 "...",
 ".U."}));
    assertEquals(3125, fox.differentOutcomes(new String[]
    {"DDDDD",
 ".....",
 ".....",
 ".....",
 "....."}));
    assertEquals(900, fox.differentOutcomes(new String[]
{"DDDDD",
 "U....",
 ".U...",
 "..U..",
 "...U."}));    
    assertEquals(2268, fox.differentOutcomes(new String[]
{"....D..",
 "U....D.",
 "D.D.U.D",
 "U.U...D",
 "....U..",
 "D.U...D",
 "U.U...."}));    
    assertEquals(999999937, fox.differentOutcomes(new String[]
    {"DDDDDDDDDD",
 "..........",
 "..........",
 "..........",
 "..........",
 "..........",
 "..........",
 "..........",
 "..........",
 ".........."}));
    assertEquals(1, fox.differentOutcomes(new String[]
    {"..",
 ".."}));
  }
}
