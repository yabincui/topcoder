import org.junit.*;
import static org.junit.Assert.*;

public class TeamBuilderTest {
  TeamBuilder builder = new TeamBuilder();

  @Test
  public void test() {
    assertArrayEquals(new int[]{1, 1}, builder.specialLocations(
    new String[]{"010","000","110"}));
    assertArrayEquals(new int[]{1, 3}, builder.specialLocations(
    new String[]{"0010","1000","1100","1000"}));
    assertArrayEquals(new int[]{5, 5}, builder.specialLocations(
    new String[]{"01000","00100","00010","00001","10000"}));
    assertArrayEquals(new int[]{1, 3}, builder.specialLocations(
    new String[]{"0110000","1000100","0000001","0010000","0110000","1000010","0001000"}));
    
  }
}
