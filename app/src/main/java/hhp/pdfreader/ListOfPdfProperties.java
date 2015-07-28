package hhp.pdfreader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hhphat on 7/27/2015.
 */
public class ListOfPdfProperties extends ArrayList<PdfFileProperties>{

    public ListOfPdfProperties() {
        super();
        PdfFileProperties a = new PdfFileProperties("a",R.drawable.favourite_star);
        this.add(a);
        PdfFileProperties b= new PdfFileProperties("a",R.drawable.favourite_star);
        this.add(b);
        PdfFileProperties c= new PdfFileProperties("a",R.drawable.favourite_star);
        this.add(c);
        PdfFileProperties d= new PdfFileProperties("a",R.drawable.favourite_star);
        this.add(d);
    }

    public enum PdfSortCase{
        SORTBYNAME(0),
        SORTBYDATE(1);
        private int value;
        private PdfSortCase(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
    public void loadFile(){

    }
    public void saveFile(){

    }
    public  ArrayList<PdfFileProperties> getFavouriteList(){
        ArrayList<PdfFileProperties> favouriteList = new ArrayList<>();
        for (int i=0; i < this.size(); i++) {
            PdfFileProperties pdfProperties = this.get(i);
            if (pdfProperties.isFavourite())
                favouriteList.add(pdfProperties);
        }
        return favouriteList;
    }
    private int compareTwoPdfFileAs(PdfFileProperties pdf1,PdfFileProperties pdf2, int index){
        if (index == PdfSortCase.SORTBYNAME.getValue()){
            return pdf1.CompareToAsName(pdf2);
        }
        else
            return pdf2.CompareToAsDate(pdf2);
    }
    public void sortFileAs(final PdfSortCase pdfSortCase){
        Collections.sort(this, new Comparator<PdfFileProperties>() {
            public int compare(PdfFileProperties pdf1, PdfFileProperties pdf2) {
                return compareTwoPdfFileAs(pdf1,pdf2,pdfSortCase.getValue());
            }
        });
    }

}
