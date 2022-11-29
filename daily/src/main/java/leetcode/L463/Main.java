package leetcode.L463;

public class Main {
	public static void main(String[] args) {
//		int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
		int[][] grid = {{1,0}};
		Solution solution = new Solution();
		System.out.println(solution.islandPerimeter(grid));
	}
}
/**
 * 当这个边挨着水域或者是地图边界是，周长就+1
 * */
class Solution {
	public int islandPerimeter(int[][] grid) {
		int sum = 0;
		int row = grid.length;
		int col = grid[0].length;
		for (int i = 0; i< row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {
					if (i == 0 || grid[i-1][j] == 0) {
						sum += 1;
					}
					if (i == row - 1 || grid[i+1][j] == 0) {
						sum += 1;
					}
					if (j == 0 || grid[i][j-1] == 0) {
						sum += 1;
					}
					if (j == col - 1 || grid[i][j+1] == 0) {
						sum += 1;
					}
				}

			}
		}
		return sum;
	}
}
