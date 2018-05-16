package br.com.rodrigocardoso.daos;

import br.com.rodrigocardoso.models.Entidade;

import java.util.List;

/**
 * Created by rodri on 01/05/2018.
 */
public interface IDao <E extends Entidade> {
    public Integer save(Entidade value);
    public Boolean update(Entidade value);
    public E get(Integer id);
    public List<E> getAll();
    public Boolean delete(Integer id);
}
