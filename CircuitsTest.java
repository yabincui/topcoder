import org.junit.*;
import static org.junit.Assert.*;

public class CircuitsTest {
  Circuits circuits = new Circuits();

  @Test
  public void test() {
    assertEquals(12, circuits.howLong(new String[]
    {"1 2",
 "2",
 ""}, new String[]
{"5 3",
 "7",
 ""}));
    assertEquals(10, circuits.howLong(new String[]
    {"1 2 3 4 5","2 3 4 5","3 4 5","4 5","5",""}, new String[]
{"2 2 2 2 2","2 2 2 2","2 2 2","2 2","2",""}));
    assertEquals(9, circuits.howLong(new String[]
    {"1","2","3","","5","6","7",""}, new String[]
{"2","2","2","","3","3","3",""}));
    assertEquals(22, circuits.howLong(new String[]
    {"","2 3 5","4 5","5 6","7","7 8","8 9","10",
 "10 11 12","11","12","12",""}, new String[]
{"","3 2 9","2 4","6 9","3","1 2","1 2","5",
 "5 6 9","2","5","3",""}));
    assertEquals(105, circuits.howLong(new String[]
    {"","2 3","3 4 5","4 6","5 6","7","5 7",""}, new String[]
{"","30 50","19 6 40","12 10","35 23","8","11 20",""}));
  
  }
}
