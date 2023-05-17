package TP;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;

import java.io.Serializable;
public class Creneau  implements Serializable{    
    public  LocalTime min ;
    private LocalDateTime debut ;
    private LocalDateTime fin;
    private boolean bloque;
    private Tache tache ;
    private boolean libre;
    private Planning plan;
    public Creneau(){}
    public Creneau(LocalDateTime debut, LocalDateTime fin) {
        this.debut = debut;
        this.fin = fin;
        this.libre=true;
        this.bloque=false;
    }
    
    // Getters et Setters
    
    public Tache getTache()
    {
        return tache ;
    }
    
    public LocalTime Duree()
    {
        long durationInSeconds = Duration.between(debut.toLocalTime() , fin.toLocalTime() ).getSeconds();
        int hours = (int) (durationInSeconds / 3600);
        int minutes = (int) ((durationInSeconds % 3600) / 60);
        int seconds = (int) (durationInSeconds % 60);
        return LocalTime.of(hours,minutes,seconds);

    }
   
    public void setTache(Tache tache)
    { 
     this.tache=tache;
    }
   
    public void setDebut(LocalDateTime debut) {
        this.debut =debut;
    }

    public void setFin(LocalDateTime fin) {
        this.fin =fin;
    }

    public LocalDateTime getFin() {
        return fin;
    }
    
    public LocalDateTime getDebut()
    {
       return debut;
    }
    
    public boolean isBloque() {
        return bloque;
    }
    
    public boolean islibre() {
        return libre;
    }
   
    public void setBloque(boolean bloque) {
        this.bloque = bloque;
    }
    
    public void setlibre(boolean libre) {
        this.libre = libre;
    }
    /* 
    private void plantachesauto(ArrayList<> taches)
    {}
    */
    public LocalTime getmin()
    { 
        return min;
    }
    public Planning getplan()
    {
        return plan;
    }
    
    public void setPlan(Planning plan)
    {
        this.plan=plan;
    }  
}




