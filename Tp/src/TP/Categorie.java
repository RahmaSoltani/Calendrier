package TP;


enum Categorie {
    STUDIES("RED"),
    WORK("BLUE"),
    HOBBY("GREEN");

    private String color;

    private Categorie(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}