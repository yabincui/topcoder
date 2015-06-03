import org.junit.*;
import static org.junit.Assert.*;

public class WolfInZooDivTwoTest {
  WolfInZooDivTwo wolf = new WolfInZooDivTwo();

  @Test
  public void test() {
    assertEquals(27, wolf.count(5, new String[]
{"0 1"}, new String[]
{"2 4"}));
    assertEquals(798, wolf.count(10, new String[]
    {"0 4 2 7"}, new String[]
{"3 9 5 9"}));
    assertEquals(250671525, wolf.count(100, new String[]
    {"0 2 2 7 10 1","3 16 22 30 33 38"," 42 44 49 51 57 60 62"," 65 69 72 74 77 7","8 81 84 88 91 93 96"},
    new String[]
{"41 5 13 22 12 13 ","33 41 80 47 40 ","4","8 96 57 66 ","80 60 71 79"," 70 77 ","99"," 83 85 93 88 89 97 97 98"}));
    assertEquals(6, wolf.count(3, new String[]
    {"1"}, new String[]
{"2"}));
  }
}
