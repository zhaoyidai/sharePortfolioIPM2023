/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.exec;

import tp04.metier.Action;
import tp04.metier.ActionComposee;
import tp04.metier.ActionSimple;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Run {

    private static final Logger LOG = Logger.getLogger(Run.class.getName());

    public static void main(String[] args) {
        ActionSimple bnp;
        ActionSimple axa;
        ActionComposee bqAss;
        Jour j1;
        Jour j2;

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
        // affichage des cours - comme 1 action simple et 1 action
        LOG.log(Level.INFO, "Action simple *bnp* à j1 : {0}", bnp.valeur(j1));
        LOG.log(Level.INFO, "Action *Banque-Assurance* à j2 : {0}",  bqAss.valeur(j2));

        Portefeuille p;
        p = new Portefeuille();
        p.acheter(axa, 10);
        LOG.log(Level.INFO, "Portefeuille : {0}", p);
        p.acheter(bnp, 20);
        LOG.log(Level.INFO, "Portefeuille : {0}",  p);
        p.acheter(bqAss, 5);
        LOG.log(Level.INFO, "Portefeuille : {0}", p);
        p.acheter(bqAss, 15);
        LOG.log(Level.INFO, "Portefeuille : {0}", p);
        LOG.log(Level.INFO, "Portefeuille à j1 : {0}", p.valeur(j1));
        p.vendre(axa, 5);
        LOG.log(Level.INFO, "Portefeuille : {0}", p);
        p.vendre(axa, 5);
        LOG.log(Level.INFO, "Portefeuille : {0}", p);
        p.vendre(axa, 5);
        LOG.log(Level.INFO, "Portefeuille : {0}", p);
        p.vendre(bnp, 50);
        LOG.log(Level.INFO, "Portefeuille : {0}", p);

        
        LOG.log(Level.INFO, "Liste acheter : ");
        for(Action a:p.getListeAchete()){
            LOG.log(Level.INFO, "message {0}", a.getLibelle());
        }

 
        LOG.log(Level.INFO, "message : {0}", p.toStringDetail());

    }

}
