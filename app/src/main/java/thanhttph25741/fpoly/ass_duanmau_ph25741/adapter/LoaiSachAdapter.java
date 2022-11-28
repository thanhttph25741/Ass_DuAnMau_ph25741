package thanhttph25741.fpoly.ass_duanmau_ph25741.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import thanhttph25741.fpoly.ass_duanmau_ph25741.R;
import thanhttph25741.fpoly.ass_duanmau_ph25741.dao.LoaiSachDAO;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.ItemClick;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.LoaiSach;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
   private Context context;
   private ArrayList<LoaiSach> list;
   private ItemClick itemClick;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list, ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_loaisach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttenLoai.setText("Ten Loai: "+list.get(position).getTenloai());
        holder.txtMaloai.setText("Ma loai: " +String.valueOf(list.get(position).getId()));
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        list.clear();
                        list = loaiSachDAO.getDSLoaiSach();

                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Khong the xoa loai sach nay vi da co sach thuoc the loai nay", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xoa loai sach khong thanh cong", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        holder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach = list.get(holder.getAdapterPosition());
                itemClick.onClick(loaiSach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaloai, txttenLoai;
        ImageView ivDel, ibEdit ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenLoai = itemView.findViewById(R.id.txtTenLoai);
            txtMaloai = itemView.findViewById(R.id.txtMaloai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ibEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
