import org.junit.*;
import static org.junit.Assert.*;

public class FoxAndSortingTest {
  FoxAndSorting fox = new FoxAndSorting();

  @Test
  public void test() {
    assertEquals(0L, fox.get(1)); 
    assertEquals(1L, fox.get(2));
    long value = 1L;
    for (int i = 0; i < 17; ++i) {
      value *= 10;
    }
    assertEquals(value, fox.get(19));
    assertEquals(value + 1, fox.get(20));
    assertEquals(value / 10 + 1, fox.get(21));

    assertEquals(100000000000000011L, fox.get(192));
    assertEquals(10000000000000002L, fox.get(193));
    
    assertEquals(100000000L, fox.get(10));
    assertEquals(999999999999999999L, fox.get(1000000000000000000L));
    assertEquals(999999999999999989L, fox.get(999999999999999998L));
    assertEquals(100000000100L, fox.get(58));
    assertEquals(132558071125756493L, fox.get(271828182845904523L));
    assertEquals(646003042230121105L, fox.get(314159265358979L));
  }
}
