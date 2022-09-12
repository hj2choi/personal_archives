package hk.ust.hackathon.peertopeerforeignexchange;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ExchangeViewActivity extends ListActivity {

    String[] exchangeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exchangeList = getResources().getStringArray(R.array.Currency);

        setListAdapter(new ArrayAdapter<String>(this,R.layout.exchange_item, exchangeList));

        //getListView().setBackground(getResources().getDrawable(R.drawable.backgradient));
    }


}