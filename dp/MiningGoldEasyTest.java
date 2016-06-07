import org.junit.*;
import static org.junit.Assert.*;

public class MiningGoldEasyTest {
  MiningGoldEasy easy = new MiningGoldEasy();

  @Test
  public void test() {
    assertEquals(4, easy.GetMaximumGold(2, 2, new int[]{0}, new int[]{0}));
    assertEquals(7, easy.GetMaximumGold(2, 2, new int[]{0, 2}, new int[]{0, 1}));
    assertEquals(15, easy.GetMaximumGold(3, 3, new int[]{0, 3, 3}, new int[]{0, 3, 0}));
    assertEquals(11, easy.GetMaximumGold(3, 4, new int[]{0, 3}, new int[]{4, 1}));
    assertEquals(48, easy.GetMaximumGold(5, 6, new int[]{0, 4, 2, 5, 0}, new int[]{3, 4, 5, 6, 0}));
    assertEquals(16255, easy.GetMaximumGold(557, 919, new int[]
    {81, 509, 428, 6, 448, 149, 77, 142, 40, 405, 109, 247}, new int[]
{653, 887, 770, 477, 53, 637, 201, 863, 576, 393, 512, 243}));
  }
}
