package array_max_value;

public class ArrayMaxValue
{
    public static Integer getMaxValue(int[] values)
    {
        //Виже ошибку только если пустой массив придет, вернётся значение Integer.MIN_VALUE
        //поправил условием
        if (values.length > 0) {
            int maxValue = Integer.MIN_VALUE;
            for (int value : values) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            return maxValue;
        }else
            return null;
    }
}