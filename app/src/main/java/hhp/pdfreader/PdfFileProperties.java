package hhp.pdfreader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import java.util.Date;

/**
 * Created by hhphat on 7/27/2015.
 */
public class PdfFileProperties {
    private String title;
    private int iconId;
    private Date lastViewed;
    private boolean favourite;

    public PdfFileProperties(String title, int iconId){
        this.title = title;
        this.iconId = iconId;
        favourite = false;
        lastViewed = null;
    }
    public int CompareToAsName(PdfFileProperties pdf){
        return title.compareTo(pdf.getTitle());
    }
    public int CompareToAsDate(PdfFileProperties pdf){
        return lastViewed.compareTo(pdf.getLastViewed());
    }
    public String getTitle(){
        return title;
    }


    public void setFavourite(boolean isFavourite) {
        this.favourite = isFavourite;
    }

    public void setLastViewed(Date lastViewed) {
        this.lastViewed = lastViewed;
    }
    public void loadFile(){

    }
    public void saveFile(){

    }

    public boolean isFavourite() {
        return favourite;
    }

    public Date getLastViewed() {
        return lastViewed;
    }

    public int getIconId() {
        return iconId;
    }
}
