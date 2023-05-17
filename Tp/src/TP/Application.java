package TP;
import java.util.Map;
import java.util.HashMap;
import java.io.*;


public class Application {
    private Map<String, Utilisateur> utilisateurs;
    private String storageFilePath;

    public Application() {
        utilisateurs = new HashMap<>();
        storageFilePath = "newfile.txt"; 
    }
    public Utilisateur getUtilisateur(Utilisateur user)
    {
     return utilisateurs.get(user.getPseudo());

    }
    
    public Utilisateur authentifier(String pseudo) {
        chargerUtilisateurs(); 
        if (utilisateurs.containsKey(pseudo)) {
            System.out.println("il existe");
            return utilisateurs.get(pseudo);
        } else {
            System.out.println("il n'existe pas !");
            return null;
        }
    }

    public void setUtilisateur(Utilisateur user)
    {
        utilisateurs.put(user.getPseudo(),user);
    }
    public void ajouterUtilisateur(Utilisateur user) {
        chargerUtilisateurs(); // Load existing users
        
        if (!utilisateurs.containsKey(user.getPseudo())) {
            utilisateurs.put(user.getPseudo(), user);
            sauvegarderUtilisateurs(); // Save the updated users, including the new user
            System.out.println("Utilisateur ajouté avec succès !");
        } else {
            System.out.println("Pseudo déjà utilisé. Veuillez choisir un autre pseudo.");
        }
    }
    
  
    public void sauvegarderUtilisateurs() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(storageFilePath))) {
            outputStream.writeObject(utilisateurs);
            System.out.println("Sauvegarde des utilisateurs effectuée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
        
    }
    
    public void chargerUtilisateurs() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(storageFilePath))) {
            Map<String, Utilisateur> loadedUtilisateurs = (Map<String, Utilisateur>) inputStream.readObject();
            utilisateurs.putAll(loadedUtilisateurs); // Merge the loaded users with the existing ones
            System.out.println("Chargement des utilisateurs effectué avec succès !");
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de stockage des utilisateurs n'existe pas. Un nouveau fichier sera créé lors de la sauvegarde.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
    } 
}

