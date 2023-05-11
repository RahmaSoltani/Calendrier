package TP;

import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalTime;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.io.Serializable;

import java.time.LocalDateTime;
public class Utilisateur implements Serializable {
    private String pseudo;
    private Calendrier calendar;
    private ArrayList<Tache> taches;
    private ArrayList<Projet> projets;
    private LocalDate joursrnetable;
    private int nbtacherent;
    private ArrayList<Badge> Good ;
    private ArrayList<Badge> VeryGood;
    private ArrayList<Badge> Excelent;
    private ArrayList<Planning> planning;
    private int encouragement;
    private int min ;
    private int moyrend;
    private Timer endOfDayTimer;
    private LocalTime DureeHoby;
    private LocalTime DureeWork;
    private LocalTime DureeStudy;
    private long rendement;

    public Utilisateur() {
    }

    public Utilisateur(String nom) {
        pseudo = nom;
        taches = new ArrayList<>();
        calendar = new Calendrier();
        projets = new ArrayList<>();
        Good=new ArrayList<>();
        VeryGood=new ArrayList<>();
        Excelent =new ArrayList<>();
        nbtacherent=0;
        rendement=0;
        moyrend=0;
    }

    public void addTache(Tache tache) {
        taches.add(tache);
        sortTachesByDateAndPriority();
        Categorie c=Categorie.HOBBY;
        if(tache.getCategorie().compareTo(c)==0)
        {
         DureeHoby.plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()).plusSeconds(tache.getDuree().getSecond());
        }
        else
        {   c=Categorie.WORK;
            if(tache.getCategorie().compareTo(c)==0)
            {
                DureeWork.plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()).plusSeconds(tache.getDuree().getSecond());
            }
            else
            {
                c=Categorie.STUDIES;
                if(tache.getCategorie().compareTo(c)==0)
                {
                    DureeStudy.plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()).plusSeconds(tache.getDuree().getSecond());   
                }
                
            }
        }
    }
    
    private void scheduleEndOfDayTask() {
        endOfDayTimer = new Timer();
        LocalTime endOfDay = LocalTime.of(23, 59, 59);

        // Calculate the delay until the end of the day
        long delay = Duration.between(LocalTime.now(), endOfDay).toMillis();

        // Schedule the task to run at the end of the day
        endOfDayTimer.schedule(new EndOfDayTask(), delay);
    }

    private class EndOfDayTask extends TimerTask {
        @Override
        public void run() {
            // This code will be executed at the end of the day
            System.out.println("End of the day. Performing end-of-day tasks...");

            // Call the function to add the day to joursrnetables
            addjourrentable();
            setrendement();
            // Reschedule the task for the next day
            scheduleEndOfDayTask();
            ajouterBagdes();
        }
    }
   
    public void addPlanning(LocalDate debut, LocalDate fin ,ArrayList<Creneau> creneaux,ArrayList<Projet> p) throws DateException {
        Planning newPlanning = new Planning(debut, fin);
        for(Creneau c: creneaux)
        {
          c.setPlan(newPlanning);
          this.calendar.ajouterCreneau(c);
        }
        for(Projet projet :p)
        {
          projet.setPlan(newPlanning);
          this.projets.add(projet);
        }
        
    }
   
    public Planning getCurrentDayPlanning() {
        LocalDate currentDate = LocalDate.now();
    
        for (Planning plan : planning) {
            if (currentDate.isEqual(plan.getDatedebut()) || currentDate.isEqual(plan.getDatefin()) ||
                    (currentDate.isAfter(plan.getDatedebut()) && currentDate.isBefore(plan.getDatefin()))) {
                System.out.println("Current day is within the planning range!");
                return plan; // Return the current planning object
            }
        }
    
        return null; // Return null if no planning is found for the current day
    }
    
    public void ajouterBagdes()
    {   Planning plan=getCurrentDayPlanning();
        Badge badge;
      if(calendar.getNumberOfCompletedTasksInDay(LocalDate.now())==min)
      {
       System.out.println("you did a great job!");
       encouragement++;
       plan.setEncouragement(plan.getEncouragement()+1);
       plan.setNbtachestermin(plan.getNbtachestermin()+1);
      }
      if(plan.getEncouragement()==5)
      {
        plan.setEncouragement(0);
        plan.setGood(plan.getGood()+1);
      }
     if(encouragement==5)
      {
        badge=Badge.Good;
        Good.add(badge);
        encouragement=0;

      }
      if(plan.getGood()==3)
      {
        plan.setGood(0);
        plan.setVeryGood(plan.getVeryGood()+1);
      }
      if(Good.size()==3)
      {
        badge=Badge.VeryGood;
        VeryGood.add(badge);
        Good.clear();

      }
      if(plan.getVeryGood()==3)
      {
        plan.setVeryGood(0);
        plan.setExcelent(plan.getExcelent()+1);
      }
      if(VeryGood.size()==3)
      {
        badge=Badge.Excelent;
        Excelent.add(badge);
        VeryGood.clear();

      }
    }
   
    public void addjourrentable() {
     LocalDate date=LocalDate.now();
     int a=calendar.getNumberOfCompletedTasksInDay(date);
     if(a>nbtacherent)
     {
        nbtacherent=a;
        joursrnetable=date;
      }

        
    }

    public void removeTache(Tache tache) {
        taches.remove(tache);
    }

    public void setCalendrier(Calendrier cal) {
        this.calendar = cal;
    }

    public void setPseudo(String nom) {
        pseudo = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void addProjet(Projet projet) {
        projets.add(projet);
    }

    public void removeProjet(Projet projet) {
        projets.remove(projet);
    }

    public Calendrier getCalendar() {
        return calendar;
    }

    public void affichertaches() {
        for (Tache t : taches) {
            System.out.println(t.getNom());
        }
    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public void ajouterplan(Planning plan)
    {
       planning.add(plan); 
    }

    public void  setrendement()
    {
        rendement=(calendar.getNumberOfCompletedTasksInDay(LocalDate.now())/calendar.getNumberOFtasksInDay(LocalDate.now()))*100;
    }
    
    public long getrendement()
    {
        return rendement;
    }
    
    public void sortTachesByDateAndPriority() {
        Collections.sort(taches);
    }

    public void setTache(Tache tache)
    {
        this.taches.remove(tache);
        this.taches.add(tache);

    }
 
    public void sortTaches() {
        Collections.sort(taches, new TacheComparator());
        
    }
   
    public void ajoutetachemanuel(Tache tache,Creneau creneau,boolean bloque)
    {   if(creneau.islibre()){
        int a= creneau.Duree().compareTo(tache.getDuree());
       if(a<0)
       {
        System.out.println("la durée du creneau choisit est inférieure à la durée de votre tache , choisit un autre creneau");
       }
       else{
        creneau.setBloque(bloque);
        creneau.setlibre(false);
        long durationInSeconds = Duration.between(creneau.Duree(), tache.getDuree() ).getSeconds();
        int hours = (int) (durationInSeconds / 3600);
        int minutes = (int) ((durationInSeconds % 3600) / 60);
        int seconds = (int) (durationInSeconds % 60);
        LocalTime time = LocalTime.of(hours,minutes,seconds);
        int result = time.compareTo(creneau.min);
        if(result>0||result==0)
        {
        int h=creneau.getDebut().getHour();
        int min=creneau.getDebut().getMinute();
        int sec=creneau.getDebut().getSecond();
        LocalTime t=creneau.getDebut().toLocalTime().plusSeconds(sec).plusMinutes(min).plusHours(h);
        LocalDateTime d = LocalDateTime.of(creneau.getDebut().getDayOfMonth(),creneau.getDebut().getMonth(),creneau.getDebut().getYear(),t.getHour(),t.getMinute(),t.getSecond());
        Creneau creneau1= new Creneau(d, creneau.getFin());
        this.calendar.ajouterCreneau(creneau1);
        creneau.setDebut(d);
        }
        this.calendar.ajouterCreneau(creneau);
        }
        if(tache.getPeriodicite()>1)
        {
            taches.remove(tache);
            tache.setPeriodicite(tache.getPeriodicite()-1);
            taches.remove(tache);
            sortTaches();
        
        }
        else{ removeTache(tache);}
       }
    }
   
    private class TacheComparator implements Comparator<Tache> {

        @Override
        public int compare(Tache t1, Tache t2) {
            if (t1.getDateLimite() == null && t2.getDateLimite() == null) {
                // Both tasks have null deadline, consider them equal
                return comparePriorities(t1.getPriorite(), t2.getPriorite());
            } else if (t1.getDateLimite() == null) {
                // t1 has null deadline, consider t2 greater
                return 1;
            } else if (t2.getDateLimite() == null) {
                // t2 has null deadline, consider t1 greater
                return -1;
            } else {
                int deadlineComparison = t1.getDateLimite().compareTo(t2.getDateLimite());
                if (deadlineComparison != 0) {
                    // If deadlines are not equal, return the comparison result
                    return deadlineComparison;
                } else {
                    // Deadlines are equal, compare based on priority
                    Priorite priorite1 = t1.getPriorite();
                    Priorite priorite2 = t2.getPriorite();
                    int priorityComparison = priorite2.comparePriority(priorite1);
                    if (priorityComparison != 0) {
                        // If priorities are not equal, return the comparison result
                        return priorityComparison;
                    } else {
                        // Priorities are equal, compare based on task names
                        return t1.getNom().compareTo(t2.getNom());
                    }
                }
            }
        }
    
        private int comparePriorities(Priorite priority1, Priorite priority2) {
            // Compare the priorities based on their natural ordering
            return Integer.compare(priority1.getPriorityOrder(), priority2.getPriorityOrder());
        }
    }

    public void ajoutertachesauto(List<Tache> taches, LocalDateTime debut, LocalDateTime fin) {
       Iterator<Map.Entry<LocalDateTime, Creneau>> creneauIterator = calendar.getCreneaux().entrySet().iterator();
       Iterator<Tache> tacheIterator = taches.iterator();

        while (creneauIterator.hasNext()) {
         Map.Entry<LocalDateTime, Creneau> creneauEntry = creneauIterator.next();
         Creneau creneau = creneauEntry.getValue();
         


        if (creneau.getDebut().isAfter(debut)) {
          break;
        }
        }
        while (creneauIterator.hasNext() && tacheIterator.hasNext()) {
            Map.Entry<LocalDateTime, Creneau> creneauEntry = creneauIterator.next();
            Creneau creneau = creneauEntry.getValue();
            Tache tache = tacheIterator.next();
            if(creneau.getDebut().isAfter(fin))
            {
                break;
            }
            if(creneau.islibre() )
            {
             if (!tache.isDecomposable())
             {
              ajoutetachemanuel(tache, creneau, false);            
             }
             else{
               Creneau a=calendar.getPremierCreneauDisponible(debut,fin,tache.getDuree());
               if(a!=null)
               {
                ajoutetachemanuel(tache,a,false);
               }
               else
               {
                int length=tache.getNom().length();
                String n=tache.getNom().substring(length-1,length);
                String nom=tache.getNom().substring(0, length-1);
                try {
                    int nombre = Integer.parseInt(n);
                    nombre=nombre+1;
                    tache.setNom(nom+nombre);
                } catch (NumberFormatException e) {
                    tache.setNom(tache.getNom()+1);
                }
                
               Tache t = new Tache(tache.getNom(), tache.getDuree(), tache.getPriorite(), tache.getDateLimite(), tache.getCategorie(), true, tache.getPeriodicite(), tache.getEtat());
               addTache(t);
            }

            }
            }

        } 
    }
    
     
} 