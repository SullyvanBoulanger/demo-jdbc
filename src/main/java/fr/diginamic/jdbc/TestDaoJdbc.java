package fr.diginamic.jdbc;

import java.util.List;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {
    public static void main(String[] args) {
        FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();

        List<Fournisseur> fournisseurs = fournisseurDaoJdbc.extraire();
        fournisseurs.forEach(System.out::println);

        System.out.println("-----");

        fournisseurDaoJdbc.update(fournisseurs.getFirst().getName(), "Neko !");
        fournisseurDaoJdbc.insert(new Fournisseur(5, "Metal !"));
        Fournisseur fournisseur = new Fournisseur(6, "Chiens");
        fournisseurDaoJdbc.insert(fournisseur);
        fournisseurDaoJdbc.delete(fournisseur);

        fournisseurDaoJdbc.insert(new Fournisseur(7, "L’Espace Création"));

        fournisseurDaoJdbc.extraire().forEach(System.out::println);
    }
}
