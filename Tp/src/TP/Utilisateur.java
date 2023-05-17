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
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
 
public class Utilisateur implements Serializable {
    private String pseudo;
    private ArrayList<Planning> calendar;
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
    public void planifier()
    {  
        Scanner s = new Scanner(System.in);
        System.out.println("inter the day of start");
        int day = s.nextInt();
        int month = s.nextInt();
        int year = s.nextInt();
        System.out.println("inter the day of end");

        LocalDate debut = LocalDate.of(year,month,day);
         day = s.nextInt();
         month = s.nextInt();
         year = s.nextInt();

        LocalDate fin = LocalDate.of(year,month,day);
          
        Planning plan ;

        try{
         plan = new Planning(debut,fin);
       System.out.println("set the number of the time frames that you want to add");
       int i= s.nextInt();
       for(int j=0 ;i>j;j++)
       {
        plan.ajouterCreneaulibre();
       }
       System.out.println("set the number of the tasks that you want to add");
        i= s.nextInt();
       for(int j=0 ;i>j;j++)
       {
       plan.ajouterTache();
       }
       System.out.println("set the number of the projects that you want to add");
       i= s.nextInt();
      for(int j=0 ;i>j;j++)
      {
      plan.ajouterProjet();
      }
      plan.affichertaches();
    }
    catch(DateException e)
    {
     System.out.println("you can't choose a date before the current date, please try again");
    }
    }
    

    public Utilisateur() {
    }

    public Utilisateur(String nom) {
        pseudo = nom;
        taches = new ArrayList<>();
        calendar = new ArrayList<>();
        projets = new ArrayList<>();
        Good=new ArrayList<>();
        VeryGood=new ArrayList<>();
        Excelent =new ArrayList<>();
        nbtacherent=0;
        rendement=0;
        moyrend=0;
    }
/*     
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

  

 

    public void setPseudo(String nom) {
        pseudo = nom;
    }

    public String getPseudo() {
        return pseudo;
    }


  
*/

    public ArrayList<Planning> getPlanning() {
        return calendar;
    }

    public String getPseudo()
    {
      return pseudo;
    }
    public void setPseudo( String name)
    {
      this.pseudo=name;
    }
/* 
    public void  setrendement()
    {
        rendement=(calendar.getNumberOfCompletedTasksInDay(LocalDate.now())/calendar.getNumberOFtasksInDay(LocalDate.now()))*100;
    }
    
    public long getrendement()
    {
        return rendement;
    }
    
    

    public void setTache(Tache tache)
    {
        this.taches.remove(tache);
        this.taches.add(tache);

    }

    public void sortTaches() {
        Collections.sort(taches, new TacheComparator());
        
    }
   
    s

    public void ajoutertachesauto(List<Tache> taches, LocalDateTime debut, LocalDateTime fin,ArrayList<Creneau> c) {
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
              c.add(creneau);           
             }
             else{
               Creneau a=calendar.getPremierCreneauDisponible(debut,fin,tache.getDuree());
               if(a!=null)
               {
                ajoutetachemanuel(tache,a,false);
                c.add(a);
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
               creneau.setTache(tache);
               c.add(creneau);
            }

            Boolean g=accept();
            { 
              if(g)
              {
                for(Creneau r:c)
                {
                  calendar.ajouterCreneau(r);
                }
            } 
            }
            }
            }

        } 
    }
    
 */  
} 
