package OA2;

public class spiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int c1 = 0,c2 = n - 1;
        int r1 = 0,r2 = n - 1;
        int index = 1;

        while(c1 <= c2 && r1 <= r2) {
            for(int c = c1;c <= c2;c++) matrix[r1][c] = index++;
            for(int r = r1 + 1;r <= r2;r++) matrix[r][c2] = index++;
            for (int c = c2 -1 ; c >= c1; c--) matrix[r2][c] = index++;
            for (int r = r2 -1; r > r1; r--) matrix[r][c1] = index++;
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return matrix;
    }
}
