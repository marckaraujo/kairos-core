package org.kairos.core;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by Felipe on 20/10/2015.
 */
public class SimpleWindowManager extends WindowManager {

    public SimpleWindowManager(Stage window) {
        super(window);
    }

    @Override
    public void add(Activity activity) {
        super.add(activity);
        activity.onStart();
        if(!tail.equals(head)){
            if (activity.actionBar!=null){
                activity.setHomeAsUp(true);
            }
        }
        tail.content=getDecorView();
        if(tail!=head){
            tail.prev.activity.onPause();
            windowPane.remove(tail.prev.content);
            tail.prev.activity.onStop();
        }
        activity.onResume();
    }

    @Override
    public void setContentView(Node content) {
        super.setContentView(content);
        if(content instanceof Region) {
            show();
        }
    }

    @Override
    public ActivityTransition back() {
        if(tail.activity.fragmentManager.backStack!=null){
            tail.activity.fragmentManager.backStack.backStack();
            tail.activity.fragmentManager.backStack=null;
            return null;
        }
        tail.activity.onPause();
        if(tail.prev!=null){
            windowPane.remove(tail.content);
            windowPane.add(tail.prev.content);
            tail.prev.activity.onStart();
        }
        tail.activity.onStop();
        show();
        return super.back();

    }



    private void show(){
        window.hide();
        ((Stage)window).show();
    }
}
