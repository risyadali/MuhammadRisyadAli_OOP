package Repository;

import java.util.*;

public abstract class BaseRepository<T, ID> {
    protected Map<ID, T> data = new HashMap<>();
    protected List<T> list = new ArrayList<>();

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(data.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(list);
    }

    public abstract void save(T entity);
    public abstract ID getId(T entity);
}