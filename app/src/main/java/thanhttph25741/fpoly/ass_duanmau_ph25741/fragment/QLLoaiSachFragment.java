package thanhttph25741.fpoly.ass_duanmau_ph25741.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thanhttph25741.fpoly.ass_duanmau_ph25741.R;
import thanhttph25741.fpoly.ass_duanmau_ph25741.adapter.LoaiSachAdapter;
import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.LoaiSachDAO;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.ItemClick;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.LoaiSach;

public class QLLoaiSachFragment extends Fragment {
    RecyclerView recyclerLoaiSach;
    EditText editLoaiSach;
    LoaiSachDAO dao;
    int maloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisach, container, false);

         recyclerLoaiSach = view.findViewById(R.id.recyclerLoaiSach);
         editLoaiSach = view.findViewById(R.id.edtloaisach);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);

        dao =new LoaiSachDAO(getContext());
         loadData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = editLoaiSach.getText().toString();

                if(dao.themLoaiSach(tenloai)){
                    loadData();
                    editLoaiSach.setText("");
                }else {
                    Toast.makeText(getContext(), "Them loai sach khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = editLoaiSach.getText().toString();
 LoaiSach loaiSach = new LoaiSach(maloai, tenloai);
                if(dao.thayDoiLoaiSach(loaiSach)){
                    loadData();
                    editLoaiSach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thay doi thong tin khong thanh cong", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }

    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);

        ArrayList<LoaiSach> list =  dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClick(LoaiSach loaiSach) {
                editLoaiSach.setText(loaiSach.getTenloai());
                maloai = loaiSach.getId();
            }
        });
        recyclerLoaiSach.setAdapter(adapter);
    }
}
