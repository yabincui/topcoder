import org.junit.*;
import static org.junit.Assert.*;

public class WolfPackDivTwoTest {
  WolfPackDivTwo wolf = new WolfPackDivTwo();

  @Test
  public void test() {
    assertEquals(1, wolf.calc(new int[]{3, 5}, new int[]{0, 0}, 1));
    assertEquals(0, wolf.calc(new int[]{0, 1}, new int[]{0, 0}, 1));
    assertEquals(4, wolf.calc(new int[]{0, 2, 4}, new int[]{0, 0, 0}, 2));
    assertEquals(2, wolf.calc(new int[]{7, 8}, new int[]{8, 7}, 1));
    assertEquals(0, wolf.calc(new int[]
    {0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,4,4,4,4,4,4,4,4,4,4,6,6,6,6,6,6,6,6,6,6,8,8,8,8,8,8,8,8,8,8},
    new int[]
{0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18}, 12));
    assertEquals(573748580, wolf.calc(new int[]
{0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,4,4,4,4,4,4,4,4,4,4,6,6,6,6,6,6,6,6,6,6,8,8,8,8,8,8,8,8,8,8},
    new int[]
{0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18,0,2,4,6,8,10,12,14,16,18}, 31));
  }
}
