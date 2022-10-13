package be.condorcet.demospringboot.services;

import be.condorcet.demospringboot.entities.Comfact;
import be.condorcet.demospringboot.entities.Client;

import java.util.List;
import be.condorcet.demospringboot.repositories.ClientRepository;
import be.condorcet.demospringboot.repositories.ComfactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ComfactServiceImpl  implements InterfComfactService{
    @Autowired
    private ComfactRepository comfactRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Comfact create(Comfact comfact) throws Exception {
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public Comfact read(Integer id) throws Exception {
        return comfactRepository.findById(id).get();
    }

    @Override
    public Comfact update(Comfact comfact) throws Exception {
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public void delete(Comfact comfact) throws Exception {
        comfactRepository.deleteById(comfact.getNumcommande());
    }

    @Override
    public List<Comfact> all() throws Exception {
        return comfactRepository.findAll();
    }

    @Override
    public List<Comfact> getComfacts(Client cl) {
        List<Comfact> lcf = comfactRepository.findComfactByClient(cl);
        return lcf;
    }
}