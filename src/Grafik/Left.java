/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafik;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

/**
 *
 * @author Reza
 */
//the Left-object of the borderpane
public class Left extends VBox{
    
    private Label headingLabel1 = new Label("Mitt system:");
    private Label headingLabel2 = new Label("(4 Hel-RG, 1 Hel-MG, 5 Halv-MG)");
    private Label[] rowNumberlabelArray = new Label[13];
    private Label[] MGArray = new Label[13];
    private ImageView[] MGimageViewArray = new ImageView[13];
    
    private ImageView[] userRowimageViewArray = new ImageView[39];
    private Label[] userRowArray = new Label[39];
    private Image imageBlank;
    private String imageBlankPath = "blank.jpg";
    
    private Image MGimage1;
    private String MGimage1Path = "MG-ej-klickad.jpg";
    private Image MGimage2;
    private String MGimage2Path = "MG-klickad.jpg";
    private ImageView imageView1;
    private ImageView imageView2;
    
    private HBox spaceHbox1 = new HBox();
    private HBox[] hboxLabelArray = new HBox[13];
    private boolean flagOf6MGs = false;
    
    private int[] chosenMGIndexes = new int[6];
    
    private HBox spaceHbox2 = new HBox();
    private Label statusLabel = new Label("Info: Ditt system är inte korrekt ifylld!");
    
    
    public Left(){
        
        this.setPadding(new Insets(10, 10, 10, 25));  
        this.setSpacing(1);
        this.setAlignment(Pos.TOP_CENTER);
        
        Font headingFont ;
        headingFont = Font.font("Arial", FontWeight.BOLD, 20);
        
        headingLabel1.setFont(headingFont);
        headingLabel1.setTextFill(Color.BLUE);
        this.getChildren().add(headingLabel1);
        
        headingLabel2.setFont(headingFont);
        headingLabel2.setTextFill(Color.BLUE);
        this.getChildren().add(headingLabel2);
        
        //a HBox for filling up space
        spaceHbox1 = new HBox();
        spaceHbox1.setPadding(new Insets(10, 10, 10, 100));
        this.getChildren().add(spaceHbox1);
        
        loadLabelImages();
        
        //creating instances of HBox-array and setting properties
        for(int z = 0 ; z < hboxLabelArray.length ; z++){
            hboxLabelArray[z] = new HBox();
            hboxLabelArray[z].setSpacing(10);
            hboxLabelArray[z].setFillHeight(true); 
            hboxLabelArray[z].autosize();
            hboxLabelArray[z].setAlignment(Pos.CENTER);

        }
       
       
        
        Font labelFont ;
        labelFont = Font.font("Arial", FontWeight.BOLD, 20);
        
        //objects of rownumbers, color, text, font
        for(int x = 0 ; x < rowNumberlabelArray.length ; x++){
            rowNumberlabelArray[x] = new Label();
            BackgroundFill background_fill = new BackgroundFill(Color.LIGHTYELLOW,  CornerRadii.EMPTY, Insets.EMPTY); 
            Background background = new Background(background_fill);
            rowNumberlabelArray[x].setBackground(background);
            
            if (x<9){
                rowNumberlabelArray[x].setText("   " + Integer.toString((x+1)) + " ");
            }
            else{
                rowNumberlabelArray[x].setText(" " + Integer.toString((x+1)) + " ");
            }
            
            rowNumberlabelArray[x].setFont(labelFont);
            rowNumberlabelArray[x].setAlignment(Pos.CENTER);
        }
        
        //loading images for result-part
        try {
            
            MGimage1 = new Image(MGimage1Path);
            MGimage2 = new Image(MGimage2Path);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda MG-bild!");
            //System.exit(0);
            return;
        }
        
        
        
        //get screenresolution to set procentage of screen later
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double width = primaryScreenBounds.getWidth();
        double height = primaryScreenBounds.getHeight();
        
        //setting properties of image-views
        imageView1 = new ImageView(MGimage1);
        imageView1.setFitHeight(height/25);
        imageView1.setFitWidth(width/55);
        imageView2 = new ImageView(MGimage2);
        imageView2.setFitHeight(height/25);
        imageView2.setFitWidth(width/55);
        
        //setting properties of image-views and setting the images to the grafics of the MGArray
        for(int w = 0 ; w < MGArray.length ; w++){
            MGimageViewArray[w] = new ImageView(MGimage1);
            MGimageViewArray[w].setFitHeight(height/25);
            MGimageViewArray[w].setFitWidth(width/55);
            
            MGArray[w] = new Label();
            MGArray[w].setGraphic(MGimageViewArray[w]);
            
        }
        
        //settting properties of image-array
        for(int w = 0 ; w < userRowimageViewArray.length ; w++){
            
            userRowimageViewArray[w] = new ImageView();
            
            
            userRowimageViewArray[w] = new ImageView(imageBlank);
            
            userRowimageViewArray[w].setFitHeight(height/25);
            userRowimageViewArray[w].setFitWidth(width/55);
            userRowArray[w] = new Label();
            userRowArray[w].setGraphic(userRowimageViewArray[w]);
            userRowArray[w].setAlignment(Pos.TOP_CENTER);
            
        }
        
        
        int totalLabels = (rowNumberlabelArray.length + MGArray.length + userRowArray.length);
        int squareNumber = 0; 
        int hboxIndex = 0;
        int userRowIndex = 0;
        
        //adding rowNumbers, MG-pictures and 1X2-labels to the screen (stage)
        for (int addIndex = 0 ; addIndex < totalLabels ; addIndex++ ){
            if ( squareNumber == 0){
                hboxLabelArray[hboxIndex].getChildren().add(rowNumberlabelArray[hboxIndex]);
                squareNumber++;
            }
            else if (squareNumber == 1){
                hboxLabelArray[hboxIndex].getChildren().add(MGArray[hboxIndex]);
                squareNumber++;
            }
            else if (squareNumber == 2){
                hboxLabelArray[hboxIndex].getChildren().add(userRowArray[userRowIndex]);
                squareNumber++;
                userRowIndex++;
            }
            else if (squareNumber == 3){
                hboxLabelArray[hboxIndex].getChildren().add(userRowArray[userRowIndex]);
                squareNumber++;
                userRowIndex++;
            }
            
            else if (squareNumber == 4){
                hboxLabelArray[hboxIndex].getChildren().add(userRowArray[userRowIndex]);
                hboxIndex++;
                squareNumber = 0;
                userRowIndex++;
            }
            
        }
        
        //filling the chosenIndex-array of -1 (unacceptable number), to know if and when 
        //the array contains unaccepted number
        for (int x = 0 ; x < chosenMGIndexes.length ; x++){
            chosenMGIndexes[x] = -1;
        }
        
        this.getChildren().addAll(hboxLabelArray);
        
        //a HBox for filling up space
        spaceHbox2 = new HBox();
        spaceHbox2.setPadding(new Insets(10, 10, 10, 100));
        this.getChildren().add(spaceHbox2);
        
        Font statusFont ;
        statusFont = Font.font("Arial", FontWeight.BOLD, 20);
        statusLabel.setFont(statusFont);
        statusLabel.setTextFill(Color.RED);
        statusLabel.setStyle("-fx-border-color: blue;");
        this.getChildren().add(statusLabel);
        
    }
    
    //load image for blank
    public void loadLabelImages(){
        
        try {
            
            imageBlank = new Image(imageBlankPath);
        }
        catch(Exception e) {
            System.out.println("Gick ej att ladda bild!");
            //System.exit(0);
            return;
        }
        
    }
    
    public Label[] getMGArray(){
        return MGArray;
    }
    
    public Label[] getUserRowArray(){
        return userRowArray;
    }
    
    public ImageView[] getMGImageViewArray(){
        return MGimageViewArray;
    }
    
    public ImageView[] getUserRowImageViewArray(){
        return userRowimageViewArray;
    }
    
    public boolean get6MGsFlag(){
        return flagOf6MGs;
    }
    
    public int[] getChosenMGIndexes(){
        return chosenMGIndexes;
    }
    
    public ImageView getImageView1(){
        return imageView1;
    }
    
    public ImageView getImageView2(){
        return imageView2;
    }
    
    
    
}
