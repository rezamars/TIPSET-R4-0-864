/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Left;
import Grafik.Right;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author Reza
 */
//handles resetting the Mgs and result and the textarea
public class ClearButtonListener {
    
    private Right right;
    private Left left;
    private Button clearButton;
    private MGListener MGlistener;
    private boolean[] mgFlagArray;
    private boolean result13Flag;
    private ResultRowListener resultRowListener;
    private boolean[] resultFlagArray;
    private TextArea numberOfRightsTextArea;
    private boolean flagOfCorrectSystem;
    private boolean[] user1X2FlagArray;
    private String[] strArray;
    private User1X2Listener user1X2Listener;
    
    
    public ClearButtonListener(Right right1, MGListener MGlistener1, boolean flag13, ResultRowListener resultRowListener1, Left left1, 
            User1X2Listener user1X2Listener1){
        
        this.right = right1;
        this.clearButton = right.getClearButton();
        this.MGlistener = MGlistener1;
        this.mgFlagArray = MGlistener1.getMgFlagArray();
        this.result13Flag = flag13;
        this.resultRowListener = resultRowListener1;
        this.resultFlagArray = resultRowListener.getResultFlags();
        this.numberOfRightsTextArea = right.getTextArea();
        this.flagOfCorrectSystem = left1.getFlagOfCorrectSystem();
        this.user1X2FlagArray = user1X2Listener1.getUser1X2FlagArray();
        this.strArray = resultRowListener.getStrArray();
        this.user1X2Listener = user1X2Listener1;
        
    }
    
    //add listener to clear-button
    //at click: resetting the MCGs,flags, the results and the textarea
    //and updating the grafics
    public void addClearButtonListener(){
        
        clearButton.setOnAction(e -> {
            
            resetAll();
            
        });
        
    }
    
    public void resetAll(){
        
        MGlistener.resetMGs();
        MGlistener.updateLabelImage();
        MGlistener.updateEnableCountCButton(result13Flag);
            
        for(int x = 0 ; x < mgFlagArray.length ; x++){
            this.resultFlagArray[x] = false;
        }

        resultRowListener.resetChosenResults();
        numberOfRightsTextArea.setText("Antal rätt:\n13 rätt: \n12 rätt:\n11 rätt:\n10 rätt:");
            
        for (int a = 0 ; a < user1X2FlagArray.length ; a++){
            //user1X2Listener.getUser1X2FlagArray()[a] = false;
        }
        
        for ( int s = 0 ; s < strArray.length ; s++){
            strArray[s] = "";
        }
        
        this.user1X2Listener.reset1X2s();
        
        
    }
    
    
}
