import org.junit.*;
import static org.junit.Assert.*;

public class TwoLLogoTest {
  TwoLLogo logo = new TwoLLogo();

  @Test
  public void test() {
    assertEquals(1, logo.countWays(new String[]{"....", "...."}));
    assertEquals(3, logo.countWays(new String[]
{".##..",
 "...#.",
 ".#.#.",
 "#...#"}));
    assertEquals(4, logo.countWays(new String[]
{"..#.",
 "#.#.",
 "....",
 "..#."}));    
    assertEquals(0, logo.countWays(new String[]
{"..",
 ".."}));    
    assertEquals(34, logo.countWays(new String[]
{".#.#",
 "....",
 ".#.#",
 "...."}));    
    assertEquals(1350, logo.countWays(new String[]
{"##############",
 "##############",
 "#.############",
 "#.############",
 "#.############",
 "#.############",
 "#.############",
 "#.############",
 "#.#####.######",
 "#.#####.######",
 "#.#####.######",
 "#....##.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######.######",
 "#######......#",
 "##############"}));    
    assertEquals(2386, logo.countWays(new String[]
{"#......",
 ".#....#",
 ".#.#...",
 "#....#.",
 ".##..#.",
 ".#.....",
 ".....#.",
 ".#.#...",
 ".#...#.",
 "..##..."}));    
    assertEquals(5020791386L, logo.countWays(new String[]
{"...#..........................",
 "..............................",
 "..............................",
 "..................#...#.......",
 "..................#...........",
 "..............................",
 "...........#..................",
 "..............................",
 ".....#..#.....................",
 ".......................#......",
 "..................#.....#.....",
 "..............................",
 "..............................",
 "..............................",
 "..............................",
 "..#...........................",
 "..............................",
 "..............................",
 "..............................",
 "#............................#",
 "..............................",
 ".....#.........#............#.",
 "..............................",
 ".........................#....",
 ".#............................",
 ".............#................",
 "..............................",
 "..............................",
 ".......................#......",
 ".............#................"}));
  }
}
