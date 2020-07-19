/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Grafik.Center;
import Grafik.Left;
import Grafik.Right;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Reza
 */
//handles the listener to the selected MGs that the user chooses
public class MGListener    {

    private Label[] MGArray ;
    private  int index = 0;
    private Left left;
    private Center center;
    private Right right;
    private ImageView[] MGimageViewArray;
    private int MGIndex = -1;
    private boolean[] mgFlagArray = new boolean[13];
    
    private Image MGimage1;
    private String MGimage1Path = "MG-ej-klickad.jpg";
    private Image MGimage2;
    private String MGimage2Path = "MG-klickad.jpg";
    private ImageView imageView1;
    private ImageView imageView2;
    
    private int numberOfMGs = 0;
    private int[] chosenMGIndexes;
    
    private boolean flagOf6MGs;
    private boolean result13Flag;
    
    private User1X2Listener user1X2Listener;
    private CombinedSystemChecker combinedSystemChecker;
    private boolean flagOfCorrectSystem;
    
    
    
    public MGListener(Label[] MGA, Left left1, Center center1, Right right1, boolean flag13, CombinedSystemChecker combinedSystemChecker1){
        
        for (int a = 0 ; a < mgFlagArray.length ; a++){
            mgFlagArray[a] = false;
        }
        
        this.MGArray = MGA;
        this.left = left1;
        this.center = center1;
        this.right = right1;
        this.imageView1 = left1.getImageView1();
        this.imageView2 = left1.getImageView2();
        this.MGimageViewArray = left1.getMGImageViewArray();
        this.flagOf6MGs = left1.get6MGsFlag();
        this.chosenMGIndexes = left1.getChosenMGIndexes();
        this.result13Flag = flag13;
        this.combinedSystemChecker = combinedSystemChecker1;
        this.flagOfCorrectSystem = left.getFlagOfCorrectSystem();
        
        loadLabelImage();
        
    }
    
    
    //listener for clicking on MGs
    public void addMGLabelListener(){
	        
        for(int x = 0 ; x<MGArray.length ; x++){
                
	        MGArray[x].setOnMouseClicked(event -> {
                    
                    
                    for (int index = 0 ; index < MGArray.length ; index++){
                        if(event.getSource()== MGArray[index]){
                        MGIndex = index;
                        }
                        else{
                            //System.exit(0);
                        }
                   }
                    
                    MGLabelFlagSetter();
                    updateLabelImage();
                    this.combinedSystemChecker.checkIfUserSystemIsComplete();
                    updateEnableCountCButton(this.result13Flag);
                    
                    //System.out.println("In MGListener, NumberOfMGs: " + numberOfMGs);
                });
        }
    }       
    
    //loads images
    public void loadLabelImage(){
        
        try {
            
            MGimage1 = new Image(MGimage1Path);
            MGimage2 = new Image(MGimage2Path);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda MG-bild!");
            //System.exit(0);
            return;
        }
        
    }

    //setting the flags for chosen and unchosen MGs
    public void MGLabelFlagSetter(){
        
        if (mgFlagArray[MGIndex] == false){
            mgFlagArray[MGIndex] = true;
            }
        else if(mgFlagArray[MGIndex] == true){
            mgFlagArray[MGIndex] = false;
        }
        
    }
    
    //changing label images, counting number of MGs chosen
    public void updateLabelImage(){
        
        if(MGIndex != -1){
            
            if (mgFlagArray[MGIndex] == false){
                
               
            if (numberOfMGs == 6){
                for (int y = 0; y < MGArray.length ; y++){
                    if (mgFlagArray[y] != true){
                        MGArray[y].setDisable(false);
                    }
                    else {
                        MGArray[y].setDisable(false);
                    }
                }
            }
            
                
            MGimageViewArray[MGIndex].setImage(MGimage1);
            MGArray[MGIndex].setGraphic(MGimageViewArray[MGIndex]);
                
            
            if (numberOfMGs > 0){
                numberOfMGs--;
            }
                
            
            }
            else if (mgFlagArray[MGIndex] == true){

                MGimageViewArray[MGIndex].setImage(MGimage2);
                MGArray[MGIndex].setGraphic(MGimageViewArray[MGIndex]);
                numberOfMGs++;
            }
            
        }
        
        
            
            
        if (numberOfMGs > 5){
            System.out.println("6+++++ " + numberOfMGs );
            for (int y = 0; y < MGArray.length ; y++){
                if (mgFlagArray[y] != true){
                    MGArray[y].setDisable(true);
                }
                else {
                    MGArray[y].setDisable(false);
                }
            }
        }
        
         
        int iter = 0;
        for (int y = 0; y < mgFlagArray.length ; y++){
                if (mgFlagArray[y] == true){
                    chosenMGIndexes[iter] = y;
                    iter++;
                }
            }
        
        if (numberOfMGs == 6){
            
            flagOf6MGs = true;
            
        }
        else{
            flagOf6MGs = false;
        }
        //System.out.println("MGs = " + numberOfMGs);
        
    }

    //enable and disable the count-button depending on numbers of MGs and result-input
    public void updateEnableCountCButton(boolean flag13){
        
        this.result13Flag = flag13;
        
        /*
        System.out.println("FlagOfCorrectSystem: " + this.left.getFlagOfCorrectSystem());
        System.out.println("FlagOf6MGs: " + flagOf6MGs);
        System.out.println("result13Flag: " + result13Flag);
        System.out.println("---------------");
        */
        
        if ((flagOf6MGs == true) && (this.result13Flag == true) && (this.left.getFlagOfCorrectSystem() == true)){
            right.getCountButton().setDisable(false);
        }
        else{
            right.getCountButton().setDisable(true);
        }
        
    }
    
    public boolean[] getMgFlagArray(){
        return this.mgFlagArray;
    }
    
    
 
    //resets the MGs and changes the grapics of the MGs
    public void resetMGs(){
        
        for(int p = 0 ; p < mgFlagArray.length ; p++){
            mgFlagArray[p] = false;
        }
        
        for(int w = 0 ; w < MGArray.length ; w++){
            MGimageViewArray[w].setImage(MGimage1);
            MGArray[w].setGraphic(MGimageViewArray[w]);
            MGArray[w].setDisable(false);
        }
        
        for(int q = 0 ; q < chosenMGIndexes.length ; q++){
            chosenMGIndexes[q] = -1;
        }
        
        numberOfMGs = 0;
    }
    
    
    public int getNumberOfMGs(){
        return this.numberOfMGs;
    }
    
    
}
