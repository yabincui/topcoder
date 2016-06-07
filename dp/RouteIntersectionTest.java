import org.junit.*;
import static org.junit.Assert.*;

public class RouteIntersectionTest {
  RouteIntersection route = new RouteIntersection();

  @Test
  public void test() {
    assertEquals("VALID", route.isValid(1, new int[]{1}, "+"));
    assertEquals("NOT VALID", route.isValid(2, new int[]{1, 2, 1, 2}, "++--"));
    assertEquals("VALID", route.isValid(3, new int[]{1, 2, 3, 1, 2}, "+++--"));
    assertEquals("NOT VALID", route.isValid(344447,
        new int[]{132,51717,628,344447,628,51717,344447,2}, 
        "+-++-+--"));
    assertEquals("NOT VALID", route.isValid(1, new int[]{1, 1}, "+-"));
    assertEquals("NOT VALID", route.isValid(990630,
        new int[]{833196,524568,361663,108056,28026,824639,269315,440977,440977,765458,
988451,242440,948414,130873,773990,765458,130873,28026,853121,553636,
581069,82254,735536,833196,898562,898562,940783,988451,540613,317306,
623194,940783,571384,988451,108056,514374,97664},
        "--+---+-+++-+-+---++-++-+---+-+--+-++"));
  }
}
