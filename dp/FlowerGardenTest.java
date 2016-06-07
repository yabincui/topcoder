import org.junit.*;
import static org.junit.Assert.*;

public class FlowerGardenTest {
  FlowerGarden garden = new FlowerGarden();

  @Test
  public void test1() {
    int h[] = {5,4,3,2,1};
    int b[] = {1,1,1,1,1};
    int w[] = {365,365,365,365,365};
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, garden.getOrdering(h, b, w));
  }

  @Test
  public void test2() {
    assertArrayEquals(new int[]{5,4,3,2,1}, garden.getOrdering(new int[]{5, 4, 3, 2, 1},
                      new int[]{1,5,10,15,20}, new int[]{4,9,14,19,24}));
  }

  @Test
  public void test3() {
    assertArrayEquals(new int[]{1,2,3,4,5}, garden.getOrdering(new int[]{5,4,3,2,1},
                      new int[]{1,5,10,15,20}, new int[]{5,10,15,20,25}));
  }

  @Test
  public void test4() {
    assertArrayEquals(new int[]{3,4,5,1,2}, garden.getOrdering(new int[]{5,4,3,2,1},
                      new int[]{1,5,10,15,20}, new int[]{5,10,14,20,25}));
  }

  @Test
  public void test5() {
    assertArrayEquals(new int[]{2,4,6,1,3,5}, garden.getOrdering(new int[]{1,2,3,4,5,6},
                      new int[]{1,3,1,3,1,3}, new int[]{2,4,2,4,2,4}));
  }

  @Test
  public void test6() {
    assertArrayEquals(new int[]{4,5,2,3}, garden.getOrdering(new int[]{3,2,5,4},
                      new int[]{1,2,11,10}, new int[]{4,3,12,13}));
  }
}
