package com.company;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        // f(x) = sin^n(a*x)*cos^m(b*x)
        try (Scanner scanner = new Scanner(System.in)) {
            double a, b, m, n, x0, x1;
            System.out.print("Введите n: ");
            n = scanner.nextDouble();
            System.out.print("Введите a: ");
            a = scanner.nextDouble();
            System.out.print("Введите m: ");
            m = scanner.nextDouble();
            System.out.print("Введите b: ");
            b = scanner.nextDouble();
            System.out.print("Введите x0: ");
            x0 = scanner.nextDouble();
            System.out.print("Введите x1: ");
            x1 = scanner.nextDouble();
            if (x1 < x0) {
                double t = x0;
                x0 = x1;
                x1 = t;
            }
            System.out.printf("Значение интеграла: %f\n", simpson(x0, x1, x -> Math.pow(Math.sin(a * x), n) * Math.pow(Math.cos(b * x), m), 1000));
        } catch (InputMismatchException e) {
            System.out.println("Числа a, b, m, n, x0 и x1 должны быть вещественного типа!");
        }
    }

    /**
     * Метод вычисления интеграла по методу Симпсона (формула Котеса)
     *
     * @param a нижний предел интегрирования
     * @param b верхний предел интегрирования
     * @param f функция интегрирования
     * @param n количество отрезков разбиения
     * @return значение определённого интеграла
     */
    private static double simpson(double a, double b, Function<Double, Double> f, int n) {
        double h = (b - a) / (2 * n); // шаг
        double evenSum = 0D; // сумма значений чётных точек
        double oddSum = 0D; // сумма значений нечетных точек
        double[] points = new double[2 * n + 1]; // массив точек
        for (int i = 0; i < points.length; i++) {
            points[i] = a + i * h; // заполнение массива точек
        }
        for (int i = 1; i <= 2 * n - 1; i += 2) {
            oddSum += f.apply(points[i]); // подсчёт суммы значений нечётных точек
        }
        for (int i = 2; i <= 2 * n - 2; i += 2) {
            evenSum += f.apply(points[i]); // подсчёт суммы значений чётных точек
        }
        return h * (f.apply(points[0]) + 4 * oddSum + 2 * evenSum + f.apply(points[2 * n])) / 3; // вычисление формулы
    }

}
