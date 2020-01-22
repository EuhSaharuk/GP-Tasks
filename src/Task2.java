import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс решающий задачу вычисления суммы всех чисел между единицей и числом заданным в файле INPUT.TXT
 * Результат заносится в файл OUTPUT.txt
 * @author  Евгений Сахарук
 * @version 1.0
 */
public class Task2 {//не работат

    /** Главный метод программы. Считывает входные данные, вызывает метод вычисления суммы и выводит ответ в файл
     @param args Параметры командной строки
     */
    public static void main(String[] args) {
        //System.out.println(java.util.Arrays.toString("dsf dfds\n".split("\\s*(\\r\\n|\\s)")));

        int num = Integer.parseInt(readInputData("INPUT.txt").split("\\s*(\\r\\n|\\s)")[0]);
        int sum = arithmeticalProgessionSum(num);
        writeOutputData("OUTPUT.txt",sum + "");
    }

    /** Метод вычисления суммы
     @param n Число до кторого суммиркются числа
     */
    static int arithmeticalProgessionSum(int n) {
        if(n > 0)
            return (n + 1) * n / 2;
        else{
            n = Math.abs(n);
            return ((n + 1) * n / 2)*-1+1;
        }

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
