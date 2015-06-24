import org.junit.*;
import static org.junit.Assert.*;

public class CuttingGrassTest {
  CuttingGrass grass = new CuttingGrass();

  @Test
  public void test() {
    assertEquals(1, grass.theMin(new int[]{5, 8, 58}, new int[]{2, 1, 1}, 16));
    assertEquals(0, grass.theMin(new int[]{5, 8}, new int[]{2, 1}, 58));
    assertEquals(-1, grass.theMin(new int[]{5, 8}, new int[]{2, 1}, 0));
    assertEquals(5, grass.theMin(new int[]{5, 1, 6, 5, 8, 4, 7},
                  new int[]{2, 1, 1, 1, 4, 3, 2}, 33));
    assertEquals(8, grass.theMin(new int[]
    {49, 13, 62, 95, 69, 75, 62, 96, 97, 22, 69, 69, 52}, new int[]
{7, 2, 4, 3, 6, 5, 7, 6, 5, 4, 7, 7, 4}, 517));
    assertEquals(36, grass.theMin(new int[]
    {1231, 1536, 1519, 1940, 1539, 1385, 1599, 1613, 1394, 1803,
 1763, 1706, 1863, 1452, 1818, 1914, 1386, 1954, 1496, 1722,
 1616, 1862, 1755, 1215, 1233, 1078, 1448, 1241, 1732, 1561,
 1633, 1307, 1844, 1911, 1371, 1338, 1989, 1789, 1656, 1413,
 1929, 1182, 1815, 1474, 1540, 1797, 1586, 1427, 1996, 1202}, new int[]
{86, 55, 2, 86, 96, 71, 81, 53, 79, 22,
 23, 8, 69, 32, 35, 39, 30, 18, 92, 64,
 88, 1, 48, 81, 91, 75, 44, 77, 3, 33,
 9, 52, 56, 4, 19, 73, 52, 18, 8, 77,
 91, 59, 50, 62, 42, 87, 89, 24, 71, 67}, 63601));
  }
}
