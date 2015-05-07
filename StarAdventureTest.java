import org.junit.*;
import static org.junit.Assert.*;

public class StarAdventureTest {
  StarAdventure star = new StarAdventure();

  @Test
  public void test1() {
    assertEquals(3, star.mostStars(new String[]
 {"01",
 "11"}));
  }

  @Test
  public void test2() {
    assertEquals(450, star.mostStars(new String[]
    {"0999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"
,"9999999999"}));
  }

  @Test
  public void test3() {
    assertEquals(21, star.mostStars(new String[]
{"012"
,"012"
,"012"
,"012"
,"012"
,"012"
,"012"}));    
  }

  @Test
  public void test4() {
    assertEquals(335, star.mostStars(new String[]
{"0123456789",
 "1123456789",
 "2223456789",
 "3333456789",
 "4444456789",
 "5555556789",
 "6666666789",
 "7777777789",
 "8888888889",
 "9999999999"}));
  }
}
