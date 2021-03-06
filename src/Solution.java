import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;


public class Solution {
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;

    public Solution(String filename) { parse(filename); }

    private void parse(String filename) {
        try {
            File f            = new File(filename);
            FileReader fr     = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            if ((line = br.readLine()) != null) {
                String[] coordinates = line.split(":")[1].trim().split(",");
                String[] xValues = coordinates[0].trim().replaceAll("x=", "").split("\\.\\.");
                String[] yValues = coordinates[1].trim().replaceAll("y=", "").split("\\.\\.");

                this.xMin = Integer.parseInt(xValues[0]);
                this.yMin = Integer.parseInt(yValues[0]);
                this.xMax = Integer.parseInt(xValues[1]);
                this.yMax = Integer.parseInt(yValues[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solveP1() {
        // what do you do when cannot solve a problem... cheat like hell
        // The maximum y cord is the distance from the start to the bottom row minus one, or abs(this.yMin) - 1
        // In all reality, why this works is kinda complicated, but it does...
        int maxHeight = Math.abs(this.yMin) - 1; // yMin input should always be negative... I think;
        int total     = 0;

        for (int i=0; i < maxHeight; ++i) { total += maxHeight - i; }
        System.out.println("Max Trick shot height: " + total);
    }

    private boolean hit(int x, int y, int xMax, int yMax) {
        int xSteps = x;
        int ySteps = y;

        while ((y >= yMax ) && (x <= xMax)) {
            if ((x >= this.xMin) && (x <= this.xMax) && (y >= this.yMin) && (y <= this.yMax)) {
                return true;
            }

            xSteps--;
            ySteps--;

            if (ySteps != 0) { y += ySteps; }
            if (xSteps > 0)  { x += xSteps; }
        }

        return false;
    }

    public void solveP2() {
        int xMax  = this.xMax;
        int yMax  = Math.abs(this.yMin) - 1;
        int total = 0;

        for (int x=0; x <= xMax; ++x) {
            for (int y=this.yMin; y <= yMax; ++y) {
                if (hit(x, y, xMax, this.yMin)) { total += 1; }
            }
        }

        System.out.println("Total velocity positions: " + total);
    }
}