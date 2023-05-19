package TP;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;



import java.util.Comparator;
public class Planning {
    public int nbtaches=5;
    private LocalDate datedebut;
    private LocalDate datefin;
    private int nbtachestermin=0;
    private int encouragement=0;
    private int Good=0;
    private long rendjour=0;
    private int VeryGood=0;
    private int Excelent=0;
    private int nbprojets;
    public LocalTime DureeHoby= LocalTime.of(0,0,0);;
    public LocalTime DureeWork= LocalTime.of(0,0,0);;
    public LocalTime DureeStudy= LocalTime.of(0,0,0);; 
    private ArrayList<Tache> tachesnonplan;
    private ArrayList<Tache> tachesplan;
    private ArrayList<Projet> projets;
    private ArrayList<Creneau> creneauxlibres;
    private ArrayList<Creneau> creneauxplan;
    
    public Planning(){
        creneauxlibres = new ArrayList<>();
        creneauxplan = new ArrayList<>();
        tachesnonplan= new ArrayList<>();
        tachesplan = new ArrayList<>();
    }
  
  

    public Planning(LocalDate datedebut, LocalDate datefin) throws DateException {
        LocalDate currentDate = LocalDate.now();
        
        if (datedebut.isBefore(currentDate) || datefin.isBefore(currentDate)) {
            throw new DateException("Provided date is before the current date.");
        }
        creneauxlibres =new ArrayList<Creneau>();
        creneauxplan = new ArrayList<Creneau>();
        tachesnonplan = new ArrayList<>();
        tachesplan = new ArrayList<>();
        projets = new ArrayList<>();
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void orderCreneauxlibreByDebut() {
        Collections.sort(creneauxlibres, new Comparator<Creneau>() {
            @Override
            public int compare(Creneau c1, Creneau c2) {
                return c1.getDebut().compareTo(c2.getDebut());
            }
        });
    }
   
    public void orderCreneauxplanByDebut() {
        Collections.sort(creneauxplan, new Comparator<Creneau>() {
            @Override
            public int compare(Creneau c1, Creneau c2) {
                return c1.getDebut().compareTo(c2.getDebut());
            }
        });
    }
     
    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public boolean isCurrentDayWithin() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(datedebut) && currentDate.isBefore(datefin);
    }

    public int getNbtachestermin() {
        return nbtachestermin;
    }

    public void setNbtachestermin(int nbtachestermin) {
        this.nbtachestermin = nbtachestermin;
    }

    public int getEncouragement() {
        return encouragement;
    }

    public void  annulerplanification(Creneau c)
    { 
    
     supprimerTacheplan(c.getTache());
     addTachenonplan(c.getTache());
     c.setlibre(true);
     c.setTache(null);
     addCreneaulibre(c);
     supprimerCreneauplan(c);

    }

    public void setEncouragement(int encouragement) {
        this.encouragement = encouragement;
    }

    public int getGood() {
        return Good;
    }

    public void setGood(int good) {
        Good = good;
    }

    public int getVeryGood() {
        return VeryGood;
    }

    public void setVeryGood(int veryGood) {
        VeryGood = veryGood;
    }

    public int getExcelent() {
        return Excelent;
    }

    public void setExcelent(int excelent) {
        Excelent = excelent;
    }

    public void incrementGood() {
        Good++;
    }
    
    public void incrementVeryGood() {
        VeryGood++;
    }
    
    public void incrementExcelent() {
        Excelent++;
    }
    
    public void incrementencouragement()
    {
        encouragement++;
    }
    
    public void incrementtachecomplets() {
        nbtachestermin++;
    }
    
    public ArrayList<Tache> getTachesplan() {
        return tachesplan;
    }
  
    public ArrayList<Tache> getTachesnonplan()
   {
    return tachesnonplan;
   }

    public void ajouterCreneaulibre()
    {
    Scanner s= new Scanner(System.in);
    System.out.println("inter the day ");
        int day = s.nextInt();
        int month=s.nextInt();
        int year=s.nextInt();
        LocalDate date = LocalDate.of(year,month,day);
        System.out.println("inter the time");
        int min =s.nextInt();
        int h =s.nextInt();
        Creneau c=new Creneau(LocalDateTime.of(year,month,day,h,min,0),LocalDateTime.of(year,month,day,h,min,0));
        addCreneaulibre(c);
    }
   
    public void addCreneauplan(Creneau creneau)
   { 
      creneauxplan.add(creneau);
      orderCreneauxplanByDebut();
    }
    
    public void ajoutTache(Tache t)// called by the user
    {
        addTachenonplan(t);
        if(t.getCategorie().equals(Categorie.HOBBY))
        {
        DureeHoby.plusHours(t.getDuree().getHour()).plusMinutes(t.getDuree().getMinute());
        }
        else if(t.getCategorie().equals(Categorie.STUDIES))
        {
        DureeStudy.plusHours(t.getDuree().getHour()).plusMinutes(t.getDuree().getMinute());
        }
        else 
        {
            DureeWork.plusHours(t.getDuree().getHour()).plusMinutes(t.getDuree().getMinute());
        
        }
    }
   
    public void ajouterProjet()
   { Scanner s= new Scanner(System.in);
     System.out.println("inter the name of your project ");
        String name=s.next();
        System.out.println("inter a description fro your project");
        String desc =s.next();
        Projet p=new Projet(name,desc);
        System.out.println("inter the number of the tasks you want to add");
        int i = s.nextInt();
        for(int j=0;i>j;j++)
        {
            Tache t =ajouterTache();
        t.setProjet(p);
        }
        addProjet(p);
    }
   
    public  void setRendjour()
    {
    rendjour=getNumberOFtasksCompletedInDay(LocalDate.now())/getNumberOFtasksInDay(LocalDate.now());
    }

  
    public int getNumberOFtasksInDay(LocalDate date)
    { int count=0;
        
        for (Creneau creneau : creneauxplan) {
            LocalDate creneauDate = creneau.getDebut().toLocalDate(); // Assuming getDebut() returns LocalDateTime
            if (creneauDate.equals(date)) {
               
                count++;
            
            }
        }
        
        return count;
    }
    
    public void orderTachesplanByDateLimiteAndPriority() {
        Collections.sort(tachesplan, new Comparator<Tache>() {
            @Override
            public int compare(Tache t1, Tache t2) {
                int dateComparison = t1.getDateLimite().compareTo(t2.getDateLimite());
                if (dateComparison != 0) {
                    return dateComparison;
                }
                return t2.getPriorite().comparePriority(t1.getPriorite());
            }
        });
    }
    
    public void orderTachesNonplanByDateLimiteAndPriority() {
        Collections.sort(tachesnonplan, new Comparator<Tache>() {
            @Override
            public int compare(Tache t1, Tache t2) {
                int dateComparison = t1.getDateLimite().compareTo(t2.getDateLimite());
                if (dateComparison != 0) {
                    return dateComparison;
                }
                return t2.getPriorite().comparePriority(t1.getPriorite());
            }
        });
    }




    public void supprimerCreneaulibre(Creneau creneau) {
        creneauxlibres.remove(creneau);
        orderCreneauxlibreByDebut();
    }
   
    public void supprimerCreneauplan(Creneau creneau) {
        creneauxplan.remove(creneau);
        orderCreneauxplanByDebut();
    }

    public Creneau getCreneaulibres(LocalDateTime debut) {
        for (Creneau creneau : creneauxlibres) {
            if (creneau.getDebut().equals(debut)) {
                return creneau;
            }
        }
        return null;
    }
   
    public Creneau getCreneauplan(LocalDateTime debut) {
        for (Creneau creneau : creneauxplan) {
            if (creneau.getDebut().equals(debut)) {
                return creneau;
            }
        }
        return null;
    }
  
    public Tache ajouterTache()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("inter the name ");
        String name = s.next();
        System.out.println("inter the period");
        int min =s.nextInt();
        int h =s.nextInt();
        System.out.println("inter the deadline");
        int day = s.nextInt();
        int month = s.nextInt();
        int year = s.nextInt();
        System.out.println("inter the priority");
        String p = s.next();
        System.out.println("inter the category");
        String categ = s.next();
        Priorite priorite = Priorite.valueOf(p);
        Categorie categorie=Categorie.valueOf(categ);
        System.out.println("is it decomposable");
        Boolean decomp = s.nextBoolean();
        System.out.println("inter the periodicity");
        int per =s.nextInt();
        Tache t= new Tache(name,LocalTime.of(h,min),priorite, LocalDate.of(year,month,day),categorie, decomp, per);
        ajoutTache(t);
        return t;
    }
    
    public void addBadges()
    {
       
    if(getNumberOFtasksCompletedInDay(LocalDate.now())>=nbtaches)
    {
     encouragement++;
     
    }
    if((encouragement%5)==0)
    {
        Good++;  
          
    }
    if((Good%3)==0)
    {
        VeryGood++;
      
    }
    if((VeryGood%3)==0)
    {
    Excelent++;
    
    }
    }
   
    public int getNumberOFtasksCompletedInDay(LocalDate date)
    { int count=0;
        
        for (Creneau creneau : creneauxplan) {
            LocalDate creneauDate = creneau.getDebut().toLocalDate(); // Assuming getDebut() returns LocalDateTime
            if (creneauDate.equals(date)) {
               if(creneau.getTache().getEtat().compareTo(Etat.Completed)==0)
               {
                count++;
               }
            }
        }
        
        return count;
    }
    

    public void addProjet(Projet p)
    {
        projets.add(p);
    }
    
    public void addTacheplan(Tache t)
    {
        tachesplan.add(t);
        orderTachesplanByDateLimiteAndPriority();
    }
 
    public void addTachenonplan(Tache tache) {
        tachesnonplan.add(tache);
        orderTachesNonplanByDateLimiteAndPriority();
        /* 
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
        */
    }
    
    public void supprimerTacheplan(Tache t)
    {
        tachesplan.remove(t);
        orderTachesplanByDateLimiteAndPriority();
    }
   
    public void supprimerTacheNonplan(Tache t)
    {
        tachesnonplan.remove(t);
        orderTachesNonplanByDateLimiteAndPriority();
    }
 
    public void setCreneauxlibres(ArrayList<Creneau> creneaux) {
        this.creneauxlibres = creneaux;
    }
   
    public void setCreneauxplan(ArrayList<Creneau> creneaux) {
        this.creneauxplan = creneaux;
    }
   
    public Boolean accept()
    {
      return true;
    }
   
    public ArrayList<Creneau> getCreneauxlibres()
    {
        return creneauxlibres;
    }
     
    public ArrayList<Creneau> getCreneauxplan()
    {
        return creneauxplan;
    }

    public void addCreneaulibre(Creneau creneau)
    { 
        creneauxlibres.add(creneau);
        orderCreneauxlibreByDebut();
    }

    public void affichertaches() {
        for (Tache t : tachesnonplan) {
            System.out.println(t.getNom());
        }
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
                addBadges();
                setRendjour();
                SetNbProjettermin();
                // Call the function recursively to set up for the next day
                endOfDayTimer();
            
            }
        }, remainingSeconds * 1000); // Convert remainingSeconds to milliseconds
        
        // Optional: You can also store the timer object in an instance variable if you need to cancel it later
        // this.endOfDayTimer = timer;
    }  
     
    public Creneau getPremierCreneauDisponible(LocalTime dureeMin) {
        for (Creneau creneau : creneauxlibres) {
            if (creneau.Duree().compareTo(dureeMin) >= 0) {
                return creneau;
            }
        }
        return null;
    }

    public void plantache(Tache tache,Creneau creneau,boolean bloque)
    { 
    if(creneau.islibre())
    { LocalDate date = LocalDate.of(creneau.getDebut().getYear(),creneau.getDebut().getMonth(),creneau.getDebut().getDayOfMonth());
       if(date.isBefore(tache.getDateLimite()))
       {
        if(tache.isDecomposable()){
           if(creneau.Duree().compareTo(tache.getDuree())<0)
           {
            String name;
            int length=tache.getNom().length();
            String n=tache.getNom().substring(length-1,length);
            String nom=tache.getNom().substring(0, length-1);
            try {
                int nombre = Integer.parseInt(n);
                nombre=nombre+1;
                String numberAsString = String.valueOf(nombre);
               name=nom+numberAsString;
            } catch (NumberFormatException e) {
              name=tache.getNom()+ String.valueOf(1);
            }
           Tache t = new Tache(name, tache.getDuree().minusHours(creneau.Duree().getHour()).minusMinutes(creneau.Duree().getMinute()),tache.getPriorite(), tache.getDateLimite(), tache.getCategorie(), true, tache.getPeriodicite());
           addTachenonplan(t);
        }   
        if(tache.getDuree().compareTo(creneau.Duree().minusMinutes(creneau.getmin().getMinute()))<0)
        {
            Creneau cr= new Creneau(creneau.getDebut().plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()),creneau.getFin());        
            addCreneaulibre(cr);   
            creneau.setFin(creneau.getDebut().plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()));         
        }
         tachesnonplan.remove(tache);
         addTacheplan(tache);
         creneau.setTache(tache);
         creneau.setBloque(bloque);
         creneau.setlibre(false);
         addCreneauplan(creneau); 
         creneauxlibres.remove(creneau);
        }
        else
        {
        if(creneau.Duree().compareTo(tache.getDuree())<0)
        {
            System.out.println("choose another one");
        }
        else{
        if(tache.getDuree().compareTo(creneau.Duree().minusMinutes(creneau.getmin().getMinute()))<0)
        {
            Creneau cr= new Creneau(creneau.getDebut().plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()),creneau.getFin());        
            addCreneaulibre(cr);   
            creneau.setFin(creneau.getDebut().plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()));         
        }
        if(tache.getPeriodicite()==1)
        {
         addTacheplan(tache);
         tachesnonplan.remove(tache);
        }
        else
        {
            tache.setPeriodicite(tache.getPeriodicite()-1);
        }
        creneau.setTache(tache);
        creneau.setBloque(bloque);
        creneau.setlibre(false);
        addCreneauplan(creneau); 
        creneauxlibres.remove(creneau);


    }
    
    }

}
else
{
System.out.println("the deadline is before");
creneau.setTache(null);
}



}
}

    

    public boolean miseajourdemande()
    {  
        return true ;
    }

    
/* 
   public void plantachesafter(Arraylist<Tache> taches,boolean tachescompleted,boolean creneauxcompleted)
   {
   for(Tache t: taches)
   {
    plantacheauto(t, tachescompleted, creneauxcompleted);
   }
   }
*/
    public int plantacheauto(Tache t,boolean tachescompleted,boolean creneauxcompleted) {
        for (Creneau c : creneauxlibres) {
            plantache(t, c, false);
            if (c.getTache() != null) {
                return 0;
            }
        }
        
        if (miseajourdemande()) {
            for (Creneau cr : creneauxplan) {
              if(cr.getDebut().isAfter(LocalDateTime.now())){                
                annulerplanification( cr);
              }
            }
            plantachesauto(tachescompleted,creneauxcompleted);
        }
        
        return 0;
    }
    
    public void plantachesauto(boolean tachecompleted , boolean creneauxcompleted)
    {
     int creneauxIndex = 0;
     int tachesIndex = 0;
     ArrayList<Creneau> creneaux = new ArrayList<>();
        while (creneauxIndex < creneauxlibres.size() && tachesIndex < tachesnonplan.size()) {
            Creneau creneau = creneauxlibres.get(creneauxIndex);
            Tache tache = tachesnonplan.get(tachesIndex);
           
            if(tache.getDecomposable())
            {
               Creneau c=getPremierCreneauDisponible(tache.getDuree());
               if(c!=null)
               {
                plantache(tache, c,false); 
                creneaux.add(c);
            }
              else
              {
               plantache(tache, creneau,false); 
               creneauxIndex++;
               creneaux.add(creneau);
               
              }
              tachesIndex++;
              
            }
            else
            { 
            plantache(tache, creneau, isCurrentDayWithin());
            if(creneau.getTache()!=null)
            {
             creneaux.add(creneau);
             
             creneauxIndex++;
             tachesIndex++;

            }
            else
            {
             Creneau a=getPremierCreneauDisponible(tache.getDuree());
             plantache(tache, a,false);
             creneaux.add(a);
        
             tachesIndex++;
            }
        }
        }
        
        if(tachesIndex<creneauxlibres.size())
        {
        tachecompleted=false;
        }
        if(creneauxIndex<creneauxlibres.size())
        {
        creneauxcompleted=false;
        }
        if(!accept())
        {
        for(Creneau c :creneaux)
        {
           annulerplanification(c);
        }    
        }    


    }


    public long getRendJour()
    {
        return rendjour;
    }

    public void SetNbProjettermin()
    {
        int count=0;
     for(Projet p:projets)
     {
        if(p.getCompleted())
        {
            count++;
        }
     }
     nbprojets=count;
    }    



}

