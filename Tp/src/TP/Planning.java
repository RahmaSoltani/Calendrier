package TP;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.reflect.Array;
import java.time.Duration;
public class Planning {
    private LocalDate datedebut;
    private LocalDate datefin;
    private int nbtachestermin;
    private int encouragement;
    private int Good;
    private int VeryGood;
    private int Excelent;
    private LocalTime DureeHoby;
    private LocalTime DureeWork;
    private LocalTime DureeStudy; 
    private ArrayList<Tache> tachesnonplan;
    private ArrayList<Tache> tachesplan;
    private ArrayList<Projet> projets;
    private Map<LocalDateTime, Creneau> creneauxlibres;
    private Map<LocalDateTime,Creneau> creneauxplan;
    private Creneau cr;
    public Planning(){}
    public Planning(LocalDate datedebut, LocalDate datefin) throws DateException {
        LocalDate currentDate = LocalDate.now();
        
        if (datedebut.isBefore(currentDate) || datefin.isBefore(currentDate)) {
            throw new DateException("Provided date is before the current date.");
        }
        creneauxlibres =new HashMap<LocalDateTime,Creneau>();
        creneauxplan = new HashMap<LocalDateTime, Creneau>();
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
        addCreneau(c);
    }
   public void addCreneauplan(Creneau creneau)
   { 
      creneauxplan.put(creneau.getDebut(), creneau);
    }
    public void addCreneau(Creneau creneau) {
        creneauxlibres.put(creneau.getDebut(), creneau);
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
    
    public void supprimerCreneaulibre(Creneau creneau) {
        creneauxlibres.remove(creneau.getDebut());
    }
    public void supprimerCreneauplan(Creneau creneau) {
        creneauxplan.remove(creneau.getDebut());
    }

    public boolean hasCreneaulibres(LocalDateTime debut) {
        return creneauxlibres.containsKey(debut);
    }
    public boolean  hasCreneauplan(LocalDateTime debut)
    {
        return creneauxplan.containsKey(debut);
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
        addTachenonplan(t);
        return t;
    }
    
    public void addProjet(Projet p)
    {
        projets.add(p);
    }
    
    public void addTachesplan(Tache t)
    {
        tachesplan.add(t);
        sortTachesplan();
    }
 
    public void addTachenonplan(Tache tache) {
        tachesnonplan.add(tache);
        sortTachesnonplan();
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
    
    public void sortTachesnonplan() {
       // Collections.sort(tachesnonplan, new TacheComparator());
        
    }
   
    public void sortTachesplan()
   {
    //Collections.sort(tachesplan, new TacheComparator());
   }
   
   public void setCreneauxlibres(Map<LocalDateTime, Creneau> creneaux) {
        this.creneauxlibres = creneaux;
    }
   
    public void setCreneauxplan(Map<LocalDateTime, Creneau> creneaux) {
        this.creneauxplan = creneaux;
    }
   
    public Creneau getCreneaulibre(LocalDateTime debut) {
        return creneauxlibres.get(debut);
    }
   
    public Creneau getCreneauplan(LocalDateTime debut)
    {
        return creneauxplan.get(debut);
    }

    public Boolean accept()
    {
      return true;
    }
   
    public Map<LocalDateTime,Creneau> getCreneaux()
    {
        return creneauxlibres;
    }
   
    public void addCreneaulibre(Creneau creneau)
    { 
        creneauxlibres.put(creneau.getDebut(),creneau);
    }

    public void affichertaches() {
        for (Tache t : tachesnonplan) {
            System.out.println(t.getNom());
        }
    }
   /* 
    public void ajoutertachesauto() {
        Iterator<Map.Entry<LocalDateTime, Creneau>> creneauIterator = getCreneaux().entrySet().iterator();
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
               plantachemanuel(tache, creneau, false); 
               c.add(creneau);           
              }
              else{
                Creneau a=getPremierCreneauDisponible(debut,fin,tache.getDuree());
                if(a!=null)
                {
                 plantachemanuel(tache,a,false);
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
                 
                Tache t = new Tache(tache.getNom(), tache.getDuree(), tache.getPriorite(), tache.getDateLimite(), tache.getCategorie(), true, tache.getPeriodicite());
                addTachenonplan(t);
                creneau.setTache(tache);
                c.add(creneau);
             }
 
             Boolean g=accept();
             { 
               if(g)
               {
                 for(Creneau r:c)
                 {
                   addCreneau(r);
                 }
             } 
             }
             }
             }
 
         } 
     }
     */
    public Creneau getPremierCreneauDisponible (LocalTime dureeMin) {
        for (Creneau creneau : creneauxlibres.values()) {
            LocalDateTime creneauDebut = creneau.getDebut();
            LocalDateTime creneauFin = creneau.getFin();
                if (creneau.Duree().compareTo(dureeMin) >= 0) {
                    return creneau;
                }
        

        }
        return null;

    }
    
    public void plantachemanuel(Tache tache,Creneau creneau,boolean bloque)
    { 
    if(creneau.islibre())
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
              name=nom+ String.valueOf(1);
            }
           Tache t = new Tache(name, tache.getDuree().minusHours(creneau.Duree().getHour()).minusMinutes(creneau.Duree().getMinute()),tache.getPriorite(), tache.getDateLimite(), tache.getCategorie(), true, tache.getPeriodicite());
           addTachenonplan(t);
        }   
         else if ( creneau.Duree().minusMinutes(creneau.getmin().getMinute()).compareTo(tache.getDuree())>0)
         {
          Creneau c= new Creneau(creneau.getDebut().plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()),creneau.getFin());
          addCreneaulibre(creneau);
         }
        }
        else
        {
        if(tache.getDuree().compareTo(creneau.Duree().minusMinutes(creneau.getmin().getMinute()))<0)
        {
            Creneau cr= new Creneau(creneau.getDebut().plusHours(tache.getDuree().getHour()).plusMinutes(tache.getDuree().getMinute()),creneau.getFin());        
            addCreneaulibre(cr);   
            creneau.setFin(creneau.getFin().minusHours(tache.getDuree().getHour()).minusMinutes(tache.getDuree().getMinute()));         
        }
        if(tache.getPeriodicite()==1)
        {
         addTachesplan(tache);
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
        creneauxlibres.remove(creneau.getDebut(),creneau);
    
    }





}
/*  

         creneau.setTache(tache);
         creneau.setBloque(bloque);
         creneauxplan.put(creneau.getDebut(),creneau);
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
         
        Tache t = new Tache(tache.getNom(), tache.getDuree(), tache.getPriorite(), tache.getDateLimite(), tache.getCategorie(), true, tache.getPeriodicite());
        addTachenonplan(t);
        creneau.setTache(tache);
    
        }
 else{
    if(creneau.islibre()){

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
        addCreneau(creneau1);
        creneau.setDebut(d);
        }
        addCreneau(creneau);
        }
        if(tache.getPeriodicite()>1)
        {
            tachesnonplan.remove(tache);
            tache.setPeriodicite(tache.getPeriodicite()-1);
            tachesplan.remove(tache);
            sortTachesplan();
            sortTachesnonplan();
        
        }
    }
   
}
*/}
/* 
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
*/
}
