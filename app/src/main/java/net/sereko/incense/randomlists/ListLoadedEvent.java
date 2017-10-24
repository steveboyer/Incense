package net.sereko.incense.randomlists;

import java.util.List;

/**
 * Created by steve on 10/23/17.
 */

public class ListLoadedEvent {
    int position;
    String prose;

    public ListLoadedEvent(int position, String prose){
        this.position = position;
        this.prose = prose;
    }

    int getPosition(){
        return position;
    }

    String getProse(){
        return prose;
    }
}
