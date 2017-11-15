package edu.temple.lab8;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link URLBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class URLBar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment URLBar.
     */

    mActivityWebView web;

    // TODO: Rename and change types and number of parameters
    public static URLBar newInstance(String param1) {
        URLBar fragment = new URLBar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View urlFragment = inflater.inflate(R.layout.fragment_urlbar, container, false);
        final Button btnGo = urlFragment.findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"Go pressed",Toast.LENGTH_LONG).show();
                    EditText url = (EditText) urlFragment.findViewById(R.id.etxtURL);
                    String URL = url.getText().toString();
                    web.goClick(URL);
            }
        });
        if(!mParam1.equals("empty")){
            EditText url = (EditText) urlFragment.findViewById(R.id.etxtURL);
            url.setText(mParam1);
            //web.goClick(mParam1);
        }
        return urlFragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof mActivityWebView){
            web = (mActivityWebView) context;
        }
    }

    public interface mActivityWebView{
        public void goClick(String URL);
    }

}
