package thanhttph25741.fpoly.ass_duanmau_ph25741.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thanhttph25741.fpoly.ass_duanmau_ph25741.R;
import thanhttph25741.fpoly.ass_duanmau_ph25741.adapter.Top10Adapter;
import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.ThongkeDAO;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.Sach;

public class ThongkeTop10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_thongke_top10, container, false);
        RecyclerView recyclertop10 = view.findViewById(R.id.recyclerTop10);

        ThongkeDAO thongkeDAO = new ThongkeDAO(getContext());
        ArrayList<Sach> list = thongkeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclertop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter= new Top10Adapter(getContext(),list);
        recyclertop10.setAdapter(adapter);

       return view;
    }
}
