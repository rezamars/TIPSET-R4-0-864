/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Left;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Reza
 */
public class User1X2Listener {
    
    private Image image1;
    private String image1Path = "1-färgad.jpg";
    private Image imageX;
    private String imageXPath = "x-färgad.jpg";
    private Image image2;
    private String image2Path = "2-färgad.jpg";
    private Image imageBlank;
    private String imageBlankPath = "blank.jpg";
    
    private String strArrayTypeOfImage = "";
    private Label[] userRowArray;
    
    private int userRowLabelIndex = -1;
    private String[] strArray = new String[39];
    
    private boolean[] user1X2FlagArray = new boolean[39];
    private ImageView[] userRowimageViewArray;
    
    private int numberOfMGs;
    private int[] chosenMGIndexes;
    
    private Left left;
    private MGListener MGlistener;
    private CombinedSystemChecker combinedSystemChecker;
    private ResultRowListener resultRowListener;
    
    
    public User1X2Listener(Label[] userRowArray1, Left left1, MGListener MGListener1, CombinedSystemChecker combinedSystemChecker1, 
            ResultRowListener resultRowListener1){
        
        this.left = left1;
        this.userRowArray = userRowArray1;
        this.userRowimageViewArray = left1.getUserRowImageViewArray();
        
        this.chosenMGIndexes = left1.getChosenMGIndexes();
        this.MGlistener = MGListener1;
        this.numberOfMGs = MGListener1.getNumberOfMGs();
        this.combinedSystemChecker = combinedSystemChecker1;
        this.resultRowListener = resultRowListener1;
        
        
        for (int a = 0 ; a < user1X2FlagArray.length ; a++){
            user1X2FlagArray[a] = false;
        }
        
        loadLabelImages();
        
        for ( int s = 0 ; s < strArray.length ; s++){
            strArray[s] = "";
        }
        
    }
    
    
    //loads the images for the result-input
    public void loadLabelImages(){
        
        try {
            
            image1 = new Image(image1Path);
            imageX = new Image(imageXPath);
            image2 = new Image(image2Path);
            imageBlank = new Image(imageBlankPath);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda bild!");
            //System.exit(0);
            return;
        }
        
    }
    
    //adds listener for the result input of the user
    //then, calls the flagsetter method, 
    //and updates the images for clicked, depending on 1X2
    public void addUser1X2LabelListener(){
	        
        for(int x = 0 ; x<userRowArray.length ; x++){
                
	        userRowArray[x].setOnMouseClicked(event -> {
                    
                    for(int loopIndex = 0 ; loopIndex <userRowArray.length ; loopIndex+=3){
                        
                        if(event.getSource() == userRowArray[loopIndex]){
                            userRowLabelIndex = loopIndex;
                            if (strArray[userRowLabelIndex].equalsIgnoreCase("")){
                                strArrayTypeOfImage = "1";
                            }
                            else{
                                strArrayTypeOfImage = "";
                            }
                            strArray[userRowLabelIndex] = strArrayTypeOfImage;
                            //strArray[userRowLabelIndex + 1] = "";
                            //strArray[userRowLabelIndex + 2] = "";
                        }
                        else if(event.getSource() == userRowArray[loopIndex+1]){
                            userRowLabelIndex = loopIndex + 1 ;
                            if (strArray[userRowLabelIndex].equalsIgnoreCase("")){
                                strArrayTypeOfImage = "X";
                            }
                            else{
                                strArrayTypeOfImage = "";
                            }
                            strArray[userRowLabelIndex] = strArrayTypeOfImage;
                            //strArray[userRowLabelIndex - 1] = "";
                            //strArray[userRowLabelIndex + 1] = "";
                        }
                        else if(event.getSource() == userRowArray[loopIndex+2]){
                            userRowLabelIndex = loopIndex + 2 ;
                            if (strArray[userRowLabelIndex].equalsIgnoreCase("")){
                                strArrayTypeOfImage = "2";
                            }
                            else{
                                strArrayTypeOfImage = "";
                            }
                            strArray[userRowLabelIndex] = strArrayTypeOfImage;
                            //strArray[userRowLabelIndex - 1] = "";
                            //strArray[userRowLabelIndex - 2] = "";
                        }
                    }
                    
                   
                    user1X2LabelFlagSetter();
                    updateLabelImage();
                    this.combinedSystemChecker.checkIfUserSystemIsComplete();
                    
                });
        }
    }      
    
    //sets the flags for the result-part, specifying which of the results the user has chosen and not chosen
    public void user1X2LabelFlagSetter(){
        
        if (user1X2FlagArray[userRowLabelIndex] == false){
            user1X2FlagArray[userRowLabelIndex] = true;
                //numberOfResChecked++;
            }
        else if(user1X2FlagArray[userRowLabelIndex] == true){
            user1X2FlagArray[userRowLabelIndex] = false;
            //numberOfResChecked--;
        }
        
    }
    
    
    //changing the result-grafics depending on chosen and unchosen 1,X,2
      public void updateLabelImage(){
        
        if (user1X2FlagArray[userRowLabelIndex] == false){
              
            /*
            if(strArrayTypeOfImage == "1"){
                disableIndexes[0] = userRowLabelIndex + 1;
                disableIndexes[1] = userRowLabelIndex + 2;
            }
            else if(strArrayTypeOfImage == "X"){
                disableIndexes[0] = userRowLabelIndex - 1;
                disableIndexes[1] = userRowLabelIndex + 1;
            }
            else if(strArrayTypeOfImage == "2"){
                disableIndexes[0] = userRowLabelIndex - 1;
                disableIndexes[1] = userRowLabelIndex - 2;
            }
            else{
                
            }
            */
            
            userRowimageViewArray[userRowLabelIndex].setImage(imageBlank);
            userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);    
            
            //userRowArray[disableIndexes[0]].setDisable(false);
            //userRowArray[disableIndexes[1]].setDisable(false);
            
            //checkedResults--;
            //this.result13Flag  = false;
            
        }
        else if (user1X2FlagArray[userRowLabelIndex] == true){
            
            if(strArrayTypeOfImage == "1"){
                userRowimageViewArray[userRowLabelIndex].setImage(image1);
                userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);
                //disableIndexes[0] = userRowLabelIndex + 1;
                //disableIndexes[1] = userRowLabelIndex + 2;
                
            }
            else if(strArrayTypeOfImage == "X"){
                userRowimageViewArray[userRowLabelIndex].setImage(imageX);
                userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);
                //disableIndexes[0] = userRowLabelIndex - 1;
                //disableIndexes[1] = userRowLabelIndex + 1;
            }
            else if(strArrayTypeOfImage == "2"){
                userRowimageViewArray[userRowLabelIndex].setImage(image2);
                userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);
                //disableIndexes[0] = userRowLabelIndex - 1;
                //disableIndexes[1] = userRowLabelIndex - 2;
            }
            else{
                
            }
            
            //userRowArray[disableIndexes[0]].setDisable(true);
            //userRowArray[disableIndexes[1]].setDisable(true);
            //checkedResults++;   
            
            /*
            //when the user checks 13 results the 13Flag sets to true 
            //whihch makes it possible to click the count-button
            if(checkedResults == 13){
                
                result13Flag = true;
                
                countRowNumber();
            }
            else{
                
            }
            */
        }
            
        this.MGlistener.updateEnableCountCButton(this.resultRowListener.get13Flag());
    }
      
      
    public boolean[] getUser1X2FlagArray(){
        return this.user1X2FlagArray;
    }
    
    public String[] getStrArray(){
        return this.strArray;
    }
    
    
}
