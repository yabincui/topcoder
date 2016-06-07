import org.junit.*;
import static org.junit.Assert.*;

public class KiloManXTest {
  KiloManX man = new KiloManX();

  @Test
  public void test() {
    assertEquals(218, man.leastShots(new String[]{"070","500","140"}, new int[]
{150,150,150}));
    assertEquals(205, man.leastShots(new String[]
    {"1542", "7935", "1139", "8882"}, new int[]
{150,150,150,150}));
    assertEquals(48, man.leastShots(new String[]
    {"07", "40"}, new int[]
{150,10}));
    assertEquals(260445, man.leastShots(new String[]
    {"198573618294842",
 "159819849819205",
 "698849290010992",
 "000000000000000",
 "139581938009384",
 "158919111891911",
 "182731827381787",
 "135788359198718",
 "187587819218927",
 "185783759199192",
 "857819038188122",
 "897387187472737",
 "159938981818247",
 "128974182773177",
 "135885818282838"}, new int[]
{157, 1984, 577, 3001, 2003, 2984, 5988, 190003,
9000, 102930, 5938, 1000000, 1000000, 5892, 38}));
    assertEquals(92, man.leastShots(new String[]
    {"02111111", "10711111", "11071111", "11104111",
 "41110111", "11111031", "11111107", "11111210"}, new int[]
{28,28,28,28,28,28,28,28}));
  }
}
