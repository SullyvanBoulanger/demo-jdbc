package fr.diginamic.jdbc.entites;

public class Fournisseur {
    private int id;
    private String name;

    public Fournisseur(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(" - ").append(name);

        return stringBuilder.toString();
    }
}
