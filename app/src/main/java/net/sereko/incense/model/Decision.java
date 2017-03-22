package net.sereko.incense.model;

import java.util.ArrayList;

/**
 * Created by steve on 3/20/17.
 */

public class Decision {
    private String name;
    private ArrayList<Element> elements;
    // Specider

    enum Type {
        PRO, CON
    }

    public Decision(){

    }

    class Element {
        private String name;
        private Type type;
        private Integer value;

        public Element(String name){

        }
    }
}
