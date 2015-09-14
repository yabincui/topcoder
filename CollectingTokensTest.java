import org.junit.*;
import static org.junit.Assert.*;

public class CollectingTokensTest {
  @Test
  public void test() {
    CollectingTokens collecting = new CollectingTokens();

    assertEquals(8, collecting.maxTokens(new int[]{1}, new int[]{2}, new int[]{7, 1}, 6));
    assertEquals(14, collecting.maxTokens(new int[]{3, 1}, new int[]{2, 2},
    new int[]{2, 3, 9}, 5));
    assertEquals(16, collecting.maxTokens(new int[]
{1,2,5,3}, new int[]
{4,4,1,4}, new int[]
{6,1,6,4,4},
3));
    assertEquals(26, collecting.maxTokens(new int[]
    {9,1,7,10,5,8,3,4,2}, new int[]
{6,6,9,6,6,1,1,6,6}, new int[]
{4,2,1,6,3,7,8,5,2,9},
4));
    assertEquals(194, collecting.maxTokens(new int[]
    {25,22,35,42,40,9,32,12,37,44,23,1,24,28,20,4,26,33,11,48,34,6,16,50,46,17,8,43,18,30,31,36,39,13,
10,45,3,47,15,2,29,19,7,14,41,49,38,27,21}, new int[]
{5,5,25,25,25,42,25,40,5,35,25,32,42,9,32,23,40,25,20,33,26,37,12,1,48,24,22,25,11,24,48,34,18,9,50,42,16,40,1,
10,47,22,48,44,48,1,4,46,47}, new int[]
{6,9,4,9,5,8,6,4,4,1,4,8,3,4,5,8,5,6,4,9,7,9,7,9,5,2,7,2,7,7,5,9,5,8,5,7,1,9,3,9,3,6,4,5,5,4,7,9,2,2},
48));
  }
}
