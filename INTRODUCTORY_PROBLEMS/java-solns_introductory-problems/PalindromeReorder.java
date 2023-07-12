import java.util.*;
import java.io.*;

public class PalindromeReorder {

  static class FastIO extends PrintWriter {
    private InputStream stream;
    private byte[] buf = new byte[1 << 16];
    private int curChar, numChars;

    // standard input
    public FastIO() {
      this(System.in, System.out);
    }

    public FastIO(InputStream i, OutputStream o) {
      super(o);
      stream = i;
    }

    // file input
    public FastIO(String i, String o) throws IOException {
      super(new FileWriter(o));
      stream = new FileInputStream(i);
    }

    // throws InputMismatchException() if previously detected end of file
    private int nextByte() {
      if (numChars == -1)
        throw new InputMismatchException();
      if (curChar >= numChars) {
        curChar = 0;
        try {
          numChars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (numChars == -1)
          return -1; // end of file
      }
      return buf[curChar++];
    }

    // to read in entire lines, replace c <= ' '
    // with a function that checks whether c is a line break
    public String next() {
      int c;
      do {
        c = nextByte();
      } while (c <= ' ');
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = nextByte();
      } while (c > ' ');
      return res.toString();
    }

    public String nextLine() {
      int c;
      do {
        c = nextByte();
      } while (c < '\n');
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = nextByte();
      } while (c > '\n');
      return res.toString();
    }

    public int nextInt() {
      int c;
      do {
        c = nextByte();
      } while (c <= ' ');
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = nextByte();
      }
      int res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res = 10 * res + c - '0';
        c = nextByte();
      } while (c > ' ');
      return res * sgn;
    }

    public long nextLong() {
      int c;
      do {
        c = nextByte();
      } while (c <= ' ');
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = nextByte();
      }
      long res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res = 10 * res + c - '0';
        c = nextByte();
      } while (c > ' ');
      return res * sgn;
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }
  }

  public static void main(String args[]) {
    // Scanner sc = new Scanner(System.in);
    FastIO sc = new FastIO();

    // CODE START

    String s = sc.next();
    SortedMap<Character, Integer> map = new TreeMap<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
    boolean oneCount = true;
    StringBuilder sb = new StringBuilder(s.length());
    char single = '\u0000';
    for (Map.Entry<Character, Integer> e : map.entrySet()) {
      if ((e.getValue() & 1) != 0 && oneCount) {
        single = e.getKey();
        oneCount = false;
        sb.append(helper(e.getKey(), e.getValue() / 2));
      } else if ((e.getValue() & 1) != 0 && !oneCount) {
        System.out.println("NO SOLUTION");
        sc.close();
        return;
      } else {
        sb.append(helper(e.getKey(), e.getValue() / 2));
      }
    }
    StringBuilder res = new StringBuilder(s.length());
    res.append(sb);
    if (single != '\u0000') {
      res.append(single);
    }
    res.append(sb = sb.reverse());
    System.out.println(res);

    // CODE END

    sc.close();
  }

  private static StringBuilder helper(char ch, int n) {
    StringBuilder res = new StringBuilder(n);
    while (n != 0) {
      res.append(ch);
      n--;
    }
    return res;
  }
}