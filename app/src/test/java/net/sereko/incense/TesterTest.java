package net.sereko.incense;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by steve on 2/9/17.
 */
public class TesterTest {


    @Test
    public void testApex() throws Exception {
        Integer[] values = {1, 4, 5,
                            6, 8, 15,
                            16, 17, 18,
                            19, 20, 21,
                            22, 23, 24,
                            25, 26, 30,
                            34, 87, 2,
                            3, 4};

        Integer index = Tester.findApex(values);
        System.out.println("index: " + index);
        assertEquals(index, Integer.valueOf(5));
    }

//    public char[] getNextCode(char[] code){
//        char x = 'x';
//        x++ = 'y';
//        x++ = 'kie'
//        if(code has last character that is equal to 'Z' or 9){
//            if z -> a
//            if 9 -> 0
//            cut the last one off
//            getNextCode(code with one cut off)
//        } else {
//            return code.last++;
//        }



//    @Test
//    public void gekkkktNext() {
//       Tester.getthenum(0);
//    }
}