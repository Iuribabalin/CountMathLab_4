package Lab_4;

import Lab_4.Functions.Fun;
import Lab_4.GausMethod.GausMethod;
import Lab_4.Input.InputConsole;
import Lab_4.Input.InputFile;
import Lab_4.Output.OutputConsole;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class main {

    public static void main(String[] arg){
        InputConsole in = new InputConsole();
        InputFile in_file = new InputFile();

        double[][] points;
        double[][] dataSetChart = new double[5][3];

        System.out.println("Программа начала работу");
        while (true) {
            if (in.console_or_file()) {
                String name_file = in.inNameFile();
                boolean flag_all_data_file;
                try {
                    points = in_file.getDataSet(new FileReader("src/main/resources/" + name_file + ".txt"));
                    flag_all_data_file = in_file.with_all_data(new
                            FileReader("src/main/resources/" + name_file + ".txt"));
                    start_count(points,dataSetChart,flag_all_data_file);
                }catch (FileNotFoundException e) {
                    System.out.println("Ошибка в имени файла, его не существует");
                }
            } else {
                boolean flag_all_data = in.with_all_data();
                points = in.inputPoints();
                start_count(points,dataSetChart,flag_all_data);
            }
        }
    }


    private static void start_count(double[][] points, double[][] dataSetChart, boolean flag_all_data){
        OutputConsole out = new OutputConsole();
        GausMethod gausMethod = new GausMethod();
        Fun f = new Fun();

        double sum_x = sum_x(points), sum_xx = sum_xx(points), sum_xy = sum_xy(points), sum_y = sum_y(points);
        double sum_x_ln = sum_x_ln(points), sum_xx_ln = sum_xx_ln(points), sum_xy_ln = sum_xy_ln(points);
        double sum_y_ln = sum_y_ln(points), sum_y_ln_x = sum_y_ln_x(points), sum_x_ln_y = sum_x_ln_y(points);

        double sum_xxx = sum_xxx(points), sum_xxxx = sum_xxxx(points), sum_xxy = sum_xxy(points);

        double[][] matrix = new double[][]{{points[0].length, sum_x, sum_xx, sum_y},
                {sum_x, sum_xx, sum_xxx, sum_xy},
                {sum_xx, sum_xxx, sum_xxxx, sum_xxy}};

        int n_matrix = 3;

        double[] params_sqr_f = gausMethod.startMethod(matrix, n_matrix);

        if (flag_all_data) {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Sum X = " + sum_x + " Sum XX = " +
                    sum_xx + " Sum Y = " + sum_y + " Sum XY = " + sum_xy);

            dataSetChart[0][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 1);
            dataSetChart[0][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 1);

            out.print_table_all(points, dataSetChart[0][0], dataSetChart[0][1], 0, 1);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Sum ln(x) = " + sum_x_ln + " Sum ln(y) = " + sum_y_ln +
                    " Sum (ln(x))^2 = " + sum_xx_ln + " Sum ln(x)*ln(y) = " + sum_xy_ln);

            dataSetChart[1][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 2);
            dataSetChart[1][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 2);
            out.print_table_all(points, dataSetChart[1][0], dataSetChart[1][1], 0, 2);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Sum x = " + sum_x + " Sum ln(y) = " + sum_y_ln +
                    " Sum x^2 = " + sum_xx + " Sum ln(y)*x = " + sum_y_ln_x);
            dataSetChart[2][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 3);
            dataSetChart[2][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 3);

            out.print_table_all(points, dataSetChart[2][0], dataSetChart[2][1], 0, 3);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Sum ln(x) = " + sum_x_ln + " Sum y = " + sum_y +
                    " Sum (ln(x))^2 = " + sum_xx_ln + " Sum ln(x)*y = " + sum_x_ln_y);
            dataSetChart[3][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 4);
            dataSetChart[3][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 4);

            out.print_table_all(points, dataSetChart[3][0], dataSetChart[3][1], 0, 4);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Sum x = " + sum_x + " Sum y = " + sum_y +
                    " Sum x*y = " + sum_xy + " Sum y*x^2= " + sum_xxy +
                    " Sum x^2 = " + sum_xx + " Sum x^3 = " + sum_xxx + " Sum x^4 = " + sum_xxxx);
            dataSetChart[4][0] = params_sqr_f[2];
            dataSetChart[4][1] = params_sqr_f[1];
            dataSetChart[4][2] = params_sqr_f[0];
            out.print_table_all(points, params_sqr_f[2], params_sqr_f[1], params_sqr_f[0], 5);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        } else {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            dataSetChart[0][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 1);
            dataSetChart[0][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 1);

            out.print_table_short(points, dataSetChart[0][0], dataSetChart[0][1], 0, 1);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            dataSetChart[1][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 2);
            dataSetChart[1][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 2);

            out.print_table_short(points, dataSetChart[1][0], dataSetChart[1][1], 0, 2);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            dataSetChart[2][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 3);
            dataSetChart[2][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 3);

            out.print_table_short(points, dataSetChart[2][0], dataSetChart[2][1], 0, 3);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            dataSetChart[3][0] = f.getA(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 4);
            dataSetChart[3][1] = f.getB(sum_x, sum_xx, sum_y, sum_xy, sum_x_ln, sum_y_ln, sum_xx_ln,
                    sum_xy_ln, sum_y_ln_x, sum_x_ln_y, points[0].length, 4);

            out.print_table_short(points, dataSetChart[3][0], dataSetChart[3][1], 0, 4);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            dataSetChart[4][0] = params_sqr_f[2];
            dataSetChart[4][1] = params_sqr_f[1];
            dataSetChart[4][2] = params_sqr_f[0];
            out.print_table_short(points, params_sqr_f[2], params_sqr_f[1], params_sqr_f[0], 5);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        f.selected_function(points, dataSetChart);
        out.drawCharts(points, dataSetChart);
    }

    private static double sum_x(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            sum+= points[0][i];
        }
        return sum;
    }

    private static double sum_xx(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            sum += Math.pow(points[0][i],2);
        }
        return sum;
    }

    private static double sum_xxx(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            sum += Math.pow(points[0][i],3);
        }
        return sum;
    }
    private static double sum_xxxx(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            sum += Math.pow(points[0][i],4);
        }
        return sum;
    }
    private static double sum_xxy(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            sum += Math.pow(points[0][i],2)*points[1][i];
        }
        return sum;
    }
    private static double sum_xy(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            sum += points[0][i] * points[1][i];
        }
        return sum;
    }

    private static double sum_y(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            sum += points[1][i];
        }
        return sum;
    }

    private static double sum_x_ln(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            if(points[0][i]!=0)
                sum+= Math.log(points[0][i]);
        }
        return sum;
    }

    private static double sum_xx_ln(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            if(points[0][i]!=0)
                sum += Math.pow(Math.log(points[0][i]),2);
        }
        return sum;
    }

    private static double sum_xy_ln(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            if(points[0][i]!=0 && points[1][i]!=0)
            sum += Math.log(points[0][i]) * Math.log(points[1][i]);
        }
        return sum;
    }

    private static double sum_y_ln(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            if(points[1][i]!=0)
                sum += Math.log(points[1][i]);
        }
        return sum;
    }

    private static double sum_y_ln_x(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            if(points[1][i]!=0)
                sum += Math.log(points[1][i])*points[0][i];
        }
        return sum;
    }

    private static double sum_x_ln_y(double[][] points){
        double sum = 0;
        for(int i = 0; i < points[0].length; i++){
            if(points[0][i]!=0)
                sum += Math.log(points[0][i])*points[1][i];
        }
        return sum;
    }
}
