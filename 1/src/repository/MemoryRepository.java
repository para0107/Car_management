package repository;

import domain.Identifiable;

import java.util.*;

public class MemoryRepository<ID, T extends Identifiable<ID>> implements IRepository<ID, T> {
    protected HashMap<ID, T> elements ;

    public MemoryRepository(){
        elements = new HashMap<>();
    }

    @Override
    public void add(ID id, T element)
    {
        elements.put(id, element);
    }
    @Override
    public Optional<T> delete(ID id){
        if(elements.containsKey(id))
        {  T entity = elements.remove(id);
            return Optional.of(entity);
            }
        else
            return Optional.empty();

    }
    public void modify(ID id, T element){
        if(elements.containsKey(id))
            elements.put(id, element);
        else
            throw new RuntimeException("Element not found");


    }
    @Override
    public Optional<T> findbyId(ID id){
        return Optional.of(elements.get(id));
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(elements.values());
    }
}
