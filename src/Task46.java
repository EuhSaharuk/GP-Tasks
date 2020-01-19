import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Класс решающий задачу округления числа e с заданной точностью
 * Результат заносится в файл OUTPUT.txt
 * @author  Евгений Сахарук
 * @version 1.0
 */
public class Task46 {

    /** Главный метод программы. Считывает входные данные, вызывает метод округления и выводит ответ в файл
     @param args Параметры командной строки
     */
    public static void main(String[] args) {
        int num = Integer.parseInt(readInputData("INPUT.txt"));
        String e = roundE(num);
        writeOutputData("OUTPUT.txt",e);
    }

    /** Округляет число e до заданного числа знаков после запятой
     @param n Число знаков после запятой
     */
    public static String roundE(int n){
        BigDecimal e = new BigDecimal("2.7182818284590452353602875");
        return e.setScale(n,BigDecimal.ROUND_HALF_UP).toString();
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
