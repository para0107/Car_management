package repository;

import domain.Car;
import domain.Identifiable;
import filter.AbstractFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilteredRepository<T extends Identifiable<ID>,ID> extends MemoryRepository<ID, T>
{
    public AbstractFilter<T> filter;
    private Collection<T> items = new ArrayList<>();
    public FilteredRepository(AbstractFilter<T> filter)
    {
this.filter = filter;
    }
    public List<T> getAll() {
        List<T> filteredElements = new ArrayList<>();

        for (T element : super.getAll()) {
            if (filter.accept(element)) {
                filteredElements.add(element);
            }
        }
        return filteredElements;
    }

//public void add(T item) {
//    if (filter.accept(item)) {
//        items.add(item);
//    }
//}
//
//    public Collection<T> getAll() {
//        return items;
//    }
}
