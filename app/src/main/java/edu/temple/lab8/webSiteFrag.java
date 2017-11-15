package edu.temple.lab8;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link webSiteFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class webSiteFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public webSiteFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment webSiteFrag.
     */
    // TODO: Rename and change types and number of parameters

    WebView webViewer;
    public static webSiteFrag newInstance(String param1) {
        webSiteFrag fragment = new webSiteFrag();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View wFragement = inflater.inflate(R.layout.fragment_web_site, container, false);
        if(!mParam1.equals("empty")) {
            webViewer = wFragement.findViewById(R.id.webSite);
            WebSettings webSettings = webViewer.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webViewer.setWebViewClient(new WebViewClient());
            webViewer.loadUrl(mParam1);
        }
        return wFragement;
    }

}
