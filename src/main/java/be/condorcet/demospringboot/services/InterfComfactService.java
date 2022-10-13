package be.condorcet.demospringboot.services;

import be.condorcet.demospringboot.entities.Client;
import be.condorcet.demospringboot.entities.Comfact;

import java.util.List;

public interface InterfComfactService extends InterfService<Comfact> {
    public List<Comfact> getComfacts(Client cl);
}