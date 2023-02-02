/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zdai2
 */
public class ActionSimpleTest {

    private static final String EXPECTED_LIB = "Toto";
    private static final float EXPECTED_VALUE = 0F;
    private static final Map<Jour, Cours> EXPECTED_MAP = new HashMap<>();
    private static final String ExPECTED_AC_LIB = "Tata";

    public ActionSimpleTest() {
    }

    @Test
    public void testgetLibShouldPass() {
        final ActionSimple as1 = new ActionSimple(EXPECTED_LIB);
        final String result = as1.getLibelle();

        Assertions.assertSame(EXPECTED_LIB, result, "Should be the same");
    }

    @Test
    public void testSetActionComposee() {
        final ActionSimple as1 = new ActionSimple("Toto");
        final ActionComposee acc = new ActionComposee("Tata");
        as1.setActionComp(acc);
        final String result = as1.getActionComp().getLibelle();

        Assertions.assertSame(ExPECTED_AC_LIB, result, "Should be the same");
    }
        @Test
        public void testEqualsJourDonneActionSimple
        
            () {
        //Attributs à tester
        final Jour j1 = new Jour(2022, 20);
            final float v1 = 0F;

            //Instanciation d'une action simple
            final ActionSimple as1 = new ActionSimple("France 2");
            //Enregistrer le cours v1 d'une action simple à un jour j1
            as1.enrgCours(j1, v1);

            //Obtenir le cours à partir d'un jour
            final float valueObtenue = as1.valeur(j1);

            //Tester si le cours d'un jour est le même que le cours attendu
            Assertions.assertEquals(EXPECTED_VALUE, valueObtenue, "Le cour est le même à ce jour!");
        }

        @Test
        public void testNotEqualsJourDonneActionSimple
        
            () {
        //Attributs à tester
        final Jour j1 = new Jour(2022, 20);
            final float v1 = 1F;

            //Instanciation d'une action simple
            final ActionSimple as1 = new ActionSimple("France 2");
            //Enregistrer le cours v1 d'une action simple à un jour j1
            as1.enrgCours(j1, v1);

            //Obtenir le cours à partir d'un jour
            final float valeurObtenue = as1.valeur(j1);

            //Tester si le cours d'un jour n'est pas le même que le cours attendu
            Assertions.assertNotEquals(EXPECTED_VALUE, valeurObtenue, "Le cour n'est pas le même à ce jour!");
        }

        @Test
        public void testEqualsEnregistrerCoursActionSimple
        
            () {
        //Attributs à tester
        final Jour j1 = new Jour(2022, 20);
            final float v1 = 0F;

            //Instanciation d'une action simple
            final ActionSimple as1 = new ActionSimple("France 2");
            //Enregistrer le cours v1 d'une action simple à un jour j1
            as1.enrgCours(j1, v1);

            //Tester si le cours d'un jour est le même que le cours attendu
            Assertions.assertEquals(EXPECTED_MAP, valueObtenue, "Le cour est le même à ce jour!");
        }

    
}
