import org.junit.*;
import static org.junit.Assert.*;

public class OrderOfTheHatsTest {
  OrderOfTheHats hat = new OrderOfTheHats();

  @Test
  public void test() {
    assertEquals(1, hat.minChanged(new String[]{"Y"}));
    assertEquals(0, hat.minChanged(new String[]
    {"NYN",
     "NNY",
     "NNN"}));
    assertEquals(1, hat.minChanged(new String[]
{"NYN",
 "NNY",
 "YNN"}));
    assertEquals(21, hat.minChanged(new String[]
{"NYYYYYY",
 "YNYYYYY",
 "YYNYYYY",
 "YYYNYYY",
 "YYYYNYY",
 "YYYYYNY",
 "YYYYYYN"}));
    assertEquals(1, hat.minChanged(new String[]
{"NNNY",
 "YNYN",
 "YNNN",
 "YYYN"}));
    assertEquals(79, hat.minChanged(new String[]
{"YYYYYNNYYYNYNNNNYNNY",
 "NYNNNYYNNYNYYYNYYYYY",
 "NNYNNNYYNNNNNNYYYYNY",
 "YYNYNYYNNYYYNYNNNYYY",
 "NYYNNYNYNYNNNNYYYNYN",
 "NNNNNYYNYNNYYYYNYYYN",
 "YNYNYYNNNYNNNNNYNNYY",
 "NYYYYNYNYNNYNNYNNNNY",
 "YYYYNYYNNYYYNNYNNYNY",
 "YYYYYYNYNYNYNNNNNNYN",
 "NNYYYYYNNNYNNNYNNNNY",
 "YYNNNYNYYNYYNYYNYNYN",
 "NNYNYYNYYNYYNYNYNYYN",
 "YNYNYYNYNNNYNYNYYNYY",
 "NNYNNNYYYYYYYYYYYNYY",
 "YYYYYNYYNYYYYYNNYNNN",
 "NYYYYYYYYNNNNNYYNNYN",
 "YNNYNNNYYNYYYNYNYYYY",
 "YYNNYNYYYNYYNNNYYNNY",
 "NNYNYNYYYNYYNYNNYNNN"}));
    assertEquals(5, hat.minChanged(new String[]
{"YYNYNN",
 "YNYNNY",
 "YYYYNN",
 "NNNYNN",
 "NNNYNN",
 "YNYNYN"}));
    assertEquals(6, hat.minChanged(new String[]
{"NNNNNNNNNN",
 "NNNNNNNNNN",
 "NNNYNNYNNN",
 "NNNYNNYNNN",
 "NNNYNNYNNN",
 "NNNNNNNNNN",
 "NNYYYYYYNN",
 "NNYNNNNYNN",
 "NNNYYYYNNN",
 "NNNNNNNNNN"}));
  }
}
