/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp04.metier;


import java.util.LinkedHashMap;
import java.util.Map;
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
        this.actions = new LinkedHashMap<>();
    }
    
    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.actions.put(as, pourcentage);
    }
    
    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("*-- Détail \n");
        for (Action action: this.actions.keySet()){
            bld.append("*----------------- Action : ");
            bld.append(action.getLibelle() + "\n");
            bld.append("*------------ Pourcentage : ");
            bld.append(this.actions.get(action) + "\n");
        }
        String res = bld.toString();
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
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.actions);
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

    public Map<ActionSimple, Float> getActions() {
        return actions;
    }
    
}
