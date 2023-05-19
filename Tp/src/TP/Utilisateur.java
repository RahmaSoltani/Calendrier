package TP;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.io.Serializable;
import java.util.Scanner;
 
public class Utilisateur implements Serializable {
  private String pseudo;
  private ArrayList<Planning> calendar;
  private LocalDate joursrnetable;
  private int nbtacherent;
  private int Good ;
  private int VeryGood;
  private int  Excelent;
  private ArrayList<Planning> planning;
  private int encouragement;
  private long moyrend;
  private LocalTime DureeHoby;
  private LocalTime DureeWork;
  private LocalTime DureeStudy;
  private long rendement;
  private int day=0; 
  public void planifier()
    {  
      Scanner s = new Scanner(System.in);
      System.out.println("Enter the start date:");
      int startDay = s.nextInt();
      int startMonth = s.nextInt();
      int startYear = s.nextInt();
      
      LocalDate debut = LocalDate.of(startYear, startMonth, startDay);
      
      System.out.println("Enter the end date:");
      int endDay = s.nextInt();
      int endMonth = s.nextInt();
      int endYear = s.nextInt();
      
      LocalDate fin = LocalDate.of(endYear, endMonth, endDay);
          
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
      calendar.add(plan);
      orderCalendarByStartDate();
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
        calendar = new ArrayList<>();
        Good=0;
        VeryGood=0;
        Excelent =0;
        nbtacherent=0;
        rendement=0;
        moyrend=0;
        day=0;
    }
     
  public long getRendJour()
    {
    return getCurrentDayPlanning().getRendJour();
    }
   
  public void endOfDayTimer() {
      // Get the current time
      LocalTime currentTime = LocalTime.now();
      
      // Calculate the time remaining until the end of the day (assuming end time is 23:59:59)
      LocalTime endTime = LocalTime.of(23, 59, 59);
      long remainingSeconds = currentTime.until(endTime, java.time.temporal.ChronoUnit.SECONDS);
      
      // Create a timer object
      Timer timer = new Timer();
      
      // Schedule the task to be executed at the end of the day
      timer.schedule(new TimerTask() {
          @Override
          public void run() {
              // Perform end-of-day actions here
              getRendJour();
              addjourrentable();
              setDuree();
              setmoyrend();
              endOfDayTimer();
          }
      }, remainingSeconds * 1000); // Convert remainingSeconds to milliseconds
      
  }
    
  public void setmoyrend()
  { day++;
    moyrend=(moyrend*(day-1)+getCurrentDayPlanning().getRendJour())/day;
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
      if(plan.getNumberOFtasksCompletedInDay(LocalDate.now())==plan.nbtaches)
      {
       System.out.println("you did a great job!");
       encouragement++;
      }
     if(encouragement%5==0)
      {
        Good++;

      }
      
      if(Good%3==0)
      {
        Good++;

      }
      if(VeryGood%3==0)
      {
        Excelent++;

      }
    }
   
    

  public void addjourrentable() {
     LocalDate date=LocalDate.now();
     int a=getCurrentDayPlanning().getNumberOFtasksCompletedInDay(LocalDate.now());
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


  


  public ArrayList<Planning> getPlanning() {
        return calendar;
    }

 

  public Planning getPlanningForCurrentDay() {
      LocalDate currentDate = LocalDate.now();
      
      for (Planning planning : calendar) {
          if (planning.getDatedebut().isEqual(currentDate) || planning.getDatedebut().isBefore(currentDate) && planning.getDatefin().isAfter(currentDate)) {
              return planning;
          }
      }
      
      return null; // If no planning is found for the current day
  }
  
  public void orderCalendarByStartDate() {
    Collections.sort(calendar, new Comparator<Planning>() {
        @Override
        public int compare(Planning p1, Planning p2) {
            return p1.getDatedebut().compareTo(p2.getDatedebut());
        }
    });
}

  public void  setrendement()
    {
        rendement=getCurrentDayPlanning().getRendJour();
    }
    
  public long getrendement()
    {
        return rendement;
    }
  
    

  public void setDuree()
  {
    Planning plan=getCurrentDayPlanning();
    DureeHoby=plan.DureeHoby;
    DureeStudy=plan.DureeStudy;
    DureeWork=plan.DureeWork;
    plan.DureeHoby= LocalTime.of(0,0,0);
    plan.DureeStudy=LocalTime.of(0,0,0);
    plan.DureeWork=LocalTime.of(0,0,0);

  }
} 
