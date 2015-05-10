import org.junit.*;
import static org.junit.Assert.*;

public class AstronomicalRecordsEasyTest {
  AstronomicalRecordsEasy easy = new AstronomicalRecordsEasy();

  @Test
  public void test1() {
    assertEquals(5, easy.minimalPlanets(new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 5}));
    assertEquals(4, easy.minimalPlanets(new int[]{1, 2, 3, 4}, new int[]{2, 4, 6, 8}));
    assertEquals(9, easy.minimalPlanets(new int[]{1,2,3,5,6,8,9}, new int[]{2,4,5,6,7,8,9}));
    assertEquals(6, easy.minimalPlanets(new int[]{1,2,3,4}, new int[]{6,7,8,9}));
    assertEquals(9, easy.minimalPlanets(new int[]{200, 500},
                                        new int[]{100,200,300,400,600,700,800,900}));
    assertEquals(15, easy.minimalPlanets(new int[]{1,2,3,4,5,6,7,8,9,10,11,12},
                                         new int[]{6,7,8,9,10,11,12,13,14,15}));
  }
}
