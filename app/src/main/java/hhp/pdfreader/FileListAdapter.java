package hhp.pdfreader;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hhphat on 7/27/2015.
 */
public class FileListAdapter extends ArrayAdapter<PdfFileProperties>{
    private int layoutId;
    private int selectedPosition;
    public FileListAdapter(Context context, int layoutId, ArrayList<PdfFileProperties> listFile) {
        super(context, layoutId, listFile);
        setLayoutId(layoutId);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theinflater = LayoutInflater.from(getContext());

        final View row = theinflater.inflate(layoutId, parent, false);
        PdfFileProperties pdfFile = getItem(position);
        //set title
        TextView txt = (TextView) row.findViewById(R.id.textView_titleView);
        txt.setText(pdfFile.getTitle());


        EssentialFunction.setFont(getContext(), txt, 20, "fonts/EBGaramond08-Regular.ttf", 0);
        //set icon
        ImageView img = (ImageView) row.findViewById(R.id.imageView_iconOfPdf);
        img.setImageResource(pdfFile.getIconId());
        //set favourite start
        ImageButton imgBtn = (ImageButton) row. findViewById(R.id.imageButton_favouriteImage);
        imgBtn.setTag(position);
        if (pdfFile.isFavourite()) {
            imgBtn.setImageResource(R.drawable.favourite_star);
        }
        else
            imgBtn.setImageResource(R.drawable.un_favourite_star);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCurrentPdfFileFavouriteProperties(v);
            }
        });
        return row;
    }

    private void changeCurrentPdfFileFavouriteProperties(View v) {
        PdfFileProperties pdfFile = getItem((int)v.getTag());

        if (pdfFile.isFavourite()){
            pdfFile.setFavourite(false);
        }
        else {
            pdfFile.setFavourite(true);
        }
        notifyDataSetChanged();
    }



    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void updateCurSelectedPosition(int position) {
        selectedPosition = position;
    }

}
