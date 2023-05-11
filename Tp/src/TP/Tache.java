package TP;

import java.io.Serializable;

import java.time.LocalTime;

import java.util.Date ;


public class Tache implements Serializable ,Comparable <Tache>{
   
    private String nom;
    private LocalTime duree;
    private Priorite priorite;
    private Date dateLimite;
    private Categorie categorie;
    private boolean decomposable;
    private int periodicite;
    private Etat etat ;
    private Planning plan;
    private Projet projet ;
    public Tache(){} 
    public Tache(String nom,LocalTime duree, Priorite priorite, Date dateLimite, Categorie categorie, boolean decomposable, int periodicite, Etat etat ) {
        this.nom = nom;
        this.duree =duree;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.categorie = categorie;
        this.decomposable = decomposable;
        this.periodicite = periodicite;
        this.etat=etat;
    }

    // Getters et Setters

    public Tache(String tacheName, String tacheDurationStr, int tachePriorityValue, Date tacheDeadline,
            Categorie tacheCategory, boolean tacheDecomposable, int tachePeriodicity, Etat tacheEtat) {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public void setDuree(int h,int min ,int sec) {
         this.duree = LocalTime.of(h, min, sec);
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public boolean isDecomposable() {
        return decomposable;
    }

    public void setDecomposable(boolean decomposable) {
        this.decomposable = decomposable;
    }

    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }
    
    public void setEtat(Etat etat) {
        setEtat(etat);
        Etat e=Etat.Completed;
        if(this.etat.compareTo(e)==0)
        { projet.incrementnbtachecomplets();
            if(projet.nbtachescomplets()==projet.nbtaches())
            {
               projet.setCompleted(); 
            }
            /* 
          plan.incrementtachecomplets();
          plan.incrementencouragement();
          if(plan.getEncouragement()==5)
          {
            plan.setEncouragement(0);
            plan.incrementGood();
          }
          if(plan.getGood()==3)
          { plan.setGood(0);
            plan.incrementVeryGood();
          }
          if(plan.getVeryGood()==3)
          {
            plan.setVeryGood(0);
            plan.incrementExcelent();
          }
          */
        } 
    }
   
    public boolean getDecomposable()
    {return this.decomposable;}
   
    public Etat getEtat() {
        return etat;
    }
    @Override
    public int compareTo(Tache other) {
        // Compare by date first
        int dateComparison = this.dateLimite.compareTo(other.dateLimite);
        if (dateComparison != 0) {
            return dateComparison;
        }
        
        // If the dates are the same, compare by priority
        return this.priorite.compareTo(other.priorite);
    }
    
    public void afficherTache() {
    System.out.println("Nom de la tâche: " + nom);
    System.out.println("Durée: " + duree + " heures");
    System.out.println("Priorité: " + priorite);
    System.out.println("Date limite: " + dateLimite);
    System.out.println("Catégorie: " + categorie);
    System.out.println("Décomposable: " + decomposable);
    System.out.println("Périodicité: " + periodicite);
    }
   
    public Planning getplan()
    {
        return plan;
    }
    
    public void setPlan(Planning plan)
    {
        this.plan=plan;
    }
    
    public void setProjet(Projet projet)
    {
      this.projet=projet;
      this.projet.incrementnbtaches();
    }
   
    public Projet getProjet()
    {
        return projet;
    }
    
}