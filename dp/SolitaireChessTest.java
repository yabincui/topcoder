import org.junit.*;
import static org.junit.Assert.*;

public class SolitaireChessTest {
  SolitaireChess chess = new SolitaireChess();

  @Test
  public void test() {
    assertEquals(7, chess.transform(new String[]
{"...N....",
 "........",
 "........",
 "........",
 "........",
 "........",
 "...P....",
 "........"}, new String[]
{"...N....",
 ".....N..",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........"}));
    assertEquals(-1, chess.transform(new String[]
    {"........",
 "........",
 "...P....",
 "........",
 "........",
 "........",
 "........",
 "........"}, new String[]
{"........",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........",
 "...P...."}));
    assertEquals(5, chess.transform(new String[]
{"........",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........",
 ".N...P.."}, new String[]
{"........",
 "........",
 "........",
 "........",
 "........",
 "........",
 ".....P..",
 ".......N"}));
    assertEquals(-1, chess.transform(new String[]
    {"N.......",
 "........",
 "N.......",
 "........",
 "........",
 "........",
 "........",
 "........"}, new String[]
{"........",
 "..N.....",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........"}));
    assertEquals(23, chess.transform(new String[]
    {"..N.N...",
 "PPP....N",
 "..N..N..",
 "N...N...",
 "...NNNNN",
 "N.......",
 "...NN...",
 "..N...N."}, new String[]
{"..N....N",
 "P....N..",
 "..N..N..",
 "N..NNN.N",
 "N.....N.",
 "N.N.....",
 "...NNN..",
 ".....N.N"}));
  }
}