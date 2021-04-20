package Lab_4.Input;

import java.util.Scanner;

public class InputConsole {
    Scanner in = new Scanner(System.in);

    public double[][] inputPoints(){
        int n = inN();
        double[][] points = new double[2][n];

        for(int i = 0; i < n; i++){
            points[0][i] = inPointX();
            points[1][i] = inPointY();
        }

        return points;
    }

    public boolean console_or_file(){
        while (true) {
            System.out.print("Ввести данные через файл?(y/n)");
            String answer = in.nextLine().toLowerCase().trim();
            if(answer.equals("y"))
                return true;
            else if(answer.equals("n"))
                return false;
        }
    }

    public String inNameFile(){
        System.out.print("Введите имя файла (файл должен быть формата .txt): ");
        return in.nextLine().trim();
    }

    public boolean with_all_data(){
        while (true) {
            System.out.print("Выводить всю информацию для каждой функции?(y/n)");
            String answer = in.nextLine().toLowerCase().trim();
            if(answer.equals("y"))
                return true;
            else if(answer.equals("n"))
                return false;
        }
    }

    private int inN(){
        while (true) {
            System.out.print("Введите колиичество точек: ");
            try {
                int n = Integer.parseInt(in.next().trim());
                if(n >= 12)
                    return n;
                else
                    System.out.println("n должно быть 12 или больше");
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private double inPointX(){
        while (true) {
            System.out.print("Введите координату X: ");
            try {
                return Double.parseDouble(in.next().trim().replaceAll(",", "\\."));
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private double inPointY(){
        while (true) {
            System.out.print("Введите координату Y: ");
            try {
                return Double.parseDouble(in.next().trim().replaceAll(",", "\\."));
            } catch (NumberFormatException ignored) {
            }
        }
    }
}
