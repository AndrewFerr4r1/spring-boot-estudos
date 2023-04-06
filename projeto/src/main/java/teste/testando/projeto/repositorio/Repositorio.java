package teste.testando.projeto.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import teste.testando.projeto.model.Usuario;

import java.util.List;

@Repository
public interface Repositorio extends CrudRepository<Usuario, Integer> {

    List<Usuario> findAll();


    Usuario findById(int codigo);

    List<Usuario> findByOrderByNomeDesc();

    List<Usuario> findByNomeOrderByCidade(String nome);

    List<Usuario> findByNomeContaining(String termo);

    List<Usuario> findByNomeStartsWith(String termo);

    List<Usuario> findByNomeEndsWith(String termo);

    @Query(value = "SELECT * FROM Usuario where id = :id", nativeQuery = true)
    List<Usuario> pesquisarPorId(int id);

    int countById(int id);
}
