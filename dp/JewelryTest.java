import org.junit.*;
import static org.junit.Assert.*;

public class JewelryTest {
  Jewelry jewelry = new Jewelry();

  @Test
  public void test1() {
    assertEquals(9L, jewelry.howMany(new int[]{1, 2, 5, 3, 4, 5}));
  }

  @Test
  public void test2() {
    assertEquals(18252025766940L, jewelry.howMany(new int[]{1000,1000,1000,1000,1000,
 1000,1000,1000,1000,1000,
 1000,1000,1000,1000,1000,
 1000,1000,1000,1000,1000,
 1000,1000,1000,1000,1000,
 1000,1000,1000,1000,1000}));
  }

  @Test
  public void test3() {
    assertEquals(4, jewelry.howMany(new int[]{1,2,3,4,5}));
  }

  @Test
  public void test4() {
    assertEquals(607, jewelry.howMany(new int[]{7,7,8,9,10,11,1,2,2,3,4,5,6}));
  }

  @Test
  public void test5() {
    assertEquals(0, jewelry.howMany(new int[]{123,217,661,678,796,964,54,111,417,526,917,923}));
  }
}
