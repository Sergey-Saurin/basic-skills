public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius)
    {
        double areaСircle = Math.PI * (radius * radius);
        return areaСircle;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius)
    {
        double sphereVolume = (4 * Math.PI * Math.pow(radius, 3)) / 3;
        return sphereVolume;
    }

    public static boolean isTriangleRightAngled(double a, double b, double c)
    {
        //В уроке было сказано что при поиске максимального значения из более чем двух переменных
        //лучше использовать массив, но по заданию нужно использовать методы Math поэтому так
        double variable1 = Math.max(a, b);
        double variable2 = Math.max(b, c);
        double variableMax = Math.max(variable1, variable2);
        //Условие возможности построения треугольника:
        // у треугольника сумма любых двух сторон должна быть больше третьей.
        //
        //Поэтому берем сумму двух меньших сторон, если сумма меньших сторон больше, значит
        // треугольник может существовать
        //Здесь получается сумма двух сторон за минусом большей стороны
        double sumTwoSides = (a + b + c) - variableMax;

        return variableMax < sumTwoSides;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c)
    {
        boolean mayExist = isTriangleRightAngled(a, b, c);

        if(mayExist)
        {
            //Используем формулу описанную в рекомендациях к заданию
            double p = (a + b + c)/2; // p - полупериметр из рекомендации
            double areaTriangle = Math.sqrt(Math.abs(p * (p - a) * (p - b) * (p - c)));
            return areaTriangle;
        } else return -1;
    }
}
