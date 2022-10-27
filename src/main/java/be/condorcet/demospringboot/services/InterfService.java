package be.condorcet.demospringboot.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfService<T> {
    public T create(T t) throws Exception;

    public T read(Integer id) throws Exception;

    public T update(T t) throws Exception;

    public void delete(T t) throws Exception;

    public List<T> all() throws Exception;
    public Page<T> allp(Pageable pageable) throws Exception;

}
