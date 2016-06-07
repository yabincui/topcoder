import org.junit.*;
import static org.junit.Assert.*;

public class ShopPositionsTest {
  @Test
  public void test() {
    ShopPositions shop = new ShopPositions();
    assertEquals(300, shop.maxProfit(1, 5, new int[]
{100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 1, 1, 1, 1, 1}));    
    assertEquals(1000, shop.maxProfit(1, 5, new int[]
    {1000, 5, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    assertEquals(14, shop.maxProfit(3, 1, new int[]
    {
  7,6,1,
  10,4,1,
  7,6,3
}));
    assertEquals(24, shop.maxProfit(2, 2, new int[]
    {
 12,11,10,9,8,7,
 6,5,4,3,2,1
}));
    assertEquals(127, shop.maxProfit(3, 3, new int[]
    {
  30,28,25,15,14,10,5,4,2,
  50,40,30,28,17,13,8,6,3,
  45,26,14,14,13,13,2,1,1
}));
  }
}
