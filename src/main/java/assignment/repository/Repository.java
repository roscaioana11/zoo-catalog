package assignment.repository;

import java.util.List;

public interface Repository<C> {
    List<C> getAll();
    C get(Long id);
    void add(C newElement);
    void delete(Long id);
}
