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

//handles the users system-input, contains listener to the user1X2-system labels
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
        
        
        initiate2Arrays();
        
        loadLabelImages();
        
        
    }
    
    //initiates and resets the 2 arrays that contains user1X2-flags and string-array of the system
    //that the user has clicked and chosen
    public void initiate2Arrays(){
        
        for (int a = 0 ; a < user1X2FlagArray.length ; a++){
            user1X2FlagArray[a] = false;
        }
        
        for ( int s = 0 ; s < strArray.length ; s++){
            strArray[s] = "";
        }
        
    }
    
    
    //loads the images for the users system1X2-input
    public void loadLabelImages(){
        
        try {
            
            image1 = new Image(image1Path);
            imageX = new Image(imageXPath);
            image2 = new Image(image2Path);
            imageBlank = new Image(imageBlankPath);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda bild!");
            return;
        }
        
    }
    
    //adds listener for the users system-input of the 1X2,
    //calls the flagsetter method, 
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
                        }
                    }
                    
                   
                    user1X2LabelFlagSetter();
                    updateLabelImage();
                    this.combinedSystemChecker.checkIfUserSystemIsComplete();
                    
                });
        }
    }      
    
    //sets the flags for the users 1X2system-input
    public void user1X2LabelFlagSetter(){
        
        if (user1X2FlagArray[userRowLabelIndex] == false){
            user1X2FlagArray[userRowLabelIndex] = true;
                //System.out.println("False, now true!");
            }
        else if(user1X2FlagArray[userRowLabelIndex] == true){
            user1X2FlagArray[userRowLabelIndex] = false;
        }
        
    }
    
    
    //changing the label-image of the users 1X2system-input
      public void updateLabelImage(){
        
        if (user1X2FlagArray[userRowLabelIndex] == false){
             
            userRowimageViewArray[userRowLabelIndex].setImage(imageBlank);
            userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);    
            
        }
        else if (user1X2FlagArray[userRowLabelIndex] == true){
            
            if(strArrayTypeOfImage == "1"){
                userRowimageViewArray[userRowLabelIndex].setImage(image1);
                userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);
                
            }
            else if(strArrayTypeOfImage == "X"){
                userRowimageViewArray[userRowLabelIndex].setImage(imageX);
                userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);
                
            }
            else if(strArrayTypeOfImage == "2"){
                userRowimageViewArray[userRowLabelIndex].setImage(image2);
                userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);
                
            }
            
            else if(strArrayTypeOfImage == "blank"){
                userRowimageViewArray[userRowLabelIndex].setImage(imageBlank);
                userRowArray[userRowLabelIndex].setGraphic(userRowimageViewArray[userRowLabelIndex]);
            }
            else{
                
            }
            
        }
            
        this.MGlistener.updateEnableCountCButton(this.resultRowListener.get13Flag());
    }
      
      
    //resets the 1X2-flagArray, resets the string-array that contains users system,
    //resets the users 1X2-system and changes the grapics of the 1X2-labels to blank
    public void reset1X2s(){
        
        initiate2Arrays();
        
         for(int p = 0 ; p < user1X2FlagArray.length ; p++){
            
            if (user1X2FlagArray[p] == false){
              
            userRowimageViewArray[p].setImage(imageBlank);
            userRowArray[p].setGraphic(userRowimageViewArray[p]);    
            
            }
        }
        
    }  
      
    public boolean[] getUser1X2FlagArray(){
        return this.user1X2FlagArray;
    }
    
    public String[] getStrArray(){
        return this.strArray;
    }
    
    
}
