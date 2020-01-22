import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.*;

/**
 * Класс решающий задачу поиска пути в трёхмерном лабиринте
 * Использующийся алгоритм - поиск в ширину
 * Входные данные содержатся в файле INPUT.txt
 * Результат заносится в файл OUTPUT.txt
 */
public class Task99 {

    /** Главный метод программы. Считывает входные данные и передаёт их в метод-контроллер
     * ответ выводится ответ в файл
     @param args Параметры командной строки
     */
    public static void main(String[] args) throws IOException{
        //Path file = Paths.get("INPUT.txt");
        //List<String> lines = Files.readAllLines(file);
        //String s = String.join(" ",lines);
        String s2 = String.join(" ",Files.lines(Paths.get("INPUT.txt")).toArray(String[]::new));
        //String input = Files.lines(Paths.get("INPUT.txt")).toString();

        //        readInputData("INPUT.txt");
        String output = princeOfPersia(s2);
        writeOutputData("OUTPUT.txt",output);
    }

    /**
     * Метод-контроллер, парсит входные данные, создаёт на их основе лабиринт и вызывает метод поиска в ширину
     * @param inputData Входные данные
     * @return Время которое понадобится принцу чтобы добраться до принцессы
     */
    public static String princeOfPersia(String inputData){
        String[] input = inputData.split("\\s*(\\r\\n|\\s)");
        byte h = Byte.parseByte(input[0]);
        byte m = Byte.parseByte(input[1]);
        byte n = Byte.parseByte(input[2]);
        char[][][] grid = new char[h][m][n];
        byte[] start = new byte[3], end = new byte[3];
        boolean findStrar = false, findEnd =false;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < m; j++){

                grid[i][j] = input[i*m+j+3].toCharArray();

                if(i==0 && !findStrar){
                    for(int p =0; p < n; p++){
                        if(grid[i][j][p]=='1') {
                            start = new byte[]{(byte)i,(byte)j,(byte)p};
                            findStrar = true;
                            break;
                        }
                    }
                }
                if(i==h-1 && !findEnd){
                    for(int p =0; p < n; p++){
                        if(grid[i][j][p]=='2') {
                            end = new byte[]{(byte)i,(byte)j,(byte)p};
                            findEnd = true;
                            break;
                        }
                    }
                }
            }
        }
        return wideSearch(grid,start,end)+"";
    }

    /**
     * Метод поиска в ширину
     * @param grid Лабиринт
     * @param start Начальная точка (координаты принца)
     * @param end Конечная точка (координаты принцессы)
     * @return Время необходдимое принцу
     */
    private static int wideSearch(char[][][] grid, byte[] start, byte[] end){
        ArrayList<Cell> was = new ArrayList<>();
        int time;
        was.add(new Cell(start,(short)0));
        int counter =0;
        while (!Arrays.equals(was.get(counter).pos,end)){
            addNeighbours(grid, was.get(counter).pos, was, (short)(was.get(counter).val+1));
            counter++;
        }
        time = was.get(counter).val * 5;
        return time;
    }

    /**
     * Метод добавления доступных для перехода соседей в очередь
     * @param grid Лабиринт
     * @param pos Рассматриваемая позиция
     * @param was Очередь для рассмотрения
     * @param step Путь до соседних ячеек от старта
     */
    private static void addNeighbours(char[][][] grid, byte[] pos, List<Cell> was, short step){
        Cell newNei;
        if(pos[0]<grid.length-1 && grid[pos[0]+1][pos[1]][pos[2]]!='o'){
            grid[pos[0]+1][pos[1]][pos[2]] = 'o';
            was.add(new Cell(new byte[]{(byte)(pos[0]+1),pos[1],pos[2]},step));
        }

        if(pos[1]<grid[0].length-1 && grid[pos[0]][pos[1]+1][pos[2]]!='o')
        {
            grid[pos[0]][pos[1]+1][pos[2]]='o';
            was.add(new Cell(new byte[]{pos[0],(byte)(pos[1]+1),pos[2]},step));
        }

        if(pos[1]!=0 && grid[pos[0]][pos[1]-1][pos[2]]!='o'){
            grid[pos[0]][pos[1]-1][pos[2]]='o';
            was.add( new Cell(new byte[]{pos[0],(byte)(pos[1]-1),pos[2]},step));
        }
        if(pos[2]<grid[0][0].length-1 && grid[pos[0]][pos[1]][pos[2]+1]!='o'){
            grid[pos[0]][pos[1]][pos[2]+1]='o';
            was.add(new Cell(new byte[]{pos[0],pos[1],(byte)(pos[2]+1)},step));
        }

        if(pos[2]!=0 && grid[pos[0]][pos[1]][pos[2]-1]!='o'){
            grid[pos[0]][pos[1]][pos[2]-1]='o';
            was.add(new Cell(new byte[]{pos[0],pos[1],(byte)(pos[2]-1)},step));
        }
    }

    /**
     * Класс клетки лабиринта
     */
    private static class Cell {
        /** Поле координат клетки */
        byte[] pos;
        /** Поле расстояния до клетки */
        short val;

        /** Конструктор, создпёт новый объект
         * @param pos Координаты клетки
         * @param val Расстояние до клетки
         */
        Cell(byte[] pos,short val){
            this.pos = pos;
            this.val = val;
        }
        /*
        @Override
        public boolean equals(Object obj) {
            return obj != null && obj instanceof Cell && Arrays.equals(this.pos, ((Cell) obj).pos);
        }

        @Override
        public int hashCode() {
            return 29*pos[0] + 31*pos[1] + 37*pos[2];
        }*/
    }

    /** Метод считывает данные из вайла и возвращает и в формате строки
     @param fileName Имя файла
     */
    static String readInputData(String fileName){
        StringBuilder InputData = new StringBuilder();
        try(FileReader reader = new FileReader(fileName))
        {
            int ch;
            while((ch = reader.read()) != -1)
            {
                InputData.append((char)ch);
            }
        }
        catch(IOException ex)
        {
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
