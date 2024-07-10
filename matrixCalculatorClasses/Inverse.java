package matrixCalculatorClasses;

import java.util.Scanner;

public class Inverse {
    

     public static void inverse(Scanner scanner){
    int col;
    int row;

    System.out.println("Columns of matrix:"); 
    col = scanner.nextInt();
    
    System.out.println("Rows of matrix");
    row = scanner.nextInt();

    double[][] inv = new double[col][row];
    MatrixOperations.popMatrix(inv, col, row, scanner);
    

    double determinant = Determinant.calcDeterimant(inv);
    if(determinant == 0){
        //WRITE ERROR HERE FOR IF DETERMINANT IS NON_INVERTABLE
        System.out.println("ERROR: input matrix is not invertable.");
    }else{
    double inverse[][] = calcInverse(inv, determinant);
    System.out.println("Matrix:");
    
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j < inverse.length; j++) {
                System.out.print(inverse[i][j] + " ");
            }
            System.out.println();


          } 
    }
}

    private static double[][] calcInverse(double[][] matrix, double determinant){
        double [][]minors = MatrixOperations.matrixOfMinors(matrix);
        double [][]cofactors = MatrixOperations.matrixOfCofactors(minors);
        double [][]adjugate = MatrixOperations.transpose(cofactors);

        double [][]inverse = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                inverse[i][j] = adjugate[i][j] / determinant;
            }
        }

        return inverse;
    }

    

   


}
