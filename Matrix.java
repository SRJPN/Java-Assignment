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

	public Matrix add(Matrix other){
		Matrix resultantMatrix =  new Matrix(this.rowSize, this.columnSize);
		int[] result = new int[this.rowSize*this.columnSize];
		for(int i = 0, count=0; i<this.rowSize; i++){
			for (int j=0; j<this.columnSize ; j++,count++){
				result[count] = this.getValueAt(i,j)+other.getValueAt(i,j);
			}
		}
		resultantMatrix.addValues(result);
		return resultantMatrix;
	}

	public boolean equals(Matrix other){
		for(int i = 0, count=0; i<this.rowSize; i++){
			for (int j=0; j<this.columnSize ; j++,count++){
				if(this.getValueAt(i,j)!=other.getValueAt(i,j))
					return false;
			}
		}
		return true;
	}
}