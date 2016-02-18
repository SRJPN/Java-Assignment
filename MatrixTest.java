import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

	@Test
	public void createMatrix_creates_matrix_with_given_size_and_insert_given_values(){
		int[] a = {1,2,3,4,5,6};
        Matrix matrix1 = Matrix.createMatrix(2,3,a);

		for(int i = 0, count=0; i<2; i++){
			for (int j=0; j<3 ; j++,count++){
				assertEquals(matrix1.getValueAt(i,j),a[count]);
			}
		}
	}

    @Test
    public void add_adds_the_given_matrix_with_the_matrix(){
    	int[] a = {1,2,3,4,5,6};
    	int[] b = {6,5,4,3,2,1};
        Matrix matrix1 = Matrix.createMatrix(2,3,a);
        Matrix matrix2 = Matrix.createMatrix(2,3,b);

        int[] expectedArray = {7,7,7,7,7,7,7};
        Matrix expected = Matrix.createMatrix(2,3,expectedArray);

        Matrix result = matrix1.add(matrix2);

        assertTrue(expected.equals(result));
    }

    @Test
    public void multiply_returns_null_if_the_two_matrices_cant_be_multiplied(){
    	int[] a = {1,2,3,4,5,6};
    	int[] b = {6,5,4,3,2,1};
        Matrix matrix1 = Matrix.createMatrix(2,3,a);
        Matrix matrix2 = Matrix.createMatrix(2,3,b);

    	assertNull(matrix1.multiply(matrix2));
    }

    @Test
    public void multiply_returns_the_product_of_two_matrix_of_same_sizes(){
    	int[] a = {1,2};
    	int[] b = {1,2};
        Matrix matrix1 = Matrix.createMatrix(1,2,a);
        Matrix matrix2 = Matrix.createMatrix(2,1,b);

        int[] expectedArray = {5};
        Matrix expected = Matrix.createMatrix(1,1,expectedArray); 

    	Matrix resultant = matrix1.multiply(matrix2);

    	assertTrue(resultant.equals(expected));
    }

    @Test
    public void multiply_returns_the_product_of_two_matrix_of_different_sizes(){
    	int[] a = {1,2,3,4,5,6};
    	int[] b = {1,2,3,4,5,6};
        Matrix matrix1 = Matrix.createMatrix(2,3,a);
        Matrix matrix2 = Matrix.createMatrix(3,2,b);

        int[] expectedArray = {22,28,49,64};
        Matrix expected = Matrix.createMatrix(2,2,expectedArray); 

    	Matrix resultant = matrix1.multiply(matrix2);

    	assertTrue(resultant.equals(expected));
    }
}