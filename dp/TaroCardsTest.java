import org.junit.*;
import static org.junit.Assert.*;

public class TaroCardsTest {
  TaroCards card = new TaroCards();

  @Test
  public void test() {
    assertEquals(3, card.getNumber(new int[]{1, 2}, new int[]{2, 3}, 2));
    assertEquals(8, card.getNumber(new int[]{3, 1, 2}, new int[]{1, 1, 1}, 3));
    assertEquals(16, card.getNumber(new int[]{4, 2, 1, 3}, new int[]{1, 2, 3, 4}, 5));
    assertEquals(17, card.getNumber(new int[]{1, 2, 3, 4, 5, 6, 7},
        new int[]{2, 1, 10, 9, 3, 2, 2}, 3));
    assertEquals(1, card.getNumber(new int[]{1}, new int[]{2}, 1));
    assertEquals(2239000L, card.getNumber(new int[]
    {6, 20, 1, 11, 19, 14, 2, 8, 15, 21, 9, 10, 4, 16, 12, 17, 13, 22, 7, 18, 3, 5},
    new int[]
    {4, 5, 10, 7, 6, 2, 1, 10, 10, 7, 9, 4, 5, 9, 5, 10, 10, 3, 6, 6, 4, 4},
    14));
  }
}
