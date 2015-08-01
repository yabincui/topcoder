import org.junit.*;
import static org.junit.Assert.*;

public class DungeonEscapeTest {
  DungeonEscape escape = new DungeonEscape();

  @Test
  public void test() {
    assertEquals(10, escape.exitTime(
new String[]{"0x4",
 "0x3",
 "0x3"},
new String[]{"0x9",
 "009",
 "0x9"},
new String[]{"0x9",
 "1x9",
 "009"},
new String[]{"0x9",
 "0x0",
 "009"},
2,
2));

    assertEquals(30, escape.exitTime(
    new String[]{"xxxxxxxxx1",
                 "1xxxxxxxxx",
                 "xxxxxxxxx1"},
    new String[]{"xxxxxxxxxx",
                 "xxxxxxxxxx",
                 "xxxxxxxxxx"},
    new String[]{"1111111111",
                 "xxxxxxxxxx",
                 "1111111111"},
    new String[]{"xxxxxxxxxx",
                 "1111111111",
                 "xxxxxxxxxx"},
2,
0));
    assertEquals(-1, escape.exitTime(
    new String[]{"xxxxxxxxx1",
                 "xxxx999991",
                 "x999999991"},
    new String[]{"1111111111",
                 "1111111111",
                 "1111111111"},
    new String[]{"1111122242",
                 "2222222241",
                 "2111111111"},
    new String[]{"xxxxxxxxx1",
                 "1111111111",
                 "xxxxxxxxx1"},
2,
0));
    assertEquals(17, escape.exitTime(
    new String[]{"1x2x3x4x5x6x7x8x9",
 "00000000000000000",
 "98765432223456789",
 "12345678987654321"},
new String[]{"00000000000000000",
 "00000000000000000",
 "00000000000000000",
 "00000000000000000"},
new String[]{"xxxxxxxxxxxxxxxxx",
 "xxxxxxxxxxxxxxxxx",
 "22222222222222222",
 "33333333333333333"},
new String[]{"xxxxxxxxxxxxxxxxx",
 "xxxxxxxxxxxxxxxxx",
 "22222222222222222",
 "33333333333333333"},
3,
12));
  }
}
