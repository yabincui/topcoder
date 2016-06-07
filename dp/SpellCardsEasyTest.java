import org.junit.*;
import static org.junit.Assert.*;

public class SpellCardsEasyTest {
  SpellCardsEasy card = new SpellCardsEasy();

  @Test
  public void test() {
    assertEquals(60, card.maxDamage(new int[]{1, 1, 1}, new int[]{10, 20, 30}));
    assertEquals(10, card.maxDamage(new int[]{3, 3, 3}, new int[]{10, 20, 30}));
    assertEquals(0, card.maxDamage(new int[]{4, 4, 4}, new int[]{10, 20, 30}));
    assertEquals(60, card.maxDamage(new int[]{50, 1, 50, 1, 50}, new int[]{10, 20, 30, 40, 50}));
    assertEquals(80, card.maxDamage(new int[]{2, 1, 1}, new int[]{40, 40, 10}));
    assertEquals(150, card.maxDamage(new int[]{1,2,1,1,3,2,1}, new int[]
{10,40,10,10,90,40,10}));
    assertEquals(1577, card.maxDamage(new int[]{1,2,2,3,1,4,2}, new int[]
{113,253,523,941,250,534,454}));
  }
}
