import org.junit.*;
import static org.junit.Assert.*;

public class SRMSystemTestPhaseTest {
  SRMSystemTestPhase phase = new SRMSystemTestPhase();

  @Test
  public void test() {
    assertEquals(27, phase.countWays(new String[]{"YYY"}));
    assertEquals(6, phase.countWays(new String[]
    {"YNN",
 "NYN"}));
    assertEquals(4, phase.countWays(new String[]
    {"YNN",
 "NNN",
 "NNY"}));
    assertEquals(1, phase.countWays(new String[]
    {"NNN",
 "NNN"}));
    assertEquals(15176, phase.countWays(new String[]
    {"YYY",
 "YNY",
 "NYY",
 "YYN",
 "YYY",
 "YNN"}));
  }
}
