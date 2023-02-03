/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 *
 * @author perussel
 */
public class ActionComposee extends Action {
    // attribut lien
    LinkedHashMap<ActionSimple, Float> actions;

    public ActionComposee(String libelle) {
        super(libelle);
        this.actions = new LinkedHashMap();
    }
    
    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.actions.put(as, pourcentage);
    }
    
    public String toString() {
        String res = "*-- Détail \n";
        for (Action action: this.actions.keySet()){
            res += "*----------------- Action : ";
            res += action.getLibelle() + "\n";
            res += "*------------ Pourcentage : ";
            res += this.actions.get(action) + "\n";
        }
        return res;
    }

    @Override
    public float valeur(Jour j) {
        float valeur;
        
        valeur = 0;
        for(ActionSimple as : this.actions.keySet()) {
            valeur = valeur + (as.valeur(j) * this.actions.get(as));
        }
        
        return valeur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ActionComposee other = (ActionComposee) obj;
        return Objects.equals(this.actions, other.actions);
    }

    public LinkedHashMap<ActionSimple, Float> getActions() {
        return actions;
    }
    
}
