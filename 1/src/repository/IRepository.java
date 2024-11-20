package repository;

import domain.Identifiable;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<ID,T extends Identifiable<ID>> {
    public void add(ID id, T t);
    public Optional<T> delete(ID id);
    public void modify(ID id, T t);
    public Optional <T> findbyId(ID id);
    public Collection<T> getAll();
}
