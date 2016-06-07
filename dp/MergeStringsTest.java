import org.junit.*;
import static org.junit.Assert.*;

public class MergeStringsTest {
  MergeStrings merge = new MergeStrings();

  @Test
  public void test() {
    assertEquals("ABCCBC", merge.getmin("??CC??", "ABC", "BCC"));
    assertEquals("", merge.getmin("WHAT?", "THE", "WA"));
    assertEquals("PARROT", merge.getmin("PARROT", "PARROT", ""));
    assertEquals("AAZAZZAAZZA", merge.getmin(
"???????????", "AZZAA", "AZAZZA"));
    assertEquals("KDKDKDKKKDDKDDKKKDKDKKDKDDDKDDDKKDKKKDKKDDKDDDKDKK",
        merge.getmin(
"????K??????????????D???K???K????????K?????K???????",
"KKKKKDKKKDKKDDKDDDKDKK",
"KDKDDKKKDDKDDKKKDKDKKDDDDDDD"));        
  }
}
