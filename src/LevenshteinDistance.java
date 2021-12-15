import java.util.Scanner;

public class LevenshteinDistance {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    System.out.print("String 1: ");
    String s1 = sc.nextLine();
    System.out.print("String 2: ");
    String s2 = sc.nextLine();

    System.out.printf("Similarity of: %.1f %%", similarity(s1, s2) * 100);
    sc.close();
  }

  static double similarity(String s1, String s2) {
    String longer = s1;
    String shorter = s2;
    if (s1.length() < s2.length()) {
      longer = s2;
      shorter = s1;
    }
    double longerLength = longer.length();
    if (longerLength == 0) {
      return 1;
    }
    return (longerLength - editDistance(longer, shorter)) / longerLength;
  }

  static int editDistance(String longer, String shorter) {
    longer = longer.toLowerCase().replace(" ", "");
    shorter = shorter.toLowerCase().replace(" ", "");

    int costs[] = new int[longer.length() + 1];

    for (int i = 0; i <= longer.length(); i++) {
      int lastValue = i;
      for (int j = 0; j <= shorter.length(); j++) {
        if (i == 0) {
          costs[j] = j;
        } else {
          if (j > 0) {
            int newValue = costs[j - 1];
            if (longer.charAt(i - 1) != shorter.charAt(j - 1)) {
              newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
            }
            costs[j - 1] = lastValue;
            lastValue = newValue;
          }
        }
      }
      if (i > 0) costs[shorter.length()] = lastValue;
    }
    return costs[shorter.length()];
  }
}
