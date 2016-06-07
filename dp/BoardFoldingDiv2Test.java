import org.junit.*;
import static org.junit.Assert.*;

public class BoardFoldingDiv2Test {
  BoardFoldingDiv2 board = new BoardFoldingDiv2();

  @Test
  public void test() {
    assertEquals(1, board.howMany(new String[]{"10", "11"}));
    assertEquals(6, board.howMany(new String[]
{"0",
 "0",
 "0",
 "1",
 "0",
 "0"}));
    assertEquals(84, board.howMany(new String[]
    {"1111111",
 "1111111"}));
    assertEquals(9, board.howMany(new String[]
{"0110",
 "1001",
 "1001",
 "0110"}));    
    assertEquals(1, board.howMany(new String[]
{"000",
 "010",
 "000"}));    
  }
}
