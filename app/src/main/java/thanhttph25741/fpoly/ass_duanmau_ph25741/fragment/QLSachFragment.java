package thanhttph25741.fpoly.ass_duanmau_ph25741.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import thanhttph25741.fpoly.ass_duanmau_ph25741.R;
import thanhttph25741.fpoly.ass_duanmau_ph25741.SachAdapter;
import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.LoaiSachDAO;
import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.SachDao;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.LoaiSach;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.Sach;

public class QLSachFragment extends Fragment {
    SachDao sachDao;
    RecyclerView recyclerSach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.fragment_qlsach,container,false);
         recyclerSach = view.findViewById(R.id.recyclerSach);
        FloatingActionButton floadAdd = view.findViewById(R.id.floatAdd);


         sachDao = new SachDao(getContext());
loadData();

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });
        return view;
    }
    private void loadData(){
        ArrayList<Sach> list= sachDao.getDSDauSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        SachAdapter adapter = new SachAdapter(getContext(),list);
        recyclerSach.setAdapter(adapter);
    }
    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach, null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        Spinner spnloaisach = view.findViewById(R.id.spnLoaiSach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnloaisach.setAdapter(simpleAdapter);
        builder.setNegativeButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String tensach =edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                HashMap<String,Object> hs = (HashMap<String, Object>) spnloaisach.getSelectedItem();
                int maloai = (int)hs.get("maloai");
                boolean check = sachDao.themSachMoi(tensach,tien,maloai);
                if(check){
                    Toast.makeText(getContext(), "Them sach thanh cong", Toast.LENGTH_SHORT).show();
              loadData();
                }else {
                    Toast.makeText(getContext(), "Them sach that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
AlertDialog alertDialog = builder.create();
alertDialog.show();
    }
    private ArrayList<HashMap<String,Object>> getDSLoaiSach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String,Object>> lisyHM = new ArrayList<>();

        for (LoaiSach loai : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maloai", loai.getId());
            hs.put("tenloai", loai.getTenloai());
            lisyHM.add(hs);
        }
        return lisyHM;
    }
}
