package Lab_4.Input;

import java.io.FileReader;
import java.util.Scanner;

public class InputFile {
    public double[][] getDataSet(FileReader fr){
        Scanner scan = new Scanner(fr);
        int itr = 0;
        double[][] points = new double[2][0];
        int n = 0;
        while (scan.hasNextLine()) {
            if(itr == 0){
                try {
                    String str = scan.nextLine().trim();
                    n = Integer.parseInt(str);
                    points = new double[2][n];
                    itr++;
                }catch (NumberFormatException ex){
                    System.out.println("Ошибка в данных файла при получении количества точек");
                    break;
                }
            }else if(itr == 1){
                String str = scan.nextLine().trim();
                for(int i = 0; i < n; i++){
                    try {
                        points[0][i] = Double.parseDouble(str.split(" ")[i]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Ошибка в данных файла (неверный формат данных внутри строки)");
                        break;
                    }
                }
                itr++;
            }else if(itr == 2){
                String str = scan.nextLine().trim();
                for(int i = 0; i < n; i++){
                    try {
                        points[1][i] = Double.parseDouble(str.split(" ")[i]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Ошибка в данных файла (неверный формат данных внутри строки)");
                        break;
                    }
                }
                break;
            }
        }

        return points;
    }

    public boolean with_all_data(FileReader fr){
        Scanner scan = new Scanner(fr);
        String str = "";
        int itr = 0;
        while (scan.hasNextLine()) {
            if (itr == 3) {
                str = scan.nextLine().trim();
            } else {
                itr++;
            }
        }
        return str.equals("true");
    }
}
