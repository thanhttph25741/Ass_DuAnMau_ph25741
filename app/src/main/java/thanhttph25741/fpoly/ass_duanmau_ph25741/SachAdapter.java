package thanhttph25741.fpoly.ass_duanmau_ph25741;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import thanhttph25741.fpoly.ass_duanmau_ph25741.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
  private Context context;
  private ArrayList<Sach> list;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycler_sach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.txtMaSach.setText("Ma Sach: "+ list.get(position).getMasach());
        holder.txttenSach.setText("Ten Sach: "+ list.get(position).getTensach());
        holder.txtGiaThue.setText("Gia Thue: "+ list.get(position).getGiathue());
        holder.txtMaLoai.setText("Ma Loai: "+ list.get(position).getMaloai());
        holder.txtTenLoai.setText("Ten Loai: "+ list.get(position).getTenloai());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView txtMaSach, txttenSach, txtGiaThue,txtTenLoai,txtMaLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txttenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai = itemView.findViewById(R.id.txtMaloai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);

        }
    }
}
