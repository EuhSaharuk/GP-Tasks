import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс решающий задачу расскраски таблицы умножения
 * Находит количесвто необходимых цветов исходя из размеров таблицы
 * Входные данные содержатся в файле INPUT.txt
 * Результат заносится в файл OUTPUT.txt
 * @author Евгений Сахарук
 * @version 1.0
 */
public class Task53 {//не работает

    /** Главный метод программы. Считывает входные данные и передаёт их в метод-контроллер
     * ответ выводится ответ в файл
     @param args Параметры командной строки
     */
    public static void main(String[] args) {
        String[] txt = readInputData("INPUT.txt").split(" ");
        String output = multTableColoring(Integer.parseInt(txt[0]),Integer.parseInt(txt[1]));
        writeOutputData("OUTPUT.txt", output);
    }

    /**
     * Метод находит необходимое количесвто цветов и возвращает ответ в формате строки
     * @param n Количество строк в таблице
     * @param m Количество стобцов в таблице
     * @return Количество красок каждого цвета в виде строки
     */
    public static String multTableColoring(int n, int m){
        int black, blue, green, red;
        black = blue = green = red = 0;
        for (int i = 1; i <=n; i++)
        {
            for(int j = 1; j <=m; j++)
            {
                if(i*j%5 == 0)  blue++;
                else if (i*j%3 == 0) green++;
                else if (i*j%2 == 0) red++;
            }
        }
        black = m*n - blue - green - red;

        return "RED "+ red+"\nGREEN "+ green+"\nBLUE "+ blue+"\nBLACK "+ black;
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
