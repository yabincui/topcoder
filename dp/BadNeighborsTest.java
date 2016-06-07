import org.junit.*;
import static org.junit.Assert.*;

public class BadNeighborsTest {
  BadNeighbors bad = new BadNeighbors();

  @Test
  public void test1() {
    int[] s = { 10, 3, 2, 5, 7, 8 };
    assertEquals(19, bad.maxDonations(s));
  }
  
  @Test
  public void test2() {
    int[] s = {11, 15};
    assertEquals(15, bad.maxDonations(s));
  }

  @Test
  public void test3() {
    int[] s = {7, 7, 7, 7, 7, 7, 7};
    assertEquals(21, bad.maxDonations(s));
  }

  @Test
  public void test4() {
    int[] s = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
    assertEquals(16, bad.maxDonations(s));
  }

  @Test
  public void test5() {
    int[] s = {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72};
    assertEquals(2926, bad.maxDonations(s));
  }


}
