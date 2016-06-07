import org.junit.*;
import static org.junit.Assert.*;

public class TheTicketsDivTwoTest {
  TheTicketsDivTwo ticket = new TheTicketsDivTwo();

  @Test
  public void test() {
    assertEquals(0.16666666666666666, ticket.find(2, 1, 1), 1e-9);
    assertEquals(0.5833333333333334, ticket.find(2, 1, 2), 1e-9);
    assertEquals(0.0, ticket.find(7, 7, 4), 1e-9);
    assertEquals(0.25264033564814814, ticket.find(4, 2, 10), 1e-9);
  }
}
