import java.util.Scanner;

    

public class calculator {

    public static void inverse(Scanner scanner){
    int col;
    int row;

    System.out.println("Columns of matrix:"); 
    col = scanner.nextInt();
    
    System.out.println("Rows of matrix");
    row = scanner.nextInt();

    double[][] inv = new double[col][row];
    popMatrix(inv, col, row, scanner);

    double determinant = calcDeterimant(inv);
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
        double [][]minors = matrixOfMinors(matrix);
        double [][]cofactors = matrixOfCofactors(minors);
        double [][]adjugate = transpose(cofactors);

        double [][]inverse = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                inverse[i][j] = adjugate[i][j] / determinant;
            }
        }

        return inverse;
    }

    

    private static double[][] matrixOfMinors(double[][] matrix) {
        int n = matrix.length;
        double[][] minors = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minors[i][j] = calcDeterimant(minor(matrix, i, j));
            }
        }
        return minors;
    }
    private static double[][] minor(double[][] matrix, int row, int col) {
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
    private static double[][] matrixOfCofactors(double[][] matrix) {
        int n = matrix.length;
        double[][] cofactors = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cofactors[i][j] = Math.pow(-1, i + j) * matrix[i][j];
            }
        }
        return cofactors;
    }

    private static double[][] transpose(double[][] matrix) {
        int n = matrix.length;
        double[][] transpose = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }
    
    public static boolean isFullRank(double[][] matrix) {
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

private static void determinant(Scanner scanner){
    int col;
    int row;

    System.out.println("Columns of matrix:"); 
    col = scanner.nextInt();
    
    System.out.println("Rows of matrix");
    row = scanner.nextInt();

    //MAKE ERROR HERE FOR IF MATRIX INPUT IS NON-SQUARE. ONLY ALLOW SQUARE MATRICES FOR DETERMINANT.
    double[][] det = new double[col][row];
    popMatrix(det, col, row, scanner);


    if(isFullRank(det)){
    System.out.println(calcDeterimant(det));
    }else{
        //DO PROPER ERROR AND EXCEPTION HERE !!!!!!!!!!!!!!!!!!
        System.out.println("ERROR: Matrix is not full rank and thus not a square matrix. Cannot calculate determinant of given matrix.");
    }



}

private static double calcDeterimant(double[][] det){

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

private static void multiply(Scanner scanner){
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

private static void calculateMult(int mat1Col, int mat1Row, int mat2Col, int mat2Row){
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

private static void popMatrix(double[][]matrix, int matCol, int matRow, Scanner scanner){

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





public static void main(String[] args)
{
Scanner scanner = new Scanner(System.in);

int input;

System.out.println("What type of calculation would you like to execute?\n1. Multiply Matrices\n2. Calculate Determinant of Matrix\n3. Calculate Inverse of Matrix");
input = scanner.nextInt();

switch(input){
    case 1:
        multiply(scanner);
        break;

    case 2:
        determinant(scanner);
        break;
    
    case 3:
        inverse(scanner);
        break;

    default:
        System.out.println("Error: Invalid Input");
}


















scanner.close();
}


}