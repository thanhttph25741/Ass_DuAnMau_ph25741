package thanhttph25741.fpoly.ass_duanmau_ph25741.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thanhttph25741.fpoly.ass_duanmau_ph25741.R;
import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.ThanhVienDAO;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.ThanhVien;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
private Context context;
private ArrayList<ThanhVien> list;
private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_thanhvien,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtmatv.setText("Ma TV: "+list.get(position).getMatv());
        holder.txthoten.setText("Ho ten: "+list.get(position).getHoten());
        holder.txtnamsinh.setText("Nam sinh: "+list.get(position).getNamsinh());

        holder.ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
showDiaLogCapNhatThongTin(list.get(holder.getAdapterPosition()));
            }
        });

        holder.ivdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = thanhVienDAO.xoaThongTinTV(list.get(holder.getAdapterPosition()).getMatv());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xoa thanh vien thanh cong", Toast.LENGTH_SHORT).show();
                loaddata();
                break;
                    case 0:
                        Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                   break;
                    case -1:
                        Toast.makeText(context, " Thanh vien ton tai phieu muon, khong duoc phep xoa", Toast.LENGTH_SHORT).show();
                break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView txtmatv, txthoten, txtnamsinh;
ImageView ivedit, ivdel;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                 txtmatv = itemView.findViewById(R.id.txtMaTV);
                txthoten = itemView.findViewById(R.id.txtHoTen);
                txtnamsinh = itemView.findViewById(R.id.txtNamSinh);
                ivedit= itemView.findViewById(R.id.ivEdit);
                ivdel = itemView.findViewById(R.id.ivDel);

            }
        }

        private void showDiaLogCapNhatThongTin(ThanhVien thanhVien){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.dialog_chinhsua_thanhvien,null);
            builder.setView(view);

            TextView txtMaTV = view.findViewById(R.id.txtMaTV);
            TextView edtHoTen = view.findViewById(R.id.edHoTen);
            TextView edtNamSinh = view.findViewById(R.id.edtNamSinh);

            txtMaTV.setText("ma TV:"+thanhVien.getMatv());
            edtHoTen.setText(thanhVien.getHoten());
            edtNamSinh.setText(thanhVien.getNamsinh());

            builder.setNegativeButton("Cap Nhat", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    String hoten = edtHoTen.getText().toString();
                    String namsinh = edtNamSinh.getText().toString();

                    int id = thanhVien.getMatv();

                    boolean check = thanhVienDAO.capNhatThongTinTV(id,hoten,namsinh);
                    if(check){
                        Toast.makeText(context, "Cap nhat thong tin thanh cong", Toast.LENGTH_SHORT).show();
                   loaddata();
                    }else {
                        Toast.makeText(context, "Cap nhat thong tin khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        private void loaddata(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();

        }

}
