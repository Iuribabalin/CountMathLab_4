package Lab_4.Output;

import Lab_4.Functions.Fun;

public class OutputConsole {
    Fun f = new Fun();
    public void print_table_all(double[][] points, double a, double b, double c, int nf){
        double sym_for_x = 0; double sym_for_y = 0;

        if(nf == 1)
            System.out.println("ЛИНЕЙНАЯ ФУНКЦИЯ");
        else if(nf == 2)
            System.out.println("ПОЛИНОМИАЛЬНАЯ ФУНКЦИЯ 2-Й СТЕПЕНИ");
        else if(nf == 3)
            System.out.println("ЭКСПОНЕНТАЛЬНАЯ ФУНКЦИЯ");
        else if(nf == 4)
            System.out.println("ЛОГОРИФМИЧСКАЯ ФУНКЦИЯ");
        else
            System.out.println("СТЕПЕННАЯ ФУНКЦИЯ");


        System.out.printf("|%-14s","№");
        for(int i = 1; i <= points[0].length; i++){
            System.out.printf("|%-12d", i);
        }
        System.out.println("|");

        System.out.printf("|%-14s","X");
        for(int i = 0; i < points[0].length; i++){
            sym_for_x+=points[0][i];
            System.out.printf("|%-12f", points[0][i]);
        }
        System.out.println("|");

        System.out.printf("|%-14s","Y");
        for(int i = 0; i < points[0].length; i++){
            sym_for_y+=points[1][i];
            System.out.printf("|%-12f", points[1][i]);
        }
        System.out.println("|");

        double x_ = sym_for_x/points[0].length;double y_ = sym_for_y/points[0].length;
        double sum_up_r = 0; double sum_down_r_x = 0; double sum_down_r_y = 0;


        if(nf == 1)
            System.out.printf("|%-14s","Fi=ax+b");
        else if(nf == 2)
            System.out.printf("|%-14s","Fi=ax^b");
        else if(nf == 3)
            System.out.printf("|%-14s","Fi=ae^bx");
        else if(nf == 4)
            System.out.printf("|%-14s","Fi=a*ln(x)+b");
        else
            System.out.printf("|%-14s","Fi=ax^2+bx^2+c");

        for(int i = 0; i < points[0].length; i++){
            sum_up_r+=(points[0][i] - x_)*(points[1][i]-y_);
            sum_down_r_x+=Math.pow(points[0][i] - x_,2);
            sum_down_r_y+=Math.pow(points[1][i]-y_,2);

            System.out.printf("|%-12f", f.f(points[0][i],a,b,c,nf));
        }
        System.out.println("|");


        System.out.printf("|%-14s","Eps");
        for(int i = 0; i < points[0].length; i++){
            System.out.printf("|%-12f", f.f(points[0][i],a,b,c,nf) - points[1][i]);
        }
        System.out.println("|");

        System.out.println("A = " + a);
        System.out.println("B = " + b);

        double sum_for_S = f.deviation_measure(points,a,b,c,nf);

        System.out.println("Мера отклонения = " + sum_for_S);

        if(nf == 1) {
            double r = sum_up_r / Math.sqrt(sum_down_r_x * sum_down_r_y);
            System.out.println("Коэффициент корреляции = " + r);
            r = Math.abs(r);
            if (r == 0)
                System.out.println("связь между переменными отсутствует");
            else if (r > 0 && r < 0.3)
                System.out.println("связь слабая");
            else if (r >= 0.3 && r < 0.5)
                System.out.println("связь умеренная");
            else if (r >= 0.5 && r < 0.7)
                System.out.println("связь заметная");
            else if (r >= 0.7 && r < 0.9)
                System.out.println("связь высокая");
            else if (r >= 0.9)
                System.out.println("связь весьма высокая");
        }

        System.out.println("Среднеквадратичное отклонение = " + f.square_deviation(points,a,b,c,nf));
        System.out.println("Достоверность аппроксимации = " + f.getR(points,a,b,c,nf));
        System.out.println("-------------------------------------------------------------------------\n");
    }

    public void print_table_short(double[][] points, double a, double b, double c, int nf){
        if(nf == 1) {
            System.out.printf("|%-18s|%-15s|%-15s|%-15s|%-17s|%-32s|%-32s|\n",
                    "Вид функции", "a", "b", "c", "Мера отклонения S", "Среднеквадратичное отклонение q",
                    "Достоверность аппроксимации");
        }
        if(nf == 1)
        System.out.printf("|%-18s|%-15f|%-15f|%-15s|%-17f|%-32f|%-32s|\n",
                "Fi = ax+b",a ,b ,"-",f.deviation_measure(points,a,b,0,nf),
                f.square_deviation(points,a,b,0,nf),f.getR(points,a,b,0,nf));
        else if(nf == 2)
            System.out.printf("|%-18s|%-15f|%-15f|%-15s|%-17f|%-32f|%-32s|\n",
                    "Fi = ax^b",a ,b ,"-",f.deviation_measure(points,a,b,0,nf),
                    f.square_deviation(points,a,b,0,nf),f.getR(points,a,b,0,nf));
        else if(nf == 3)
            System.out.printf("|%-18s|%-15f|%-15f|%-15s|%-17f|%-32f|%-32s|\n",
                    "Fi = ae^bx",a ,b ,"-",f.deviation_measure(points,a,b,0,nf),
                    f.square_deviation(points,a,b,0,nf),f.getR(points,a,b,0,nf));
        else if(nf == 4)
            System.out.printf("|%-18s|%-15f|%-15f|%-15s|%-17f|%-32f|%-32s|\n",
                    "Fi = a*ln(x)+b",a ,b ,"-",f.deviation_measure(points,a,b,0,nf),
                    f.square_deviation(points,a,b,0,nf),f.getR(points,a,b,0,nf));
        else
            System.out.printf("|%-18s|%-15f|%-15f|%-15f|%-17f|%-32f|%-32s|\n",
                    "Fi = ax^2+bx^2+c",a ,b ,c,f.deviation_measure(points,a,b,c,nf),
                    f.square_deviation(points,a,b,c,nf),f.getR(points,a,b,c,nf));
    }

    public void drawCharts(double[][] points, double[][] dataSetChart){
        DrawChart draw = new DrawChart();
        draw.draw(points,dataSetChart);
    }

}
