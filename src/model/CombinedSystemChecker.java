/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Left;

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
    private int[] chosenMGIndexes;
    
    
    public CombinedSystemChecker(){
        
        
        
        
    }
    
    
    public void get2Listeners(MGListener MGlistener1, User1X2Listener user1X2Listener1, Left left1){
        
        this.MGlistener = MGlistener1;
        this.user1X2Listener = user1X2Listener1;
        this.numberOfMGs = MGlistener.getNumberOfMGs();
        this.user1X2FlagArray = user1X2Listener.getUser1X2FlagArray();
        this.chosenMGIndexes = left1.getChosenMGIndexes();
        
    }
    
    
    public void checkIfUserSystemIsComplete(){
        
        int numberOfRThreeWays = 0;
        int numberOfMThreeWays = 0;
        int numberOfMTwoWays = 0;
        int numberOfRTwoWays = 0;
        
        numberOfMGs = MGlistener.getNumberOfMGs();
        //numberOfMGs = this.numberOfMGs;
        //this.chosenMGIndexes = left.getChosenMGIndexes();
        
        System.out.println("in check...MGs= " + numberOfMGs);
        
        //if (numberOfMGs == 6 ){
            for (int i = 0 ; i < 6 ; i++){
                 //System.out.println(chosenMGIndexes[i]);
            }
        //}
        
        int rowIndex = 0;
        int MGIndexIterator = 0;
        
        for (int loopIndex = 0 ; loopIndex < 39 ; loopIndex+=3){
            
            //System.out.println("In loop 39,  " + chosenMGIndexes[MGIndexIterator] + ", rowIndex: " + rowIndex);
            
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
            
            rowIndex++;
        }
        
        System.out.println("---------------");
        System.out.println("R3: " + numberOfRThreeWays);
        System.out.println("R2: " + numberOfRTwoWays);
        System.out.println("M3: " + numberOfMThreeWays);
        System.out.println("M2: " + numberOfMTwoWays);
        
    }
    
}
