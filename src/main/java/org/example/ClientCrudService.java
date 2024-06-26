package org.example;
import javax.persistence.*;
import java.util.List;

public class ClientCrudService {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");

    public Client createClient(Client client) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(client);
        transaction.commit();
        entityManager.close();
        return client;
    }

    public Client getClientById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Client client = entityManager.find(Client.class, id);
        entityManager.close();
        return client;
    }

    public List<Client> getAllClients() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Client> clients = entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        entityManager.close();
        return clients;
    }

    public void deleteClientById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        }
        transaction.commit();
        entityManager.close();
    }
}

