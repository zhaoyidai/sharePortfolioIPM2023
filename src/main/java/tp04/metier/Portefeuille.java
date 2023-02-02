/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import java.util.LinkedHashMap;


/**
 *
 * @author perussel
 */
public class Portefeuille {
    

    List<Action> listeAchete=new ArrayList<Action>();
    List<Action> listeVente=new ArrayList<Action>();

    LinkedHashMap<Action, LignePortefeuille> mapLignes;
    

    private class LignePortefeuille {
        
        private Action action;
        
        private int qte;
        
        public int getQte() {
            return qte;
        }
        
        public void setQte(int qte) {
            this.qte = qte;
        }
        
        public Action getAction() {
            return this.action;
        }
        
        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        public String toString() {
            return Integer.toString(qte);
        }
    }
    
    public Portefeuille() {
        this.mapLignes = new LinkedHashMap();
    }
    
    public void acheter(Action a, int q) {
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
            
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
        this.listeAchete.add(a);
    }

    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }
            this.listeVente.add(a);
        }        
    }
    
    public String toString() {
        String res = "*----------- Liste des actions avec la quantité associé -----------* \n";
        for (Action action: this.mapLignes.keySet()){
            res += "*----------------- Action : ";
            res += action.getLibelle() + "\n";
            res += "*-- Quantité : ";
            res += this.mapLignes.get(action).qte + "\n";
        }
        return res;
    }
    
    public String toStringDetail() {
        String res = "*----------- Liste des actions avec la quantité associé -----------* \n";
        for (Action action: this.mapLignes.keySet()){
            res += "*----------------- Action : ";
            res += action.getLibelle() + "\n";
            res += "*-- Quantité : ";
            res += this.mapLignes.get(action).qte + "\n";
            if ( action instanceof ActionComposee){
                res += action.toString();
            } 
        }
        return res;
    }

    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
    
    public int getnbActions(){
        return this.mapLignes.size();
    }

    public List<Action> getListeAchete() {
        return listeAchete;
    }

    public List<Action> getListeVente() {
        return listeVente;
    }
    
}
