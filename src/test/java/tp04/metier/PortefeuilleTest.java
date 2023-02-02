/*
 * Copyright 2023 zdai2.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zdai2
 */
public class PortefeuilleTest {
    private static final int EXPECTED_NB=2;
    private static final List EXPECTED_listAchat=new ArrayList<String>(){
        {
            add("AXA");
            add("BNP");
            add("Banque-Assurance");
            add("Banque-Assurance");
        }
    };
    private static final List EXPECTED_listVente=new ArrayList<String>(){
        {
            add("AXA");
            add("AXA");
            add("BNP");
        }
    };
    public PortefeuilleTest() {
    }

    
    public Portefeuille init() {
        ActionSimple bnp, axa;
        ActionComposee bqAss;
        Jour j1, j2;

        // init des objets metiers Jour
        j1 = new Jour(2014, 1);
        j2 = new Jour(2014, 2);

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");
        // enrg de la composition de l'action composée
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);
        // enrg. de 2 cours pour chaque action 
        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);


        Portefeuille p;
        p = new Portefeuille();
        p.acheter(axa, 10);
        p.acheter(bnp, 20);
        p.acheter(bqAss, 5);
        p.acheter(bqAss, 15);
        
        p.vendre(axa, 5);
        p.vendre(axa, 5);
        p.vendre(axa, 5);
        p.vendre(bnp, 50);
        
        return p;
    }
    
    @Test
    void testNbAction(){
        Portefeuille p = this.init();
        final int result=p.getnbActions();
        Assertions.assertSame(EXPECTED_NB, result, "Should be the same");
    }
    @Test
    void testListAchat(){
        Portefeuille p = this.init();
        ArrayList<String> listA = new ArrayList<String>();
        for(Action a:p.getListeAchete()){
            listA.add(a.getLibelle());
        }
        final List result =listA;
        Assertions.assertEquals(EXPECTED_listAchat, result, "Should be the same");
    }
    
    @Test
    void testListVente(){
        Portefeuille p = this.init();
        ArrayList<String> listA = new ArrayList<String>();
        for(Action a:p.getListeVente()){
            listA.add(a.getLibelle());
        }
        final List result =listA;
        Assertions.assertEquals(EXPECTED_listVente, result, "Should be the same");
    }
    
    @Test
    void testStringToString() {
        ActionSimple bnp, axa;
        ActionComposee bqAss;

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");

        Portefeuille p;
        p = new Portefeuille();
        
        p.acheter(axa, 10);
        p.acheter(bnp, 20);
        p.acheter(bqAss, 5);
            
        String result = "*----------- Liste des actions avec la quantité associé -----------* \n";
        result += "*----------------- Action : ";
        result += axa.getLibelle() + "\n";
        result += "*-- Quantité : ";
        result += "10\n";
        result += "*----------------- Action : ";
        result += bnp.getLibelle() + "\n";
        result += "*-- Quantité : ";
        result += "20\n";
        result += "*----------------- Action : ";
        result += bqAss.getLibelle() + "\n";
        result += "*-- Quantité : ";
        result += "5\n";
        
        Assertions.assertEquals(p.toString(), result, "Should be the toString");
    }
    
    @Test
    void testStringToStringDetail() {
        ActionSimple bnp, axa;
        ActionComposee bqAss;

        // creation d'actions simples et composée
        bnp = new ActionSimple("BNP");
        axa = new ActionSimple("AXA");
        bqAss = new ActionComposee("Banque-Assurance");
        
        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);

        Portefeuille p;
        p = new Portefeuille();
        
        p.acheter(axa, 10);
        p.acheter(bnp, 20);
        p.acheter(bqAss, 5);
            
        String result = "*----------- Liste des actions avec la quantité associé -----------* \n";
        result += "*----------------- Action : ";
        result += axa.getLibelle() + "\n";
        result += "*-- Quantité : ";
        result += "10\n";
        result += "*----------------- Action : ";
        result += bnp.getLibelle() + "\n";
        result += "*-- Quantité : ";
        result += "20\n";
        result += "*----------------- Action : ";
        result += bqAss.getLibelle() + "\n";
        result += "*-- Quantité : ";
        result += "5\n";
        result += "*-- Détail \n";
        result += "*----------------- Action : ";
        result += axa.getLibelle() + "\n";
        result += "*------------ Pourcentage : ";
        result += "0.3\n";
        result += "*----------------- Action : ";
        result += bnp.getLibelle() + "\n";
        result += "*------------ Pourcentage : ";
        result += "0.7\n";
        
        Assertions.assertEquals(p.toStringDetail(), result, "Should be the toString");
    }
    
}
