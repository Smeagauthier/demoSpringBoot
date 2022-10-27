package be.condorcet.demospringboot.services;

import be.condorcet.demospringboot.entities.Client;
import be.condorcet.demospringboot.entities.Comfact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImplTest {

    @Autowired
    private ClientServiceImpl clientServiceImpl;
    @Autowired
    private ComfactServiceImpl comfactServiceImpl;

    Client cl;

    @BeforeEach //equivalent de populate, execution au lancement du test
    void setUp() {
        try {
            cl = new Client(null, "NomTest", "PrenomTest", 1000, "LocTest", "rue test", "1", "0001", new ArrayList<>());
            clientServiceImpl.create(cl);
            System.out.println("création du client: " + cl);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du client: " + cl + " erreur: " + e.getMessage());
        }
    }

    @AfterEach //pour effacer l'élément à chaque fin de test
    void tearDown() {
        try {
            clientServiceImpl.delete(cl);
            System.out.println("effacement du client: " + cl);
        } catch (Exception e) {
            System.out.println("erreur d'effacement du client: " + cl + " erreur: " + e.getMessage());
        }
    }

    @Test
        //chaque méthode du test qui correspond au crud doit être annotée de @Test
    void create() {
        assertNotEquals(0, cl.getIdclient(), "id client non incrémenté");
        assertEquals("NomTest", cl.getNom(), "nom client non enregistré: " + cl.getNom() + " au lieu de NomTest");
        assertEquals("PrenomTest", cl.getPrenom(), "prénom client non enregistré: " + cl.getPrenom() + " au lieu de PrenomTest");
    }

    @Test()
    void creationDoublon() {   //ajouté
        Client cl2 = new Client(null, "NomTest", "PrenomTest", 2000, "LocTest2", "rue test2", "1", "0001", null); //changer les valeurs des cp, localités,...car même si il y a une contrainte d'unicité sur les 7 champs, il peut y avoir plusieurs personnes dans ces loc
        Assertions.assertThrows(Exception.class, () -> { //on teste une méthode pour voir si elle renvoie une exception
            clientServiceImpl.create(cl2); //méthode à invoquer pour tester
        }, "création d'un doublon");
    }


    @Test
    void read() {
        try {
            int numcli = cl.getIdclient();
            Client cl2 = clientServiceImpl.read(numcli);
            assertEquals("NomTest", cl2.getNom(), "noms différents " + " NomTest" + " - " + cl2.getNom());
            assertEquals("PrenomTest", cl2.getPrenom(), "prénoms différents " + " PrenomTest" + " - " + cl2.getPrenom()); //etc
        } catch (Exception e) {
            fail("recherche infructueuse " + e);
        }
    }

    @Test
    void update() {
        try {
            cl.setNom("NomTest2");
            cl.setPrenom("PrenomTest2");
            //etc
            cl = clientServiceImpl.update(cl);
            assertEquals("NomTest2", cl.getNom(), "noms différents " + " NomTest2 - " + cl.getNom());
            assertEquals("PrenomTest2", cl.getPrenom(), "prénoms différents " + " PrenomTest2 - " + cl.getPrenom());//etc
        } catch (Exception e) {
            fail("erreur de mise à jour " + e);
        }
    }

    @Test
    void delete() {
        try {
            clientServiceImpl.delete(cl);
            Assertions.assertThrows(Exception.class, () -> {
                clientServiceImpl.read(cl.getIdclient());
            }, "record non effacé");
        } catch (Exception e) {
            fail("erreur d'effacement " + e);
        }
    }

    @Test
    void delAvecCom() { //ajouté
        try {
            Comfact cf = new Comfact(null, null, Date.valueOf(LocalDate.now()), "C", new BigDecimal(1000), cl);
            comfactServiceImpl.create(cf);
            cl.getComfacts().add(cf);
            clientServiceImpl.update(cl);
            Assertions.assertThrows(Exception.class, () -> {
                clientServiceImpl.delete(cl);
            }, "effacement réalisé malgré commande liée");
            comfactServiceImpl.delete(cf);
        } catch (Exception e) {
            fail("erreur de création de commande" + e);
        }

    }

    @Test
    void rechNom() {
        List<Client> lc = clientServiceImpl.read("NomTest");
        boolean trouve = false;
        for (Client c : lc) {
            if (c.getNom().equals("NomTest")) trouve = true;
            else fail("un record ne correspond pas , nom = " + c.getNom());
        }
        assertTrue(trouve, "record non trouvé dans la liste");
    }

    @Test
    void all() {
        try {
            List<Client> lc = clientServiceImpl.all();
            assertNotEquals(0, lc.size(), "la liste ne contient aucun élément");
        } catch (Exception e) {
            fail("erreur de recherche de tous les clients " + e);
        }
    }
}