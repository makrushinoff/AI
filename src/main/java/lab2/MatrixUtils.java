package lab2;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils {

    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    public static double[][] roundMatrix(double[][] matrix) {
        final double[][] result = getNewZeroMatrix(matrix.length, matrix[0].length);
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                result[i][j] = matrix[i][j] > 0 ? 1 : -1;
            }
        }
        return result;
    }

    public static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public static double[][] sumMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        final double[][] matrix = getNewZeroMatrix(firstMatrix.length, secondMatrix[0].length);
        for(int i = 0; i < firstMatrix.length; i++) {
            for(int j = 0; j < secondMatrix[0].length; j++) {
                matrix[i][j] += firstMatrix[i][j] ;
                matrix[i][j] += secondMatrix[i][j];
            }
        }
        return matrix;
    }

    public static double[][] multiplyMatrixByNumber(double[][] matrix, double value) {
        final double[][] result = getNewZeroMatrix(matrix.length, matrix[0].length);
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                result[i][j] = matrix[i][j] * value;
            }
        }
        return result;
    }

    public static double[][] copyMatrix(double[][] matrix) {
        double[][] copyMatrix = getNewZeroMatrix(matrix.length, matrix[0].length);
        for(int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, copyMatrix[i], 0, matrix[i].length);
        }
        return copyMatrix;
    }

    public static double[][] getNewZeroMatrix(int rows, int columns) {
        double[][] result = new double[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                result[i][j] = 0;
            }
        }
        return result;
    }

    public static double[][] integerListToVerticalMatrix(List<Integer> list) {
        final double[][] matrix = getNewZeroMatrix(list.size(), 1);
        for(int i = 0; i < list.size(); i++) {
            matrix[i][0] = list.get(i);
        }
        return matrix;
    }

    public static double[][] integerListToHorizontalMatrix(List<Integer> list) {
        final double[][] matrix = getNewZeroMatrix(1, list.size());
        for(int i = 0; i < list.size(); i++) {
            matrix[0][i] = list.get(i);
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static List<Integer> verticalMatrixToIntegerList(double[][] matrix) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++) {
            list.add(matrix[i][0] > 0 ? 1 : -1);
        }
        return list;
    }

}
