package lab2;

import java.util.List;
import org.jetbrains.annotations.Nullable;

public class HopfieldNetworkService {

    private static final int WEIGHT_MATRIX_DIMENSION = (int) Math.pow(StandardImages.DIMENSION, 2);
    private static double[][] weights = new double[WEIGHT_MATRIX_DIMENSION][WEIGHT_MATRIX_DIMENSION];
    private int iterations = 0;

    public void train(List<Integer> list) {
        double[][] verticalVector = MatrixUtils.integerListToVerticalMatrix(list);
        double[][] horizontalVector = MatrixUtils.integerListToHorizontalMatrix(list);
        double[][] matrix = MatrixUtils.multiplyMatrices(verticalVector, horizontalVector);
        final double[][] resultMatrix = MatrixUtils.multiplyMatrixByNumber(matrix, (1.0 / WEIGHT_MATRIX_DIMENSION));
        weights = MatrixUtils.sumMatrices(weights, resultMatrix);
    }

    public void trainNetwork() {
        StandardImages.standardImagesBits.forEach(this::train);
    }

    @Nullable
    public List<Integer> recognizeLetter(List<Integer> newList) {
        final double[][] matrix = MatrixUtils.multiplyMatrices(weights, MatrixUtils.integerListToVerticalMatrix(newList));
        List<Integer> list = MatrixUtils.verticalMatrixToIntegerList(matrix);
        List<Integer> correctImage = StandardImages.standardImagesBits.stream()
                .filter(list::equals)
                .findFirst()
                .orElse(null);
        if(correctImage == null && iterations < 1000) {
            iterations++;
            return recognizeLetter(list);
        }
        return correctImage;
    }
}
