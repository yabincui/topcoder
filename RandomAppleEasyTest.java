import org.junit.*;
import static org.junit.Assert.*;

public class RandomAppleEasyTest {
  RandomAppleEasy apple = new RandomAppleEasy();
  
  @Test
  public void test() {
    assertEquals(0.38461538461538464, apple.theRed(new int[]{5}, new int[]{8}),
        1e-9);
    assertEquals(0.5888888888888888, apple.theRed(new int[]
        {1, 2}, new int[]
    {1, 1}), 1e-9);
    assertEquals(0.4999999999999999, apple.theRed(new int[]
        {2, 5, 6, 4, 9, 10, 6, 2}, new int[]
    {2, 5, 6, 4, 9, 10, 6, 2}), 1e-9);
    assertEquals(0.5429014970733334, apple.theRed(new int[]
        {2, 5, 6, 4, 9, 10, 6, 2}, new int[]
    {6, 7, 4, 5, 3, 2, 9, 1}), 1e-9);
    assertEquals(0.46460213827476854, apple.theRed(new int[]
        {5, 1, 2, 8, 4, 1, 1, 2, 3, 4, 5, 2, 10, 2, 6, 2, 8, 7, 9, 3}, new int[]
    {4, 7, 1, 1, 10, 3, 4, 1, 6, 2, 7, 6, 10, 5, 2, 9, 3, 8, 1, 8}), 1e-9);
  }
}