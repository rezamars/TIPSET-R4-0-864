/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Left;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author Reza
 */
public class CombinedSystemChecker {
    
    private int numberOfMGs;
    private boolean[] user1X2FlagArray;
    
    private Left left;
    private MGListener MGlistener;
    private User1X2Listener user1X2Listener;
    private ResultRowListener resultRowListener;
    private int[] chosenMGIndexes;
    
    private Label statusLabel;
    private boolean flagOfCorrectSystem;
    private boolean flag13;
    
    
    public CombinedSystemChecker(){
        
        
        
    }
    
    
    public void get2Listeners(MGListener MGlistener1, User1X2Listener user1X2Listener1, Left left1, Boolean flagOf13, 
            ResultRowListener resultRowListener1){
        
        this.MGlistener = MGlistener1;
        this.user1X2Listener = user1X2Listener1;
        this.numberOfMGs = MGlistener.getNumberOfMGs();
        this.user1X2FlagArray = user1X2Listener.getUser1X2FlagArray();
        this.chosenMGIndexes = left1.getChosenMGIndexes();
        this.statusLabel = left1.getStatusLabel();
        this.flagOfCorrectSystem = left1.getFlagOfCorrectSystem();
        this.flag13 = resultRowListener1.get13Flag();
        this.left = left1;
        this.resultRowListener = resultRowListener1;
        
    }
    
    
    public void checkIfUserSystemIsComplete(){
        
        int numberOfRThreeWays = 0;
        int numberOfRTwoWays = 0;
        int numberOfMThreeWays = 0;
        int numberOfMTwoWays = 0;
        
        
        numberOfMGs = MGlistener.getNumberOfMGs();
        //numberOfMGs = this.numberOfMGs;
        //this.chosenMGIndexes = left.getChosenMGIndexes();
        
        //System.out.println("in check...MGs= " + numberOfMGs);
        
        //if (numberOfMGs == 6 ){
            for (int i = 0 ; i < 6 ; i++){
                 //System.out.println(chosenMGIndexes[i]);
            }
        //}
        
        int rowIndex = 0;
        int MGIndexIterator = 0;
        
        for (int loopIndex = 0 ; loopIndex < 39 ; loopIndex+=3){
            
            //System.out.println("In loop 39, MGIndexIterator: " + MGIndexIterator);
            
            if (MGIndexIterator < 6){
                
                if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+1] == true) && 
                    (user1X2FlagArray[loopIndex+2] == true) && (chosenMGIndexes[MGIndexIterator] == rowIndex)){
                numberOfMThreeWays++;
                MGIndexIterator++;
                }
                else if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+1] == true) &&
                        (chosenMGIndexes[MGIndexIterator] == rowIndex)){
                    numberOfMTwoWays++;
                    MGIndexIterator++;
                }
                else if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+2] == true) &&
                        (chosenMGIndexes[MGIndexIterator] == rowIndex)){
                    numberOfMTwoWays++;
                    MGIndexIterator++;
                }
                else if((user1X2FlagArray[loopIndex+1] == true) && (user1X2FlagArray[loopIndex+2] == true) &&
                        (chosenMGIndexes[MGIndexIterator] == rowIndex)){
                    numberOfMTwoWays++;
                    MGIndexIterator++;
                }
                else if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+1] == true) && 
                    (user1X2FlagArray[loopIndex+2] == true) ){
                    numberOfRThreeWays++;
                }
                else if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+1] == true)){
                    numberOfRTwoWays++;
                }
                else if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+2] == true)){
                    numberOfRTwoWays++;
                }
                else if((user1X2FlagArray[loopIndex+1] == true) && (user1X2FlagArray[loopIndex+2] == true)){
                    numberOfRTwoWays++;
                }
            }
            else{
                if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+1] == true) && 
                    (user1X2FlagArray[loopIndex+2] == true) ){
                    numberOfRThreeWays++;
                }
                else if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+1] == true)){
                    numberOfRTwoWays++;
                }
                else if((user1X2FlagArray[loopIndex] == true) && (user1X2FlagArray[loopIndex+2] == true)){
                    numberOfRTwoWays++;
                }
                else if((user1X2FlagArray[loopIndex+1] == true) && (user1X2FlagArray[loopIndex+2] == true)){
                    numberOfRTwoWays++;
                }
            }
            
            
            
            rowIndex++;
        }
        
        boolean[] flagOf13Marked = new boolean[13];
        
        for(int x = 0 ; x < 13 ; x++){
            flagOf13Marked[x] = false;
        }
                
        int flagOf13MarkedIndex = 0;
        
        for (int loopIndex = 0 ; loopIndex < 39 ; loopIndex+=3){
            
            if(((user1X2FlagArray[loopIndex] == true) || (user1X2FlagArray[loopIndex+1] == true) || 
                    (user1X2FlagArray[loopIndex+2] == true))){
                flagOf13Marked[flagOf13MarkedIndex] = true;
            }
            flagOf13MarkedIndex++;
        }
        
        
        int numberOfGamesMarked = 0;
        
        for(int y = 0 ; y < 13 ; y++){
            if(flagOf13Marked[y] == true){
                numberOfGamesMarked++;
            }
        }
        
        if ((numberOfRThreeWays == 4) && (numberOfRTwoWays == 0) && (numberOfMThreeWays == 1) && (numberOfMTwoWays == 5) &&
                (numberOfGamesMarked == 13)){
            statusLabel.setText("Info: Ditt system är nu korrekt ifylld!");
            statusLabel.setTextFill(Color.GREEN);
            flagOfCorrectSystem = true;
            this.left.setFlagOfCorrectSystem(flagOfCorrectSystem);
        }
        else{
            statusLabel.setText("Info: Ditt system är inte korrekt ifylld!");
            statusLabel.setTextFill(Color.RED);
            flagOfCorrectSystem = false;
            this.left.setFlagOfCorrectSystem(flagOfCorrectSystem);
        }
        
        /*
        if (flag13 == true && flagOfCorrectSystem == true){
            this.MGlistener.updateEnableCountCButton(flag13);
        }
        else{
            
        }
        */
        
        this.MGlistener.updateEnableCountCButton(this.resultRowListener.get13Flag());
        
        /*
        System.out.println("---------------");
        System.out.println("NumberofGamesMarked: " + numberOfGamesMarked);
        System.out.println("R3: " + numberOfRThreeWays);
        System.out.println("R2: " + numberOfRTwoWays);
        System.out.println("M3: " + numberOfMThreeWays);
        System.out.println("M2: " + numberOfMTwoWays);
        */
    }
    
}
