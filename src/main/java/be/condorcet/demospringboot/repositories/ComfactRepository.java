package be.condorcet.demospringboot.repositories;

import be.condorcet.demospringboot.entities.Client;
import be.condorcet.demospringboot.entities.Comfact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComfactRepository extends JpaRepository<Comfact, Integer> {
    public List<Comfact> findComfactByClient(Client cl);
}