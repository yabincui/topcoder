import org.junit.*;
import static org.junit.Assert.*;

public class EllysFiveFriendsTest {
  EllysFiveFriends friend = new EllysFiveFriends();

  @Test
  public void test() {
    assertEquals(10, friend.getZero(new int[]{5, 1, 1, 2, 3}));
    assertEquals(0, friend.getZero(new int[]{2, 1, 1, 1, 2}));
    assertEquals(520, friend.getZero(new int[]{3, 4, 1, 4, 5}));
    assertEquals(549119981, friend.getZero(new int[]{42, 666, 1337, 666, 42}));
    assertEquals(165501353, friend.getZero(new int[]{8792, 9483, 6787, 9856, 6205}));
    assertEquals(952019520, friend.getZero(new int[]{10000, 10000, 10000, 10000, 10000}));
  }
}
