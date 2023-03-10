/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 *
 * @author perussel
 */
public class Portefeuille {
    
    private static final Logger LOG = Logger.getLogger(Portefeuille.class.getName());
    List<Action> listeAchete=new ArrayList<>();
    List<Action> listeVente=new ArrayList<>();

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

        @Override
        public String toString() {
            return Integer.toString(qte);
        }
    }
    
    public Portefeuille() {
        this.mapLignes = new LinkedHashMap<>();
    }
    
    public Map<Action, LignePortefeuille> getActions(){
        return this.mapLignes;
    }
    
    public void acheter(Action a, int q) {
        if (!this.mapLignes.containsKey(a)) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
            
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
        this.listeAchete.add(a);
    }

    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a)) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }
            this.listeVente.add(a);
        }        
    }
/**
 * Action qui augemente*
*/    
    public Set<String> getActionAugemente(Jour j){
        LOG.log(Level.INFO, "Toutes mes actions dont le cours augmente sur le dernier jour");
        Set<String> listAugmente=new HashSet<>();
        int annee=j.getAnnee();
        int ajdj=j.getNoJour();
        if(ajdj!=1){
            int hierj=ajdj-1;
            Jour hier=new Jour(annee,hierj);
            for(Action a:this.mapLignes.keySet()){

                LOG.log(Level.INFO, "{0} hier :{1} ajd :{2}", new Object[]{a.getLibelle(), a.valeur(hier), a.valeur(j)});
                if(a.valeur(j)>a.valeur(hier)){
                    
                    listAugmente.add(a.getLibelle());
                }
              
            } 
        }else{
            //On ne sait pas le nb de jour total de l'annee dernier
            LOG.log(Level.INFO, "D??sol??, Service en maintenance.");
            
        }
        return listAugmente;
        
    }
    
    public Set<String> getActionDepasser(Jour j,float c){
        
        Set<String> listDepasse=new HashSet<>();
        
        for(Action a:this.mapLignes.keySet()){
            if(a.valeur(j)>c){
                listDepasse.add(a.getLibelle());
            }
        }
        return listDepasse; 
    }
    
    
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("*----------- Liste des actions avec la quantit?? associ?? -----------* \n");
        for (Map.Entry<Action,LignePortefeuille> entry: this.mapLignes.entrySet()){
            Action action=entry.getKey();
            bld.append("*----------------- Action : ");
            bld.append(action.getLibelle() + "\n");
            bld.append("*-- Quantit?? : ");
            bld.append(this.mapLignes.get(action).qte + "\n");
        }
        return bld.toString();
    }
    
    public String toStringDetail() {
        StringBuilder bld = new StringBuilder();
        bld.append("*----------- Liste des actions avec la quantit?? associ?? -----------* \n");
        for (Map.Entry<Action,LignePortefeuille> entry: this.mapLignes.entrySet()){
            Action action=entry.getKey();
            bld.append("*----------------- Action : ");
            bld.append(action.getLibelle() + "\n");
            bld.append("*-- Quantit?? : ");
            bld.append(this.mapLignes.get(action).qte + "\n");
            if ( action instanceof ActionComposee){
                bld.append(action.toString());
            } 
        }
        return bld.toString();
    }

    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }

    public List<Integer> getAnalyseNb(Jour j){
        
        int annee=j.getAnnee();
        //(Index 0)Haute,(Index 1)Baisse,(Index 2)Inchange
        List<Integer> listNb=new ArrayList<>();
        for(int i=0;i<3;i++){
            listNb.add(0);
        }
        int ajdj=j.getNoJour();
        if(ajdj!=1){
            int hierj=ajdj-1;
            Jour hier=new Jour(annee,hierj);
            for(Action a:this.mapLignes.keySet()){
                if(a.valeur(hier)<a.valeur(j)){
                    listNb.set(0, listNb.get(0)+1);
                }
                else if(a.valeur(hier)>a.valeur(j)){
                    listNb.set(1, listNb.get(1)+1);
                }
                else{
                    listNb.set(2, listNb.get(2)+1);
                }
            }
            
        }
        return listNb;
    }

    
    public float valeurTotale() {
        float qteTotal = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            qteTotal += lp.getQte();
        }
        return qteTotal;
    }
    
    public float arrondirRendement(float rendement) {
        return (Math.round(rendement * 100f) / 100f);
    }

    
    public float rendement(Action action, Jour jourdeb, Jour jourfin){
        float valeurdeb = action.valeur(jourdeb);
        float valeurfin = action.valeur(jourfin);
        
        float rendement = (valeurfin - valeurdeb) / valeurdeb;
        return arrondirRendement(rendement);
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
    
    public int getQte(Action a){
        return this.mapLignes.get(a).getQte();
    }

    public Map<Action, LignePortefeuille> getMapLignes() {
        return mapLignes;
    }
    
}
