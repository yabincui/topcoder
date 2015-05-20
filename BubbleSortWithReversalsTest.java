import org.junit.*;
import static org.junit.Assert.*;

public class BubbleSortWithReversalsTest {
  BubbleSortWithReversals bubble = new BubbleSortWithReversals();

  @Test
  public void test() {
    assertEquals(0, bubble.getMinSwaps(new int[]{6,8,8,7,7}, 1));
    assertEquals(3, bubble.getMinSwaps(new int[]
    {7,2,2,13,5,5,2}, 2));
    assertEquals(12, bubble.getMinSwaps(new int[]
    {12,5,1,10,12,6,6,10,6,8}, 2));

    assertEquals(1, bubble.getMinSwaps(new int[]
    {2,3,1}, 2));
    assertEquals(22, bubble.getMinSwaps(new int[]
    {482,619,619,601,660,660,691,691,77,77,96,77}, 9));
  }
}
