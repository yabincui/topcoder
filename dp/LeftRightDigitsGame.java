public class LeftRightDigitsGame {
  public String minNumber(String digits) {
    String s = new String();
    String sNoZero = new String();

    s += digits.charAt(0);
    sNoZero += digits.charAt(0);

    for (int i = 1; i < digits.length(); ++i) {
      char c = digits.charAt(i);
      String ns;
      String nsNoZero;
      if (c != '0') {
        String s1 = c + s;
        String s2 = s + c;
        ns = minString(s1, s2);
        
        if (sNoZero.charAt(0) == '0') {
          nsNoZero = c + sNoZero;
        } else {
          String sNoZero1 = c + s;
          String sNoZero2 = sNoZero + c;
          nsNoZero = minString(sNoZero1, sNoZero2);
        }
      } else {
        ns = c + s;
        nsNoZero = sNoZero + c;
      }
      s = ns;
      sNoZero = nsNoZero;
    }
    return sNoZero;
  }

  String minString(String s1, String s2) {
    if (s1.compareTo(s2) < 0) {
      return s1;
    }
    return s2;
  }
}
