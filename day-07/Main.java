import java.util.ArrayList;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    int x1 = 0;
    int x2 = 0;

    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> hyperNet = new ArrayList<>();

        int index = 0;
        for (int i = 0; i < line.length() + 1; i++) {
          if (i == line.length()) {
            words.add(line.substring(index, i));
            break;
          }

          if (line.charAt(i) == '[') {
            words.add(line.substring(index, i));
            index = i + 1;
          } else if (line.charAt(i) == ']') {
            hyperNet.add(line.substring(index, i));
            index = i + 1;
          }
        }

        boolean isMiniPalindrome = words.stream().anyMatch(word -> {
          for (int i = 0; i < word.length() - 2; i++) {
            if (isSmallerPalindrome(word.substring(i, i + 3))) {
              String tmp = word.substring(i + 1, i + 3) + word.charAt(i + 1);

              if (hyperNet.stream().anyMatch(h -> h.contains(tmp))) {
                return true;
              }
            }
          }

          return false;
        });

        if (words.stream().anyMatch(Main::hasPalindrome) && !hyperNet.stream().anyMatch(Main::hasPalindrome)) {
          x1++;
        }

        if (isMiniPalindrome) {
          x2++;
        }
      }
    }

    System.out.println(x1);
    System.out.println(x2);
  }

  private static boolean hasPalindrome(String s) {
    for (int i = 0; i < s.length() - 3; i++) {
      if (isPalindrome(s.substring(i, i + 4))) {
        return true;
      }
    }

    return false;
  }

  private static boolean isPalindrome(String s) {
    return s.charAt(0) == s.charAt(3)
        && s.charAt(1) == s.charAt(2)
        && s.charAt(0) != s.charAt(1);
  }

  private static boolean isSmallerPalindrome(String s) {
    return s.charAt(0) == s.charAt(2)
        && s.charAt(0) != s.charAt(1);
  }
}