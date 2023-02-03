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
class ActionSimpleTest {

    private static final String EXPECTED_LIB = "Toto";
    private static final float EXPECTED_VALUE = 0F;
    private static final String ExPECTED_AC_LIB = "Tata";
    private static final Map<Jour, ActionSimple.Cours> EXPECTED_MAP = new HashMap<>();

    public ActionSimpleTest() {
    }

    @Test
    void testSetActionComposee() {
        final ActionSimple as1 = new ActionSimple("Toto");
        final ActionComposee acc = new ActionComposee("Tata");
        as1.setActionComp(acc);
        final String result = as1.getActionComp().getLibelle();

        Assertions.assertSame(ExPECTED_AC_LIB, result, "Should be the same");
    }

    @Test
    void testEqualsJourDonneActionSimple() {
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
    void testNotEqualsJourDonneActionSimple() {
        //Attributs à tester
        final Jour j1 = new Jour(2022, 20);
        final float v1 = 1F;

        //Instanciation d'une action simple
        final ActionSimple as1 = new ActionSimple("France 2");
        //Enregistrer le cours v1 d'une action simple à un jour j1
        as1.enrgCours(j1, v1);

        //Obtenir le cours à partir d'un jour
        final float actual = as1.valeur(j1);

        //Tester si le cours d'un jour n'est pas le même que le cours attendu
        Assertions.assertNotEquals(EXPECTED_VALUE, actual, "Le cour n'est pas le même à ce jour!");
    }
    
    @Test
    void testEqualsEnregistrerCoursActionSimple() {
        //Attributs à tester
        final Jour j1 = new Jour(2022, 20);
        final float v1 = 1F;

        //Instanciation d'une action simple
        final ActionSimple as1 = new ActionSimple("France 2");

        //Enregistrer le cours v1 d'une action simple à un jour j1        
        as1.enrgCours(j1, v1);
        
        //Enregistrer le cours v1 d'une action simple à un jour j1 dans la HashMap à tester
        EXPECTED_MAP.put(j1, new ActionSimple.Cours(j1, v1));
        
        //Tester si la clé, valeur des map sont les mêmes
        Assertions.assertIterableEquals(EXPECTED_MAP.entrySet(), as1.getMap().entrySet(), "Les clés, valeurs enregistrer sont les mêmes !");
    }

    
    
}
