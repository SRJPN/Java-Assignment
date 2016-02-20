import java.util.Arrays;

public class Matrix {
	int rowSize;
	int columnSize;
	int[][] matrix;

	public static Matrix createMatrix(int rowSize, int columnSize, int[] values){
		Matrix matrix = new Matrix(rowSize, columnSize);
		matrix.initialise(values);
		return matrix;
	}

	private Matrix(int rowSize, int columnSize){
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.matrix = new int[rowSize][columnSize];
	}

	private void initialise(int[] values){
		for(int rowIndex = 0, count=0; rowIndex<rowSize; rowIndex++){
			for (int columnIndex=0; columnIndex<columnSize ; columnIndex++,count++){
				this.matrix[rowIndex][columnIndex] = values[count];
			}
		}
	}

	public int getValueAt(int row, int column){
		return this.matrix[row][column];
	}

	public Matrix add(Matrix other){
		int[] result = new int[this.rowSize*this.columnSize];
		for(int rowIndex = 0, count=0; rowIndex<this.rowSize; rowIndex++){
			for (int j=0; j<this.columnSize ; j++,count++){
				result[count] = this.getValueAt(rowIndex,j)+other.getValueAt(rowIndex,j);
			}
		}
		return createMatrix(this.rowSize, this.columnSize, result);
	}

	public boolean equals(Matrix other){
		for(int rowIndex = 0, count=0; rowIndex<this.rowSize; rowIndex++){
			for (int j=0; j<this.columnSize ; j++,count++){
				if(this.getValueAt(rowIndex,j)!=other.getValueAt(rowIndex,j))
					return false;
			}
		}
		return true;
	}

	public Matrix multiply(Matrix other){
		if(this.columnSize != other.rowSize)
			return null;
		int[] resultList = new int[this.rowSize*other.columnSize];
		for (int rowIndex=0, count= 0 ; rowIndex<this.rowSize ; rowIndex++ ) {
			for (int j=0 ; j<other.columnSize ;j++,count++ ) {
				for (int columnIndex=0; columnIndex<this.columnSize ; columnIndex++ ) {
					resultList[count] += this.matrix[rowIndex][columnIndex] * other.matrix[columnIndex][j];
				}
			}	
		}
		return createMatrix(this.rowSize, other.columnSize, resultList);
	}

	private int[] multiplyWithRow(int[] row, int[][] matrix){
		int[] result = new int[matrix.length];
		for(int rowIndex = 0; rowIndex<matrix[0].length; rowIndex++){
			int value = 0;
			for(int columnIndex=0; columnIndex<row.length; columnIndex++){
				value += row[columnIndex]*matrix[columnIndex][rowIndex];
			}
			result[rowIndex] = value;
		}
		return result;
	}

	private boolean isSqaureMatrix(){
		return this.columnSize == this.rowSize;
	}

	public int determinant(){
		if(!this.isSqaureMatrix())
			return 0;
		return this.findDeterminant(this.matrix);

	}

	private int findDeterminant(int[][] matrix){
		if(matrix.length == 2){
			return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
		}
		int value = 0;
		int sign = 1;
		for (int i=0;i<matrix[0].length;i++,sign*=-1){
			int[][] subMatrix = remove(matrix, 0, i);
			value += matrix[0][i] * findDeterminant(subMatrix) * sign;
		}
		return value;
	}

	private int[][] remove(int[][] matrix, int row, int column){
		int[][] resultantMatrix =  new int[matrix.length-1][matrix[0].length-1];

		for (int i=0,rowIndex=0; i<matrix.length; i++) {
			if(i!=row){
				for (int j=0,columnIndex=0; j<matrix[0].length; j++) {
					if(j!=column){
						resultantMatrix[rowIndex][columnIndex] = matrix[i][j];
						columnIndex++;
					}
				}
				rowIndex++;
			}
		}
		return resultantMatrix;
	}

	public void print(int[][] matrix){
		System.out.println("=============================="+matrix.length);

		for (int i=0;i<matrix.length;i++ ) {
			for (int j=0;j<matrix[0].length;j++) {
				System.out.println(matrix[i][j]);
			}
		}
	}

}