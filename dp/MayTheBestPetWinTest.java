import org.junit.*;
import static org.junit.Assert.*;

public class MayTheBestPetWinTest {
  MayTheBestPetWin win = new MayTheBestPetWin();

  @Test
  public void test() {
    assertEquals(2, win.calc(new int[]{3, 4, 4, 7}, new int[]{3, 4, 4, 7}));
    assertEquals(5, win.calc(new int[]{1,3,5,4,5}, new int[]{2,5,6,8,7}));
    assertEquals(52873, win.calc(new int[]
    {2907,949,1674,6092,8608,5186,2630,970,1050,2415,1923,2697,5571,6941,8065,4710,716,756,5185,1341,993,5092,248,1895,4223,1783,3844,3531,2431,1755,2837,4015},
        new int[]
{7296,6954,4407,9724,8645,8065,9323,8433,1352,9618,6487,7309,9297,8999,9960,5653,4721,7623,6017,7320,3513,6642,6359,3145,7233,5077,6457,3605,2911,4679,5381,6574}));
  }
}
