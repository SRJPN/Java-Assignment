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
}