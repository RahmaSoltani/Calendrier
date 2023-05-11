package TP;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Map;
import java.time.LocalDate;
import java.util.TreeMap;
public class Calendrier implements Serializable {
    
    private Map<LocalDateTime, Creneau> creneaux;

    public Calendrier() {
        creneaux = new TreeMap<>(Comparator.naturalOrder());
    }

    public void ajouterCreneau(Creneau creneau) {
        creneaux.put(creneau.getDebut(), creneau);
    }

    public void supprimerCreneau(Creneau creneau) {
        creneaux.remove(creneau.getDebut());
    }

    public Creneau getCreneau(LocalDateTime debut) {
        return creneaux.get(debut);
    }

    public boolean hasCreneau(LocalDateTime debut) {
        return creneaux.containsKey(debut);
    }

    public Map<LocalDateTime, Creneau> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(Map<LocalDateTime, Creneau> creneaux) {
        this.creneaux = creneaux;
    }
    
    public Creneau getPremierCreneauDisponible(LocalDateTime debut, LocalDateTime fin, LocalTime dureeMin) {
    for (Creneau creneau : creneaux.values()) {
        LocalDateTime creneauDebut = creneau.getDebut();
        LocalDateTime creneauFin = creneau.getFin();
        
    
        if (creneauDebut.isAfter(debut) && creneauFin.isBefore(fin)) {
            // Vérifier si la durée du créneau est supérieure ou égale à dureeMin
            if (creneau.Duree().compareTo(dureeMin) >= 0) {
                return creneau;
            }
        }
    }
    
    return null; // Retourner null si aucun créneau n'est trouvé
}
public int getNumberOFtasksInDay(LocalDate jour)
{
    ArrayList<Tache> tasks=retournertachesjour(jour);
    return tasks.size();
}  
public int getNumberOfCompletedTasksInDay(LocalDate jour) {
      ArrayList<Tache> tasks = retournertachesjour(jour);
      int count = 0;
    
      for (Tache task : tasks) {
          if (task.getEtat() == Etat.Completed) {
              count++;
          }
      }
    
      return count;
    }

public ArrayList<Tache> retournertachesjour(LocalDate jour) {
    ArrayList<Tache> tachesJour = new ArrayList<>();
    
    for (Creneau creneau : creneaux.values()) {
        LocalDate creneauDate = creneau.getDebut().toLocalDate();
        
        if (creneauDate.equals(jour)) {
            Tache tache = creneau.getTache();
            if (tache != null) {
                tachesJour.add(tache);
            }
        }
    }
    
    return tachesJour;
}

/*     
   public void planifierTaches(LocalDate debut, LocalDate fin, List<Tache> taches) {
    int tacheIndex = 0; // Indice de la tâche en cours
    for (LocalDate date = debut; !date.isAfter(fin) && tacheIndex < taches.size(); date = date.plusDays(1)) {
        List<Creneau> creneauxLibres = calendar.getCreneauxLibres(date);
        for (Creneau creneau : creneauxLibres) {
            if (tacheIndex >= taches.size()) {
                break; // Toutes les tâches ont été planifiées
            }
            Tache tache = taches.get(tacheIndex);
            creneau.setTache(tache);
            creneau.setLibre(false);
            calendar.ajouterCreneau(creneau);
            tachesplan.add(tache);
            tachesnonplan.remove(tache);
            tacheIndex++;
        }
    }
    if (tacheIndex < taches.size()) {
        System.out.println("Le nombre de créneaux libres est insuffisant.");
    }
} */
}
