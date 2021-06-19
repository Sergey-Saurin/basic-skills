import java.util.concurrent.Callable;

public class MyCallable implements Callable
{

    private int regionCode;
    private long start;

    public MyCallable(int regionCode, long start)
    {
        this.regionCode = regionCode;
        this.start = start;
    }

    @Override
    public Object call() throws Exception {
        new CustomRecursiveAction(regionCode, start).compute();
        return null;
    }
}
