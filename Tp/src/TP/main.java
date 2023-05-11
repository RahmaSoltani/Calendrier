package TP;


import java.time.LocalDateTime;
import java.util.Calendar;

class Main{
public static void main(String[] args) {
     
// Create a new instance of Calendar
Calendar calendar = Calendar.getInstance();

// Set the desired date components
int year = 2023;
int month = Calendar.MARCH; // Note: Calendar.MONTH starts from 0 for January
int day = 9;
LocalDateTime date =LocalDateTime.now().minusHours(10);
// Set the calendar with the desired date components
calendar.set(year, month, day);

// Get the Date object from the calendar


      
    Application app =new Application();
    
    Utilisateur user =new Utilisateur();
    //app.ajouterUtilisateur(user);
   // app.sauvegarderUtilisateurs();
    user=app.authentifier("chaima");
    Calendrier cal = user.getCalendar();
    /*al.ajouterCreneau(c);
    user.setCalendrier(cal);
    app.setUtilisateur(user);
    System.out.println(cal.hasCreneaux());
    app.sauvegarderUtilisateurs();
    /*Tache tache= new Tache();
    tache.setNom("b");
    tache.setPriorite(Priorite.HIGH);
    tache.setDateLimite(date);
    user.addTache(tache);
    app.setUtilisateur(user);
   // app.setUtilisateur(user);
   app.sauvegarderUtilisateurs();
/*   Application a= new Application();
    Utilisateur u =a.authentifier("chaima");
    u.affichertaches();
    Tache tache= new Tache();
    tache.setNom("sport");
   /*  u.addTache(tache);
   a.setUtilisateur(u);
    a.setUtilisateur(u);
    a.sauvegarderUtilisateurs();*/

}
}
