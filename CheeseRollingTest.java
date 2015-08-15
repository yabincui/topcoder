import org.junit.*;
import static org.junit.Assert.*;

public class CheeseRollingTest {
  CheeseRolling cheese = new CheeseRolling();
  
  @Test
  public void test() {
    assertArrayEquals(new long[]{0, 2}, cheese.waysToWin(new String[]
        {"NN",
        "YN"}
        ));
    assertArrayEquals(new long[]{8, 0, 16, 0 }, cheese.waysToWin(new String[]
        {"NYNY",
            "NNYN",
            "YNNY",
            "NYNN"}
        ));
    assertArrayEquals(new long[]{4096, 8960, 0, 2048, 23808, 0, 1408, 0 },
        cheese.waysToWin(new String[]
            {"NYNYNYNY",
                "NNYNYNYY",
                "YNNNNNNN",
                "NYYNNYNY",
                "YNYYNYYY",
                "NYYNNNNN",
                "YNYYNYNN",
                "NNYNNYYN"}
            ));
    assertArrayEquals(new long[]{331616878592L, 37267079168L, 2426798866432L, 2606831599616L, 994941665280L, 1162501849088L, 1888166674432L, 4632734203904L, 832881524736L, 84707409920L, 3007127748608L, 55490052096L, 17818550272L, 254672666624L, 629921447936L, 1959311671296L },
        cheese.waysToWin(new String[]
            {"NYNNNNYYNYYNNYNN",
                "NNNNNNNNNYYNYYNY",
                "YYNYYNNNNYYYYYYN",
                "YYNNYYYNYNNYYYNY",
                "YYNNNYYNYYNNNNYY",
                "YYYNNNNYYNNYYNYN",
                "NYYNNYNYNYNYYYYN",
                "NYYYYNNNYYNYNYYY",
                "YYYNNNYNNYYYYNNN",
                "NNNYNYNNNNNNYYNY",
                "NNNYYYYYNYNYYYNN",
                "YYNNYNNNNYNNYNNY",
                "YNNNYNNYNNNNNYNN",
                "NNNNYYNNYNNYNNYY",
                "YYNYNNNNYYYYYNNN",
                "YNYNNYYNYNYNYNYN"}
            ));
    assertArrayEquals(new long[]{20922789888000L, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        cheese.waysToWin(new String[]
            {"NYYYYYYYYYYYYYYY",
                "NNYYYYYYYYYYYYYY",
                "NNNYYYYYYYYYYYYY",
                "NNNNYYYYYYYYYYYY",
                "NNNNNYYYYYYYYYYY",
                "NNNNNNYYYYYYYYYY",
                "NNNNNNNYYYYYYYYY",
                "NNNNNNNNYYYYYYYY",
                "NNNNNNNNNYYYYYYY",
                "NNNNNNNNNNYYYYYY",
                "NNNNNNNNNNNYYYYY",
                "NNNNNNNNNNNNYYYY",
                "NNNNNNNNNNNNNYYY",
                "NNNNNNNNNNNNNNYY",
                "NNNNNNNNNNNNNNNY",
                "NNNNNNNNNNNNNNNN"}
            ));
  }
  
}