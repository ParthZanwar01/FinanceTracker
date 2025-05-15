import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManager {
    private static final String DATA_FILE = "clients.dat";
    private Map<String, Client> clients;
    private Map<String, List<Account>> clientAccounts;

    // Singleton instance
    private static ClientManager instance;

    // Get singleton instance
    public static ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    // Private constructor for singleton
    private ClientManager() {
        clients = new HashMap<>();
        clientAccounts = new HashMap<>();
        loadData();
    }

    // Add a new client
    public boolean addClient(Client client) {
        if (clients.containsKey(client.getId())) {
            return false; // Client already exists
        }

        clients.put(client.getId(), client);
        clientAccounts.put(client.getId(), new ArrayList<>());
        saveData();
        return true;
    }

    // Get client by ID
    public Client getClient(String clientId) {
        return clients.get(clientId);
    }

    // Get all clients
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    // Add account for a client
    public boolean addAccount(Account account) {
        String clientId = account.getOwner().getId();
        if (!clients.containsKey(clientId)) {
            return false; // Client doesn't exist
        }

        List<Account> accounts = clientAccounts.get(clientId);
        accounts.add(account);
        saveData(); // Auto-save when a new account is created
        return true;
    }

    // Get accounts for a client
    public List<Account> getClientAccounts(String clientId) {
        return clientAccounts.getOrDefault(clientId, new ArrayList<>());
    }

    // Find account by account number
    public Account findAccount(String accountNumber) {
        for (List<Account> accounts : clientAccounts.values()) {
            for (Account account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }

    // Save data to file
    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            out.writeObject(clients);
            out.writeObject(clientAccounts);
        } catch (IOException e) {
            System.err.println("Error saving client data: " + e.getMessage());
        }
    }

    // Load data from file
    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return; // No data file yet
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            clients = (Map<String, Client>) in.readObject();
            clientAccounts = (Map<String, List<Account>>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading client data: " + e.getMessage());
        }
    }
}