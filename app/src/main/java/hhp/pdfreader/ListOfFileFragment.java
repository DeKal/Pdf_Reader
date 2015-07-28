package hhp.pdfreader;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


/**
 * Created by hhphat on 7/27/2015.
 */
public class ListOfFileFragment extends ListFragment {
    private ListOfPdfProperties listOfFileProperties;
    private FileListAdapter adapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listOfFileProperties = new ListOfPdfProperties();
        adapter = new FileListAdapter(getActivity(),R.layout.row_layout_of_list_file,
                listOfFileProperties);

        this.setListAdapter(adapter);

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

}
