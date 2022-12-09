/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] open;
    private boolean[] full;
    private boolean[] touchesBottom;
    private int length;
    private int numOpenSites;
    private WeightedQuickUnionUF gg;
    private boolean percolates;

    // creates n-by-n grid, with all sites initially blocked.
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        gg = new WeightedQuickUnionUF(n * n + 1);
        open = new boolean[n * n + 1];
        full = new boolean[n * n + 1];
        touchesBottom = new boolean[n * n + 1];
        length = n;
        numOpenSites = 0;
        percolates = false;
        for (int i = 0; i <= n * n; i++) {
            if (i <= length) {
                full[i] = true;
                open[i] = false;
                touchesBottom[i] = false;
            }
            else if (i >= (length - 1) * length + 1) {
                touchesBottom[i] = true;
                open[i] = false;
            }
            else {
                open[i] = false;
                touchesBottom[i] = false;
            }
        }
        for (int i = 1; i <= n; i++) {
            gg.union(0, i);
        }
    }

    // Opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > length || col > length) {
            throw new IllegalArgumentException();
        }
        int index = (row - 1) * length + col;
        int above = index - length;
        int left = index - 1;
        int right = index + 1;
        int below = index + length;

        if (!open[index]) {
            open[index] = true;
            numOpenSites++;
            if (length == 1) {
                percolates = true;
            }
            if (above > 0 && open[above]) {
                int i = gg.find(above);
                int j = gg.find(index);
                if (full[i] || full[j]) {
                    full[i] = true;
                    full[j] = true;
                }
                if (touchesBottom[i] || touchesBottom[j]) {
                    touchesBottom[i] = true;
                    touchesBottom[j] = true;
                }
                if (i != j) {
                    gg.union(i, j);
                }
                if (touchesBottom[i] && full[i]) {
                    percolates = true;
                }
            }
            if (left > (row - 1) * length && open[left]) {
                int i = gg.find(left);
                int j = gg.find(index);
                if (full[i] || full[j]) {
                    full[i] = true;
                    full[j] = true;
                }
                if (touchesBottom[i] || touchesBottom[j]) {
                    touchesBottom[i] = true;
                    touchesBottom[j] = true;
                }
                if (i != j) {
                    gg.union(i, j);
                }
                if (touchesBottom[i] && full[i]) {
                    percolates = true;
                }
            }
            if (index < right && right <= (row - 1) * length + length && open[right]) {
                int i = gg.find(right);
                int j = gg.find(index);
                if (full[i] || full[j]) {
                    full[i] = true;
                    full[j] = true;
                }
                if (touchesBottom[i] || touchesBottom[j]) {
                    touchesBottom[i] = true;
                    touchesBottom[j] = true;
                }
                if (i != j) {
                    gg.union(i, j);
                }
                if (touchesBottom[i] && full[i]) {
                    percolates = true;
                }
            }
            if (below <= length * length && open[below]) {
                int i = gg.find(below);
                int j = gg.find(index);
                if (full[i] || full[j]) {
                    full[i] = true;
                    full[j] = true;
                }
                if (touchesBottom[i] || touchesBottom[j]) {
                    touchesBottom[i] = true;
                    touchesBottom[j] = true;
                }
                gg.union(i, j);
                if (touchesBottom[i] && full[i]) {
                    percolates = true;
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > length || col > length) {
            throw new IllegalArgumentException();
        }
        int index = (row - 1) * length + col;
        return open[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > length || col > length) {
            throw new IllegalArgumentException();
        }
        int index = (row - 1) * length + col;
        return full[gg.find(index)] && open[index];
    }

    // Does the system percolate?
    public boolean percolates() {
        return percolates;
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

}
