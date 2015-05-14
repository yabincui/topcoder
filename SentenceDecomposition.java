import java.util.*;

public class SentenceDecomposition {
  class Word {
    String word;
    String sortedWord;

    Word(String w) {
      word = w;
      sortedWord = sortString(w);
    }

    int distance(String s, String sortedS) {
      if (sortedWord.equals(sortedS) == false) {
        return -1;
      }
      int dist = 0;
      for (int i = 0; i < s.length(); ++i) {
        if (s.charAt(i) != word.charAt(i)) {
          ++dist;
        }
      }
      return dist;
    }
  };

  String sortString(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }

  public int decompose(String sentence, String[] validWords) {
    Word[] words = new Word[validWords.length];
    for (int i = 0; i < validWords.length; ++i) {
      words[i] = new Word(validWords[i]);
    }
    int[] dp = new int[sentence.length() + 1];
    dp[0] = 0;
    for (int i = 1; i <= sentence.length(); ++i) {
      StringBuilder builder = new StringBuilder();
      dp[i] = -1;
      for (int j = i - 1; j >= 0; --j) {
        builder.insert(0, sentence.charAt(j));
        if (dp[j] == -1) {
          continue;
        }
        String s = builder.toString();
        String sortedS = sortString(s);
        int minDist = -1;
        for (Word word : words) {
          int dist = word.distance(s, sortedS);
          if (dist != -1 && (minDist == -1 || minDist > dist)) {
            minDist = dist;
          }
        }
        if (minDist != -1 && (dp[i] == -1 || dp[i] > dp[j] + minDist)) {
          dp[i] = dp[j] + minDist;
        }
      }
    }
    return dp[sentence.length()];
  }
}
