package thanhttph25741.fpoly.ass_duanmau_ph25741.model;

public class LoaiSach {
    private int id;
    private String tenloai;
    private String nhacungcap;

    public LoaiSach(int id, String tenloai, String nhacungcap) {
        this.id = id;
        this.tenloai = tenloai;
        this.nhacungcap = nhacungcap;
    }

    public String getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public LoaiSach(int id, String tenloai) {
        this.id = id;
        this.tenloai = tenloai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
