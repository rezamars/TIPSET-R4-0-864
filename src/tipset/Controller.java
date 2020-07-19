/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipset;

import Grafik.Center;
import Grafik.Left;
import Grafik.Right;
import Grafik.Top;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ClearButtonListener;
import model.CombinedSystemChecker;
import model.CountButtonListener;
import model.MGListener;
import model.ResultCounter;
import model.ResultRowListener;
import model.User1X2Listener;

/**
 *
 * @author Reza
 */
//this class is controller of the program, it contains instance variables and the main-method
public class Controller extends Application {
    
    
    private Top top = new Top();
    private Left left = new Left();
    private Center center = new Center();
    private Right right = new Right();
    private Label[] MGArray ;
    private MGListener MGlistener;
    private Label[] result1X2Array;
    private Label[] userRowArray;
    private ResultRowListener resultRowListener;
    private CountButtonListener countButtonListener;
    
    private User1X2Listener user1X2Listener;
    private CombinedSystemChecker combinedSystemChecker;
    
    private ResultCounter resultCounter;
    private boolean flag13 = false;
    
    private ClearButtonListener clearButtonListener;
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        //get screenresolution
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        //set screen-size, procentage of screenresolution
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth((primaryScreenBounds.getWidth())/1.3);
        primaryStage.setHeight((primaryScreenBounds.getHeight())/1.05);
        
        //referring to arrays
        this.MGArray = left.getMGArray();
        this.userRowArray = left.getUserRowArray();
        this.result1X2Array = center.getResultArray();
        
        combinedSystemChecker = new CombinedSystemChecker();
        
        
        //creating new instances of various objects
        MGlistener = new MGListener(MGArray, left, center, right, flag13, combinedSystemChecker);
        MGlistener.addMGLabelListener();
        
        resultRowListener = new ResultRowListener(result1X2Array, center, MGlistener, flag13, left);
        resultRowListener.addResultLabelListener();
        
        resultCounter = new ResultCounter(left,center,right);
        
        countButtonListener = new CountButtonListener(right, resultCounter);
        countButtonListener.addCountButtonListener();
        
        clearButtonListener = new ClearButtonListener(right, MGlistener, flag13, resultRowListener, left);
        clearButtonListener.addClearButtonListener();
        
        user1X2Listener = new User1X2Listener(userRowArray, left, MGlistener, combinedSystemChecker, resultRowListener);
        user1X2Listener.addUser1X2LabelListener();
        
        combinedSystemChecker.get2Listeners(MGlistener, user1X2Listener, left, flag13, resultRowListener);
        
        resultCounter.setUser1X2Listener(user1X2Listener);
        
        View v = new View(primaryStage, top, left, center, right);
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
