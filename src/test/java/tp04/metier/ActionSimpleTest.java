/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zdai2
 */
public class ActionSimpleTest {
    
    private static final String EXPECTED_LIB="Toto";
    private static final String ExPECTED_AC_LIB="Tata";
    
    public ActionSimpleTest() {
    }

    @Test
    public void testgetLibShouldPass() {
        final ActionSimple as1=new ActionSimple(EXPECTED_LIB);
        final String result=as1.getLibelle();
        
        Assertions.assertSame(EXPECTED_LIB, result, "Should be the same");
    }
    
    @Test
    public void testSetActionComposee(){
        final ActionSimple as1=new ActionSimple("Toto");
        final ActionComposee acc=new ActionComposee("Tata");
        as1.setActionComp(acc);
        final String result=as1.getActionComp().getLibelle();
        
        Assertions.assertSame(ExPECTED_AC_LIB, result, "Should be the same");
    }
    
}
