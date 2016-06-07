import org.junit.*;
import static org.junit.Assert.*;

public class SRMCardsTest {
  SRMCards cards = new SRMCards();

  @Test
  public void test1() {
    assertEquals(1, cards.maxTurns(new int[]{498, 499}));
    assertEquals(4, cards.maxTurns(new int[]
{491, 492, 495, 497, 498, 499}));
    assertEquals(4, cards.maxTurns(new int[]
{100, 200, 300, 400}));        
    assertEquals(6, cards.maxTurns(new int[]
    {11, 12, 102, 13, 100, 101, 99, 9, 8, 1}));
    assertEquals(4, cards.maxTurns(new int[]
    {118, 321, 322, 119, 120, 320}));
    assertEquals(7, cards.maxTurns(new int[]
    {10, 11, 12, 13, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
  }
}
