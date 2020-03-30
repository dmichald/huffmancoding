import java.util.Comparator;
import java.util.List;

public class HuffmanCoding {
    private double[][] array;
    private List<Sign> signs;


    public HuffmanCoding(List<Sign> signs) {
        this.signs = signs;
        this.array = new double[2][2 * signs.size() - 1];
        signs.sort(Comparator.comparingDouble(Sign::getProbabilityPoint));

    }

    public String encode(String messageToCode) {
        designateCodeForEachSign();
        StringBuilder code = new StringBuilder();
        char[] message = messageToCode.toCharArray();
        for (char c : message) {

            signs.stream()
                    .filter(sign1 -> c == sign1.getSign())
                    .findAny()
                    .ifPresent(sign1 -> code.append(sign1.getCode()));
        }

        return code.toString();
    }

    public String decode(String code) {
        designateCodeForEachSign();
        char[] codeNumbers = code.toCharArray();
        StringBuilder decodedMessage = new StringBuilder();
        StringBuilder buff = new StringBuilder();
        for (char c : codeNumbers) {
            buff.append(c);
            signs.stream().filter(sign1 -> buff.toString().equals(sign1.getCode())).findAny().ifPresent(sign1 -> {
                decodedMessage.append(sign1.getSign());
                buff.delete(0, buff.length());
            });
        }
        return decodedMessage.toString();
    }

    public void designateCodeForEachSign() {
        fillArray();
        addProbabilitiesToArray();

        while (hasTwoEmptyField(array[1])) {
            int[] indexesWithMinValue = getIndexesWithMinValue(array[0]);
            int freeIndex = getFreeIndex();
            array[0][getFreeIndex()] = array[0][indexesWithMinValue[0]] + array[0][indexesWithMinValue[1]];
            array[1][indexesWithMinValue[0]] = freeIndex;
            array[1][indexesWithMinValue[1]] = freeIndex;
            array[0][indexesWithMinValue[0]] = 99;
            array[0][indexesWithMinValue[1]] = 1;

        }

        array[0][array[0].length - 1] = Double.POSITIVE_INFINITY;
        for (int i = 0; i < array[0].length; i++) {
            if (array[0][i] == 99.0) {
                array[0][i] = 0;
            }
        }
        createCode();
    }

    private void createCode() {

        for (int i = 0; i < signs.size(); i++) {
            StringBuilder code = new StringBuilder();
            for (int j = i; j < array[0].length - 1; j += (int) array[1][j] - j) {
                code.append((int) array[0][j]);
            }
            signs.get(i).setCode(code.reverse().toString());
        }
    }

    private void addProbabilitiesToArray() {
        for (int i = 0; i < signs.size(); i++) {
            array[0][i] = signs.get(i).getProbabilityPoint();
        }
    }

    private void fillArray() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    private boolean hasTwoEmptyField(double[] array) {
        int count = 0;
        for (double v : array) {
            if (v == Double.POSITIVE_INFINITY) count++;
        }
        return count > 1;
    }

    private int getFreeIndex() {
        int index = -1;
        for (int i = 0; i < array[0].length; i++) {
            if (array[0][i] == Double.POSITIVE_INFINITY) {
                return i;
            }
        }
        return index;
    }

    private int[] getIndexesWithMinValue(double[] array) {
        int[] indexes = new int[2];
        double[] copied = new double[array.length];

        System.arraycopy(array, 0, copied, 0, copied.length);
        double min = copied[0];
        for (int i = 0; i < copied.length; i++) {
            if (min > copied[i]) {
                min = copied[i];
                indexes[0] = i;
            }
        }

        copied[indexes[0]] = 768;
        min = copied[0];
        for (int i = 0; i < copied.length; i++) {
            if (min >= copied[i]) {
                min = copied[i];
                indexes[1] = i;
            }
        }
        //  System.out.println(Arrays.toString(indexes));
        return indexes;
    }

}
