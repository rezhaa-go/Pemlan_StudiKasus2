package Studi_Kasus2;

abstract class Kendaraan {
    protected String tipeKendaraan;
    protected double hargaKendaraan;
    protected int maksKapasitas;
    protected boolean[][] kursi;

    public boolean pilihKursi(int i, int j){
        if (i >= 0 && i < kursi.length && j >= 0 && j < kursi[i].length){
            if (!kursi[i][j]){
                kursi[i][j] = true;
                System.out.println("Kursi " + (i+1) + "-" + (j+1) + " berhasil dipilih");
                return true;
            } else {
                System.out.println("Kursi " + (i+1) + "-" + (j+1) + " sudah terisi. Silakan pilih kursi lain");
                return false;
            }
        } else {
            System.out.println("Pilihan kursi tidak valid. Silakan pilih kursi lain.");
            return false;
        }
    }

    public String getDaftarKursi() {
    String hasil = "";
    boolean ada = false;

    for (int i = 0; i < kursi.length; i++) {
        for (int j = 0; j < kursi[i].length; j++) {
            if (kursi[i][j]) { 
                hasil += "[" + (i + 1) + "," + (j + 1) + "] ";
                ada = true;
            }
        }
    }
    return ada ? hasil : "Tidak ada kursi dipilih";
}

    public double hargaSewa(int lamaPenyewaan) {
        return hargaKendaraan * lamaPenyewaan;
    }

    public abstract double ongkosKirim();
    public abstract void display();
    public abstract void tampilkanKursi();

    public String getTipe() { return tipeKendaraan; }
    public double getHarga() { return hargaKendaraan; }
    public int getKapasitas() { return maksKapasitas; }
}