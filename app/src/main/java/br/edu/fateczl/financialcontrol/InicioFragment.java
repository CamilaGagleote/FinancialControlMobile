package br.edu.fateczl.financialcontrol;

/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InicioFragment extends Fragment {

    private View view;
    private TextView tvCapa;
    public InicioFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        tvCapa = view.findViewById(R.id.tv_capa);
        return view;

    }
}