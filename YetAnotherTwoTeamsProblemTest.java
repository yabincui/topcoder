import org.junit.*;
import static org.junit.Assert.*;

public class YetAnotherTwoTeamsProblemTest {
  YetAnotherTwoTeamsProblem team = new YetAnotherTwoTeamsProblem();

  @Test
  public void test() {
    assertEquals(2, team.count(new int[]{5, 4, 7, 6}));
    assertEquals(10, team.count(new int[]{1, 1, 1, 1, 1}));
    assertEquals(5, team.count(new int[]{1, 2, 3, 5, 10}));
    assertEquals(0, team.count(new int[]{1, 2, 3, 4, 10}));
    assertEquals(17672631900L, team.count(new int[]
    {999, 999, 999, 1000, 1000, 1001, 999, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,
 1000, 1000, 1000, 999, 1000, 512, 511, 1001, 1001, 1001, 1001, 1001, 1000}));
  }
}
