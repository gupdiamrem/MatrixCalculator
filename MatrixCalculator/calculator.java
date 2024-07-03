package MatrixCalculator;
import java.util.Scanner;

    

public class calculator {


public static void main(String[] args)
{
Scanner scanner = new Scanner(System.in);

int input;

System.out.println("What type of calculation would you like to execute?\n1. Multiply Matrices\n2. Calculate Determinant of Matrix\n3. Calculate Inverse of Matrix\n4. Transpose Matrix");
input = scanner.nextInt();

switch(input){
    case 1:
        MatrixOperations.multiply(scanner);
        break;

    case 2:
        Determinant.determinant(scanner);
        break;
    
    case 3:
        Inverse.inverse(scanner);
        break;

    case 4:
        MatrixOperations.transpose(MatrixOperations.matrixInput(scanner));
    default:
        System.out.println("Error: Invalid Input");
}


scanner.close();
}


}