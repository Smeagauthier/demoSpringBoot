package be.condorcet.demospringboot.services;


import be.condorcet.demospringboot.entities.Client;

import java.util.List;

public interface  InterfClientService extends InterfService<Client> {
    public List<Client> read(String nom);
}
