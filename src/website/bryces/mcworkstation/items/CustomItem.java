/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.bryces.mcworkstation.items;

/**
 *
 * @author Bryce
 */
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.image.ImageView;

 public final class CustomItem extends RecursiveTreeObject<CustomItem> {

    public ImageView image;
    
    public String material, itemID;
    

    public CustomItem(ImageView img, String mat, String id) {
        this.image = img;
        this.material = mat;
        this.itemID = id;
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }
    
    public String getMaterial(){
        return material;
    }
    
    public String getItemID(){
        return itemID;
    }
}
