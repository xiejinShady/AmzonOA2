package OA2;

import java.util.List;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;

        int i = m - 1;
        int j = 0;

        while (i >= 0 && j < matrix.length) {
            if (matrix[i][j] > target) {
                i--;
                continue;
            }
            if (matrix[i][j] < target) {
                j++;
                continue;
            }
            if (matrix[i][j] == target) {
                return true;
            }
        }

        return false;

    }

    //Amazon 原版

    class pairInt {
        int first, second;

        pairInt() {
        }

        pairInt(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public pairInt loacationOfTargetValue(int rowCount, int colCount, List<List<Integer>> matrix, int target) {

        int row = rowCount - 1;
        int col = 0;

        while (row >= 0 && col < colCount) {
            if (matrix.get(row).get(col) > target) {
                row--;
            }
            if (matrix.get(row).get(col) < target) {
                col++;
            }
            if (matrix.get(row).get(col) == target) {
                return new pairInt(row, col);

            }
        }
        return new pairInt(-1,-1);
    }
}
