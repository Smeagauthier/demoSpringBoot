package be.condorcet.demospringboot;

import be.condorcet.demospringboot.entities.Client;
import be.condorcet.demospringboot.entities.Comfact;
import be.condorcet.demospringboot.repositories.ClientRepository;
import be.condorcet.demospringboot.repositories.ComfactRepository;
import be.condorcet.demospringboot.services.ClientServiceImpl;
import be.condorcet.demospringboot.services.ComfactServiceImpl;
import be.condorcet.demospringboot.services.InterfClientService;
import be.condorcet.demospringboot.services.InterfComfactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/comfacts")
public class GestComfact {

    @Autowired
    private InterfClientService clientServiceImpl;
    @Autowired
    private InterfComfactService comfactServiceImpl;

    @RequestMapping("/rechparcli")
    public String read(@RequestParam int idclient, Map<String, Object> model) {
        System.out.println("recherche du client n° " + idclient);
        try {
          /*  Optional<Client> ocl = clientRepository.findById(idclient);//findById lance une exception si id inconnu
            Client cl = ocl.get();
            List<Comfact> lcf = comfactRepository.findComfactByClient(cl);*/
            Client cl = clientServiceImpl.read(idclient);
            List<Comfact> lcf = comfactServiceImpl.getComfacts(cl);
            model.put("moncli", cl);
            model.put("clicf", lcf);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affclicf";
    }
}