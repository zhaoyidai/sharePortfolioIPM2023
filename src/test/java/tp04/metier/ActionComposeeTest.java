/*
 * Copyright 2023 wailbenmansour.
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

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author wailbenmansour
 */
public class ActionComposeeTest {
    
     private static final Map<ActionSimple, Float> EXPECTED_MAP_AC = new LinkedHashMap<>();
    
    public ActionComposeeTest() {
    }

    @Test
    void testEqualsEnregistrerActionComposee() {
        //Attributs à tester
        final Jour j1 = new Jour(2022, 20);
        final float v1 = 15F;
        final float v2 = 35F;

        //Instanciation d'une action simple
        final ActionSimple as1 = new ActionSimple("France 2");
        final ActionSimple as2 = new ActionSimple("France 3");

        //Enregistrer le cours v1 d'une action simple à un jour j1        
        as1.enrgCours(j1, v1);
        as2.enrgCours(j1, v2);
        
        //Instanciation d'une action composée
        final ActionComposee ac1 = new ActionComposee("France télévision");
        
        //Enregistrer le cours v1 d'une action copmposée à une valeur v1
        ac1.enrgComposition(as1, v1);
        ac1.enrgComposition(as2, v2);
        
        //Enregistrer le cours v1 d'une action simple à un jour j1 dans la HashMap à tester
        EXPECTED_MAP_AC.put(as1, v1);
        EXPECTED_MAP_AC.put(as2, v2);
        
        //Tester si la clé, valeur des map sont les mêmes
        Assertions.assertIterableEquals(EXPECTED_MAP_AC.entrySet(), ac1.getActions().entrySet(), "Les clés, valeurs enregistrer sont les mêmes !");
    }
    
}
