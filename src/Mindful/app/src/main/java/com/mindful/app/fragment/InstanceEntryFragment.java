package com.mindful.app.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mindful.Logger;
import com.mindful.app.R;
import com.mindful.data.model.InstanceModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.mindful.app.fragment.InstanceEntryFragment.Listener} interface
 * to handle interaction events.
 * Use the {@link InstanceEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class InstanceEntryFragment extends Fragment {

    public static final String TAG = InstanceEntryFragment.class.getSimpleName();

    private Listener mListener;
    private final InstanceModel mInstanceModel;

    private TextView mValueView;
    private EditText mCommentView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InstanceEntryFragment.
     */
    public static InstanceEntryFragment newInstance() {
        InstanceEntryFragment fragment = new InstanceEntryFragment();
        Bundle args = new Bundle();

        // TODO put arguments

        fragment.setArguments(args);
        return fragment;
    }

    public InstanceEntryFragment() {
        // Required empty public constructor

        mInstanceModel = new InstanceModel();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO get arguments
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instance_entry, container, false);

        View btnUp = view.findViewById(R.id.btn_up);
        btnUp.setOnClickListener(ON_CLICK);

        View btnDown = view.findViewById(R.id.btn_down);
        btnDown.setOnClickListener(ON_CLICK);

        View btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(ON_CLICK);

        mValueView = (TextView) view.findViewById(R.id.instance_value);
        mCommentView = (EditText) view.findViewById(R.id.et_instance_text);

        // Update the value view
        mValueView.setText(String.valueOf(mInstanceModel.getValue()));


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Listener {

        // TODO add some stuff
    }


    // Helper classes

    private final View.OnClickListener ON_CLICK = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_up:
                    Logger.i(TAG, "btn up");

                    mInstanceModel.increment();
                    mValueView.setText(String.valueOf(mInstanceModel.getValue()));

                    break;
                case R.id.btn_down:
                    Logger.i(TAG, "btn down");

                    mInstanceModel.deccrement();
                    mValueView.setText(String.valueOf(mInstanceModel.getValue()));

                    break;
                case R.id.btn_save:
                    Logger.i(TAG, "btn save");

                    Editable editable = mCommentView.getText();
                    if (!TextUtils.isEmpty(editable)) {
                        mInstanceModel.setComment(editable.toString());
                    }

                    Uri uri = mInstanceModel.saveInstance(getActivity().getContentResolver());
                    if (uri != null) {
                        Toast.makeText(getActivity(), "Saved your shit, I hope you're HAPPY!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "You Failed! I hope you're HAPPY!", Toast.LENGTH_LONG).show();
                    }

                    break;
                default:
                    throw new IllegalStateException("This hasn't been implemented");
            }
        }
    };


}
