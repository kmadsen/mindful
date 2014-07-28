package com.mindful.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindful.app.R;

/**
 * Created by Charlie on 7/27/2014.
 */
public class GraphViewFragment extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GraphViewFragment.
     */
    public static GraphViewFragment newInstance() {
        GraphViewFragment fragment = new GraphViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_view, container, false);
        return view;
    }
}