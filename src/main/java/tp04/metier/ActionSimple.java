/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author perussel
 */
public class ActionSimple extends Action {

    // attribut lien
    private Map<Jour, Cours> mapCours;
    private ActionComposee actionComp;
    // constructeur
    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap<>();
    }
    
    // enrg possible si pas de cours pour ce jour
    public void enrgCours(Jour j, float v) {
        this.mapCours.computeIfAbsent(j, k -> new Cours(k, 0f)).setValeur(v);
    }
    
    public Map<Jour, Cours> getMap(){
        return this.mapCours;
    }
    

    public ActionComposee getActionComp() {
        return actionComp;
    }

    public void setActionComp(ActionComposee actionComp) {
        this.actionComp = actionComp;
    }
    
    @Override
    public float valeur(Jour j) {
        if(this.mapCours.containsKey(j))
            return this.mapCours.get(j).getValeur();
        else 
            return 0;
    }

  
    // encapsulation de la définition de la classe Cours
    public static class Cours {
        
        private Jour jour;

        private float valeur;

        public float getValeur() {
            return valeur;
        }
        
        public Jour getJour() {
            return jour;
        }

        public void setJour(Jour jour) {
            this.jour = jour;
        }

        public void setValeur(float valeur) {
            this.valeur = valeur;
        }

        public Cours(Jour jour, float valeur) {
            this.jour = jour;
            this.valeur = valeur;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + Objects.hashCode(this.jour);
            hash = 37 * hash + Float.floatToIntBits(this.valeur);
            return hash;
        }

        

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Cours other = (Cours) obj;
            if (Float.floatToIntBits(this.valeur) != Float.floatToIntBits(other.valeur)) {
                return false;
            }
            return Objects.equals(this.jour, other.jour);
        }
        
        

    }
}
