package main;

import controller.PetsDao;
import entity.Pet;
import java.util.ArrayList;
import view.PrincipalForm;

/**
 *
 * @author arqui
 */
public class Principal {

    public static Pet pet= new Pet();
    public static PetsDao petsDao = new PetsDao();
    public static ArrayList<Pet> pets= new ArrayList<Pet>();

    public static void main(String[] args) {
        PrincipalForm principalForm = new PrincipalForm();
        principalForm.setLocationRelativeTo(null);
        principalForm.setVisible(true);
        
    }
}
