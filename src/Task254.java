import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Класс решает задачу смены жрецов в каролевстве
 * Входные данные содержатся в файле INPUT.txt
 * Результат заносится в файл OUTPUT.txt
 * @author Евгений Сахарук
 * @version 1.0
 */
public class Task254 {//работает

    /** Главный метод программы. Считывает входные данные и передаёт их в метод-контроллер
     * ответ выводится ответ в файл
     @param args Параметры командной строки
     */
    public static void main(String[] args) {
        String input = readInputData("INPUT.txt");
        String output = priestChoise(input);
        writeOutputData("OUTPUT.txt",output);
    }

    /**
     * Метод парсит входные данные и вызывает метод
     * @param inputData Входная строка данных
     * @return Ответ, выводимый в файл
     */
    public static String priestChoise(String inputData){
        String[] input = inputData.split("\\s*(\\r\\n|\\s)");
        StringBuilder output = new StringBuilder();
        int[] couties = new int[Integer.parseInt(input[0])];
        int i;
        for (i = 1; i <= couties.length; i++)
        {
            couties[i-1] = Integer.parseInt(input[i]);
        }
        HashMap<Integer, Integer> cointiesWishes = new HashMap<Integer, Integer>();
        int wishes = Integer.parseInt(input[i++]);
        for(; wishes > 0; wishes--)
        {
            cointiesWishes.put(Integer.parseInt(input[i]),Integer.parseInt(input[i+1]));
            i+=2;
        }
        couties = elections(couties,cointiesWishes);
        for (int c: couties)
        {
            output.append(c + " ");
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }

    /**
     * Метод изменющий жрецов исходя из заявок
     * @param counties Массив Графств с их текущими жрецами
     * @param wishes Заявки на смену жреца, ключ - текущий жрец, значение - желаемый
     * @return Конечный массив графств с назначеными жрецами
     */
    public static int[] elections(int[] counties, HashMap<Integer, Integer> wishes ){
        for (int i=0;i < counties.length;i++)
        {
            if(wishes.containsKey(counties[i]))
            {
                counties[i] = wishes.get(counties[i]);
            }
        }
        return counties;
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
