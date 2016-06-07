import org.junit.*;
import static org.junit.Assert.*;

public class RGBStreetTest {
  RGBStreet street = new RGBStreet();

  @Test
  public void test() {
    assertEquals(3, street.estimateCost(
        new String[]{"1 100 100", "100 1 100", "100 100 1"}));
    assertEquals(102, street.estimateCost(
        new String[]{"1 100 100", "100 100 100", "1 100 100"}));
    assertEquals(96, street.estimateCost(
        new String[]{"26 40 83", "49 60 57", "13 89 99"}));
    assertEquals(208, street.estimateCost(
        new String[]{"30 19 5", "64 77 64", "15 19 97", "4 71 57", "90 86 84", "93 32 91"}));
    assertEquals(253, street.estimateCost(
        new String[]{"71 39 44", "32 83 55", "51 37 63", "89 29 100", 
                     "83 58 11", "65 13 15", "47 25 29", "60 66 19"}));
  }
}
