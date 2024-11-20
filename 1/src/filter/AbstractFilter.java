package filter;

@FunctionalInterface
public interface AbstractFilter <T> {
    public boolean accept(T t);
}
