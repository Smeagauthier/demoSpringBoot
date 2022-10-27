package be.condorcet.demospringboot;

import be.condorcet.demospringboot.entities.Client;
import be.condorcet.demospringboot.services.ClientServiceImpl;
import be.condorcet.demospringboot.services.ClientServiceStub;
import be.condorcet.demospringboot.services.InterfClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/clients")
public class GestClient {

    @Autowired
    private InterfClientService clientServiceImpl;


    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche clients");
        try {
            //Collection<Client> lcl = clientRepository.findAll();
            Collection<Client> lcl = clientServiceImpl.all();
            model.put("mesClients", lcl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affichagetousClients";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String nom, @RequestParam String prenom, @RequestParam String tel, Map<String, Object> model) {
        System.out.println("création de client");
        Client cl = new Client(nom, prenom, tel);
        try {
            //clientRepository.save(cl);//mise à jour du client avec son id par l'environnement
            clientServiceImpl.create(cl);
            cl = clientServiceImpl.read(cl.getIdclient());
            cl.setCp(1000);
            clientServiceImpl.update(cl);
            System.out.println(cl);
            model.put("nouvcli", cl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "nouveauClient";
    }

    @RequestMapping("/read")
    public String read(@RequestParam int idclient, Map<String, Object> model) {
        System.out.println("recherche du client n° " + idclient);
        try {
            Client cl = clientServiceImpl.read(idclient);
            model.put("moncli", cl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affclient";
    }
}
