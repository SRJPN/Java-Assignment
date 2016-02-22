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

	@Override
	public boolean equals(Object other){
		if (this == other)
			return true;
		if(!(other instanceof Matrix))
			return false;
		return this.equals((Matrix) other);		
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
		if(matrix.length == 1){
			return matrix[0][0];
		}
		int value = 0;
		int sign = 1;
		for (int i=0;i<matrix[0].length;i++,sign*=-1){
			Matrix subMatrix = createMatrix(this.rowSize-1, this.columnSize-1, remove(matrix, 0, i));
			value += matrix[0][i] * subMatrix.determinant() * sign;
		}
		return value;
	}

	private int[] remove(int[][] matrix, int row, int column){
		int[] resultantMatrix =  new int[(matrix.length-1) * (matrix[0].length-1)];

		for (int rowIndex=0,count=0 ;rowIndex<matrix.length; rowIndex++ ) 
			if(rowIndex!=row)
				for (int columnIndex=0; columnIndex<matrix[0].length; columnIndex++)
					if(columnIndex!=column){
						resultantMatrix[count] = matrix[rowIndex][columnIndex];	
						count++;
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