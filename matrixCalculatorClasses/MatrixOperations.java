package matrixCalculatorClasses;

import java.util.Scanner;

public class MatrixOperations {
    
protected static void multiply(Scanner scanner){
    int mat1Column;
    int mat1Row;
    int mat2Column;
    int mat2Row;
    

    System.out.println("Columns of matrix 1:"); 
    mat1Column = scanner.nextInt();
    
    System.out.println("Rows of matrix 1:Row");
    mat1Row = scanner.nextInt();
    
    
    System.out.println("Columns of matrix 2");
    mat2Column = scanner.nextInt();
    
    System.out.println("Rows of matrix 2:");
    mat2Row = scanner.nextInt();
    
    if (mat1Column != mat2Row){
        scanner.close();
        throw new IllegalArgumentException("Matrices cannot be multiplied. Columns of matrix 1 must be equal to rows of matrix 2.");
        
    }else if(mat1Row != mat2Column){
        scanner.close();
        throw new IllegalArgumentException("Matrices cannot be multiplied. Rows of matrix 1 must be equal to columns of matrix 2.");
    }
    
    calculateMult(mat1Column, mat1Row, mat2Column, mat2Row);

}

protected static void calculateMult(int mat1Col, int mat1Row, int mat2Col, int mat2Row){
double[][] mat1 = new double[mat1Col][mat1Row];
double[][] mat2 = new double[mat2Col][mat2Row];

double[][] prod = new double[mat1Col][mat2Row];
Scanner scanner = new Scanner(System.in);


popMatrix(mat1, mat1Col, mat1Row, scanner);
popMatrix(mat2, mat2Col, mat2Row, scanner);

scanner.close();

for (int i = 0; i < mat1Row; i++) {
    for (int j = 0; j < mat2Col; j++) {
        for (int k = 0; k < mat1Col; k++) {
            prod[i][j] += mat1[i][k] * mat2[k][j];
        }
    }
}
System.out.println("Matrix:");
        for (int i = 0; i < mat1Col; i++) {
            for (int j = 0; j < mat2Row; j++) {
                System.out.print(prod[i][j] + " ");
            }
            System.out.println();


            
          }

}

protected static void popMatrix(double[][]matrix, int matCol, int matRow, Scanner scanner){

    for(int i = 0; i < matCol; ++i){
        for(int j = 0; j < matRow; ++j){
            System.out.println("Please input number at location: [" + i + "," + j + "]");
            matrix[i][j] = scanner.nextInt();
        }
    }
    /*System.out.println("Matrix:");
            for (int i = 0; i < matCol; i++) {
                for (int j = 0; j < matRow; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
    
    
              }  */
    }
    
    protected static double[][] matrixOfMinors(double[][] matrix) {
        int n = matrix.length;
        double[][] minors = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minors[i][j] = Determinant.calcDeterimant(minor(matrix, i, j));
            }
        }
        return minors;
    }
    protected static double[][] minor(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];
        int minorRow = 0, minorCol = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            minorCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    } 
    protected static double[][] matrixOfCofactors(double[][] matrix) {
        int n = matrix.length;
        double[][] cofactors = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cofactors[i][j] = Math.pow(-1, i + j) * matrix[i][j];
            }
        }
        return cofactors;
    }

    protected static double[][] transpose(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
         // Swapping dimensions for transpose
        double[][] transpose = new double[cols][rows];
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                 // Swap indices for transposition
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }
    
    protected static boolean isFullRank(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        // Perform Gaussian elimination to check linear independence
        for (int i = 0; i < numRows; i++) {
            // Perform row reduction to make matrix upper triangular
            for (int j = i + 1; j < numRows; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < numCols; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }

        // Check if any diagonal element in reduced form is zero
        for (int i = 0; i < numRows; i++) {
            boolean allZeros = true;
            for (int j = 0; j < numCols; j++) {
                // tolerance for numerical stability
                if (Math.abs(matrix[i][j]) > 1e-10) { 
                    allZeros = false;
                    break;
                }
            }
            if (allZeros) {
                // Matrix is not full rank
                return false; 
            }
        }
        // Matrix is full rank
        return true; 
    }

protected static double[][] matrixInput(Scanner scanner){
    int col;
    int row;

    System.out.println("Columns of matrix:"); 
    col = scanner.nextInt();
    
    System.out.println("Rows of matrix");
    row = scanner.nextInt();

    double[][] matrix = new double[col][row];
    MatrixOperations.popMatrix(matrix, col, row, scanner);

    System.out.println("Matrix:");
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
}

    return matrix;
}

protected static void transposePrint(double[][] matrix){


 System.out.println("Matrix:");
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
}
}

}