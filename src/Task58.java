import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс проверяющий являются ли входные матрицы "симпатичными"
 * Входные данные содержатся в файле INPUT.txt
 * Результат заносится в файл OUTPUT.txt
 * @author  Евгений Сахарук
 * @version 1.0
 */
public class Task58 {//не работает

    /** Главный метод программы. Считывает входные данные и передаёт их в метод-контроллер
     * ответ выводится ответ в файл
     @param args Параметры командной строки
     */
    public static void main(String[] args) {
        String inputData = readInputData("INPUT.txt");
        String result = Method(inputData);
        writeOutputData("OUTPUT.txt",result);
    }

    /**
     * Метод парсит строку входных данных на параметры матриц и сами матрицы, после передаёт данные в метод проверяющий
     * матрицы на "симпатичность"
     * @param inputData Строка входных данных
     * @return Данные выводимые в файл
     */
    public static String Method(String inputData){
        String[] input = inputData.split("\\s*(\\r\\n|\\s)");
        StringBuilder output = new StringBuilder();
        int matrixCount = Integer.parseInt(input[0]);
        int c = 1;
        for(int t = 0; t < matrixCount; t++)
        {
            int rowCount = Integer.parseInt(input[c++]);
            int columnCount = Integer.parseInt(input[c++]);
            int[][] arr = new int[rowCount][columnCount];
            for(int n = 0; n < rowCount; n++)
            {
                for (int m = 0; m < columnCount; m++)
                {
                    arr[n][m]=Integer.parseInt(input[c++]);
                }
            }
            if(isCuteMatrix(arr)) output.append("YES\n");
            else output.append("No\n");
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }

    /**
     * Метод проверяет матрицу на симпатичность
     * @param matrix Проверяемая матрица
     * @return Является ли матрица симпатичной
     */
    private static boolean isCuteMatrix(int[][] matrix){

        for (int i = 0; i < matrix.length-2;i++)
        {
            for(int j = 0; j < matrix[0].length -2; j++)
            {
                int sum = matrix[i][j]+ matrix[i+1][j]+ matrix[i][j+1]+ matrix[i+1][j+1];
                if (sum ==0 || sum == 4) return false;
            }
        }
        return true;
    }

    /** Метод считывает данные из вайла и возвращает и в формате строки
     @param fileName Имя файла
     */
    static String readInputData(String fileName) {
        StringBuilder InputData = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            int ch;
            while ((ch = reader.read()) != -1) {
                InputData.append((char) ch);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return InputData.toString();
    }

    /** Метод запиывай входную строку в файл с указанным именем
     @param fileName Имя файла
     @param outputData Строка для записи в файл
     */
    static void writeOutputData(String fileName, String outputData){
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            writer.write(outputData);
            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
