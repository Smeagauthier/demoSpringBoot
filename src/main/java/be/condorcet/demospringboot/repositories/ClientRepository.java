package be.condorcet.demospringboot.repositories;
import be.condorcet.demospringboot.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findClientByLocaliteEndingWithAndCpAfter(String finloc, Integer cp); //attention faut utiliser les wrappers classes

    List<Client> findClientsByNomLike(String s);
}
