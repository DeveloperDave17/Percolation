/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] trialOpen;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        trialOpen = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
            }
            trialOpen[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialOpen);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialOpen);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trialOpen.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trialOpen.length));
    }

    // Test Client
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        PercolationStats perc = new PercolationStats(a, b);
        System.out.println("mean = " + perc.mean());
        System.out.println("stddev = " + perc.stddev());
        System.out.println(
                "95% confidence interval = [" + perc.confidenceLo() + ", " + perc.confidenceHi()
                        + "]");
    }
}
