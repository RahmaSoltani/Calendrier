package TP;


import java.io.*;
public class Projet implements Serializable {
    private String nom;
    private String description;
    private Planning plan ;
    private boolean Completed;
    private int nbtaches;
    private  int nbtachescomplets;
    public Projet(String nom, String description) {
        this.nom = nom;
        this.description = description;
        Completed=false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public Planning getplan()
    {
        return plan;
    }
    
    public void setPlan(Planning plan)
    {
        this.plan=plan;
    }
    public void incrementnbtachecomplets()
    { 
        nbtachescomplets++;
    }
    public void incrementnbtaches()
    {
        nbtaches++;
    }
    public int nbtaches()
    {
        return nbtaches;
    }
    public int nbtachescomplets()
    {
        return nbtachescomplets;
    }
    public void setCompleted()
    {
        Completed=true;
    }
    public Boolean getCompleted()
    {
        return Completed;
    }
}



