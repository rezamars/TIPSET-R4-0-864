/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Center;
import Grafik.Left;
import Grafik.Right;
import javafx.scene.control.TextArea;

/**
 *
 * @author Reza
 */
//handles counting the number of rights, depending on MGs chosen, result
public class ResultCounter {
    
    private int[] chosenMGIndexes;
    private String[] result13Array;
    private String[][] R4_0_9_Tables;
    private boolean flagOf6MGs;
    
    private String[] strArray;
    
    private int[] the8unMGmarkedIndexes = new int[8];
    
    private int[] indexesOfRThrees = new int[4];
    private int indexOfMThree = -1;
    private int[] indexesOfMTwos = new int[5];
    private int[] indexesOfOnes = new int[3];
    
    private int amountOf13 = 0;
    private int amountOf12 = 0;
    private int amountOf11 = 0;
    private int amountOf10 = 0;
    
    /*
    int numberOfRThreeWays = 0;
    int numberOfRTwoWays = 0;
    int numberOfMThreeWays = 0;
    int numberOfMTwoWays = 0;
    */
    
    private String[] fourResultsMarks = new String[4];
    private TextArea numberOfRightsTextArea;
    
    
    public ResultCounter(Left left1, Center center1, Right right1){
        
        this.chosenMGIndexes = left1.getChosenMGIndexes();
        this.result13Array = center1.getResult13Array();
        this.R4_0_9_Tables = right1.getSystemTables();
        this.flagOf6MGs = left1.get6MGsFlag();
        this.numberOfRightsTextArea = right1.getTextArea();
        
        initiateIntArrays();
        
    }
    
    public void setUser1X2Listener(User1X2Listener user1X2Listener1){
        this.strArray = user1X2Listener1.getStrArray();
    }
    
    public void initiateIntArrays(){
        
        for(int q = 0 ; q < indexesOfRThrees.length ; q++){
            indexesOfRThrees[q] = -1;
        }
        for(int w = 0 ; w < indexesOfMTwos.length ; w++){
            indexesOfMTwos[w] = -1;
        }
        for(int e = 0 ; e < indexesOfOnes.length ; e++){
            indexesOfOnes[e] = -1;
        }
    }
    
    public void startResultCounter(){
        
        specifyAndSaveIndexesOf3_2_1();
        //specify8unMGIndexes();
        
    }
    
    public void specifyAndSaveIndexesOf3_2_1(){
        
        for(int x = 0 ; x < 39 ; x+=3){
            //System.out.println(strArray[x]+ ", " + strArray[x+1] + ", " + strArray[x+2] + ",");
        }
        
        for(int i = 0 ; i < 6 ; i++){
            //System.out.println(chosenMGIndexes[i]);
        }
        
        int MGIndexIterator = 0;
        int R3iterator = 0;
        int M2iterator = 0;
        int Onesiterator = 0;
        
        int rowIndex = 0;
        
        //for(int i = 0 ; i < 13 ; i++){
            
            for(int x = 0 ; x < 39 ; x+=3){
                
                if (MGIndexIterator < 6){
                    
                    if((strArray[x].equalsIgnoreCase("1")) && (strArray[x+1].equalsIgnoreCase("X")) && 
                        (strArray[x+2].equalsIgnoreCase("2")) && (chosenMGIndexes[MGIndexIterator] == rowIndex )){
                        indexOfMThree = rowIndex;
                        MGIndexIterator++;
                    }
                    else if(((strArray[x].equalsIgnoreCase("1")) && (strArray[x+1].equalsIgnoreCase("X")) && 
                        (strArray[x+2].equalsIgnoreCase("")) && (chosenMGIndexes[MGIndexIterator] == rowIndex )) ||
                         ((strArray[x].equalsIgnoreCase("")) && (strArray[x+1].equalsIgnoreCase("X")) &&
                          (strArray[x+2].equalsIgnoreCase("2")) && (chosenMGIndexes[MGIndexIterator] == rowIndex )) ||
                        ((strArray[x].equalsIgnoreCase("1")) && (strArray[x+1].equalsIgnoreCase("")) && 
                        (strArray[x+2].equalsIgnoreCase("2")) && (chosenMGIndexes[MGIndexIterator] == rowIndex ) )){
                       
                        indexesOfMTwos[M2iterator] = rowIndex;
                        M2iterator++;
                        MGIndexIterator++;
                    }
                    
                    else if((strArray[x].equalsIgnoreCase("1")) && (strArray[x+1].equalsIgnoreCase("X")) && 
                        (strArray[x+2].equalsIgnoreCase("2")) && (chosenMGIndexes[MGIndexIterator] != rowIndex )){
                        indexesOfRThrees[R3iterator] = rowIndex;
                        R3iterator++;
                    }
                    else if(((strArray[x].equalsIgnoreCase("1")) && (strArray[x+1].equalsIgnoreCase("")) && 
                            (strArray[x+2].equalsIgnoreCase("")) && (chosenMGIndexes[MGIndexIterator] != rowIndex )) ||
                             ((strArray[x].equalsIgnoreCase("")) && (strArray[x+1].equalsIgnoreCase("X")) &&
                              (strArray[x+2].equalsIgnoreCase("")) && (chosenMGIndexes[MGIndexIterator] != rowIndex )) ||
                            ((strArray[x].equalsIgnoreCase("")) && (strArray[x+1].equalsIgnoreCase("")) && 
                            (strArray[x+2].equalsIgnoreCase("2")) && (chosenMGIndexes[MGIndexIterator] != rowIndex ) )){

                        indexesOfOnes[Onesiterator] = rowIndex;
                        Onesiterator++;
                    }
                    else{
                        //indexesOfOnes[Onesiterator] = rowIndex;
                        //Onesiterator++;
                        System.out.println("in else... ");
                    }
                    
                }
                else{
                    
                    if((strArray[x].equalsIgnoreCase("1")) && (strArray[x+1].equalsIgnoreCase("X")) && 
                        (strArray[x+2].equalsIgnoreCase("2")) ){
                        indexesOfRThrees[R3iterator] = rowIndex;
                        R3iterator++;
                    }
                    else if(((strArray[x].equalsIgnoreCase("1")) && (strArray[x+1].equalsIgnoreCase("")) && 
                            (strArray[x+2].equalsIgnoreCase("")) ) ||
                             ((strArray[x].equalsIgnoreCase("")) && (strArray[x+1].equalsIgnoreCase("X")) &&
                              (strArray[x+2].equalsIgnoreCase("")) ) ||
                            ((strArray[x].equalsIgnoreCase("")) && (strArray[x+1].equalsIgnoreCase("")) && 
                            (strArray[x+2].equalsIgnoreCase("2"))  )){

                        indexesOfOnes[Onesiterator] = rowIndex;
                        Onesiterator++;
                    }
                    else{
                        //indexesOfOnes[Onesiterator] = rowIndex;
                        //Onesiterator++;
                        System.out.println("in else... ");
                    }
                
                }
                
                
                rowIndex++;
            }
            
        //}
        
        /*
        System.out.println("indexOfMThree: " + indexOfMThree);
        
        for(int u = 0 ; u < indexesOfRThrees.length ; u++){
            System.out.println("indexesOfRThrees: " + indexesOfRThrees[u]);
        }
        for(int p = 0 ; p < indexesOfMTwos.length ; p++){
            System.out.println("indexesOfMTwos: " + indexesOfMTwos[p]);
        }
        for(int a = 0 ; a < indexesOfOnes.length ; a++){
            System.out.println("indexesOfOnes: " + indexesOfOnes[a]);
        }
        */
        
        countTheRights();
        
    }
    
    /*
    //fills the 8unmarkedMGs-array with indexes that the user has chosen as not MG
    public void specify8unMGIndexes(){
        
        for (int x = 0 ; x < the8unMGmarkedIndexes.length ; x++){
            the8unMGmarkedIndexes[x] = -1;
        }
        
        int indexOf5 = 0;
        int indexOf8 = 0;
        
        
            for(int i = 0 ; i < 13 ; i++){
                
                if(indexOf5 < 5){
                    
                    if(chosenMGIndexes[indexOf5] == i){
                        indexOf5++;
                    }
                    else{
                        the8unMGmarkedIndexes[indexOf8] = i;
                        indexOf8++;
                    }
                }
                else{
                    System.out.println("in else, i= " + i);
                        the8unMGmarkedIndexes[indexOf8] = i;
                        indexOf8++;
                }
                
            }
        
        countTheRights();
    }
    */
    
    //compares the 8 rows of the user with the default tables for R8-0-27 read from file
    private void countTheRights(){
        
        amountOf13 = 0;
        amountOf12 = 0;
        amountOf11 = 0;
        amountOf10 = 0;
        
        
        for(int x = 0 ; x < fourResultsMarks.length ; x++){
            
            fourResultsMarks[x] = result13Array[indexesOfRThrees[x]];
        }
        
        
        int numberOfRight = 0 ;
        
        for(int i = 0 ; i < 9 ; i++){
            
            numberOfRight = 0;
            for(int y = 0 ; y < 4 ; y++){
                if(R4_0_9_Tables[i][y].equalsIgnoreCase(fourResultsMarks[y])){
                    numberOfRight++;
                }
            }
            
            System.out.println("numberOfRights: " + numberOfRight);
            
            /*
            //adds the 5 MGs that are 1X2-marked from the user
            numberOfRight +=5;
            
            //adds number of rights to the 4 levels of won
            if (numberOfRight == 13){
                amountOf13++;
            }
            else if (numberOfRight == 12){
                amountOf12++;
            }
            else if (numberOfRight == 11){
                amountOf11++;
            }
            else if (numberOfRight == 10){
                amountOf10++;
            }
            */
        }
        
         
        
        //setRightsInTextArea();
        
    }
    
    //fills the ammount of rights in textarea
    public void setRightsInTextArea(){
        
        numberOfRightsTextArea.setText("Antal rätt:\n13 rätt: " + amountOf13 + "\n12 rätt: " + amountOf12 + 
                "\n11 rätt: " + amountOf11 + "\n10 rätt: " + amountOf10);
        
    }
    
}
