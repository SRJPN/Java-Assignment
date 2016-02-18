public class Matrix {

	int rowSize;
	int columnSize;
	int[][] matrix;

	public static Matrix createMatrix(int rowSize, int columnSize, int[] values){
		Matrix matrix = new Matrix(rowSize, columnSize);
		matrix.addValues(values);
		return matrix;
	}

	private Matrix(int rowSize, int columnSize){
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.matrix = new int[rowSize][columnSize];
	}

	private void addValues(int[] values){
		for(int i = 0, count=0; i<rowSize; i++){
			for (int j=0; j<columnSize ; j++,count++){
				matrix[i][j] = values[count];
			}
		}
	}

	public int getValueAt(int row, int column){
		return this.matrix[row][column];
	}
}