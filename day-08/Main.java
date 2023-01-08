import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Display display = new Display(50, 6);

    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] words = line.split(" ");

        if (words[0].equals("rect")) {
          int width = Integer.parseInt(words[1].split("x")[0]);
          int height = Integer.parseInt(words[1].split("x")[1]);

          display.rect(width, height);
        } else if (words[1].equals("column")) {
          int x = Integer.parseInt(words[2].split("=")[1]);
          int amount = Integer.parseInt(words[4]);

          display.rotateColumn(x, amount);
        } else if (words[1].equals("row")) {
          int y = Integer.parseInt(words[2].split("=")[1]);
          int amount = Integer.parseInt(words[4]);

          display.rotateRow(y, amount);
        }
      }
    }

    System.out.println(display.getLit());

    display.print(); // read from shell -> "CFLELOYFCS"
  }

  private static class Display {
    private int[][] pixels;

    public Display(int width, int height) {
      this.pixels = new int[width][height];
    }

    public void print() {
      for (int y = 0; y < this.pixels[0].length; y++) {
        for (int x = 0; x < this.pixels.length; x++) {
          System.out.print(this.pixels[x][y] == 1 ? "#" : " ");
        }
        System.out.println();
      }
    }

    public int getLit() {
      int x = 0;
      for (int i = 0; i < this.pixels.length; i++) {
        for (int j = 0; j < this.pixels[i].length; j++) {
          x += this.pixels[i][j];
        }
      }
      return x;
    }

    public void rect(int width, int height) {
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          this.pixels[x][y] = 1;
        }
      }
    }

    public void rotateColumn(int x, int amount) {
      for (int i = 0; i < amount; i++) {
        int last = this.pixels[x][this.pixels[x].length - 1];
        for (int y = this.pixels[x].length - 1; y > 0; y--) {
          this.pixels[x][y] = this.pixels[x][y - 1];
        }
        this.pixels[x][0] = last;
      }
    }

    public void rotateRow(int y, int amount) {
      for (int i = 0; i < amount; i++) {
        int last = this.pixels[this.pixels.length - 1][y];
        for (int x = this.pixels.length - 1; x > 0; x--) {
          this.pixels[x][y] = this.pixels[x - 1][y];
        }
        this.pixels[0][y] = last;
      }
    }
  }
}