package matrixCalculatorClasses;

import java.util.Scanner;

public class Determinant {
    
protected static void determinant(Scanner scanner){
    int col;
    int row;

    System.out.println("Columns of matrix:"); 
    col = scanner.nextInt();
    
    System.out.println("Rows of matrix");
    row = scanner.nextInt();

    //MAKE ERROR HERE FOR IF MATRIX INPUT IS NON-SQUARE. ONLY ALLOW SQUARE MATRICES FOR DETERMINANT.
    double[][] det = new double[col][row];
    MatrixOperations.popMatrix(det, col, row, scanner);


    if(MatrixOperations.isFullRank(det)){
    System.out.println(calcDeterimant(det));
    }else{
        //DO PROPER ERROR AND EXCEPTION HERE !!!!!!!!!!!!!!!!!!
        System.out.println("ERROR: Matrix is not full rank and thus not a square matrix. Cannot calculate determinant of given matrix.");
    }



}

protected static double calcDeterimant(double[][] det){

    double upperTri[][] = new double[det.length][det.length];
    int rowSwaps = 0;

//Copies inital input matrix by user into upperTri to be manipulated
    for (int i = 0; i < det.length; i++) {
        System.arraycopy(det[i], 0, upperTri[i], 0, det.length);
    }
     // Gaussian elimination to upper triangular form
     for (int i = 0; i < det.length; i++) {
        // Find maximum pivot
        int maxRow = i;
        for (int j = i + 1; j < det.length; j++) {
            if (Math.abs(upperTri[j][i]) > Math.abs(upperTri[maxRow][i])) {
                maxRow = j;
            }
        }

        // Swap rows if necessary
        if (maxRow != i) {
            double[] temp = upperTri[i];
            upperTri[i] = upperTri[maxRow];
            upperTri[maxRow] = temp;
            rowSwaps++;
        }

        // Eliminate elements below pivot
        for (int j = i + 1; j < det.length; j++) {
            double factor = upperTri[j][i] / upperTri[i][i];
            for (int k = i; k < det.length; k++) {
                upperTri[j][k] -= factor * upperTri[i][k];
            }
        }
    }

    // Calculate determinant as product of diagonal elements
    double determinant = 1.0;
    for (int i = 0; i < det.length; i++) {
        determinant *= upperTri[i][i];
    }

    // Adjust sign based on number of row swaps
    if (rowSwaps % 2 != 0) {
        determinant = -determinant;
    }

    return determinant;
}



}
