package Studi_Kasus2;

public class Bis extends Kendaraan{
    public Bis(){
        tipeKendaraan = "Bis";
        hargaKendaraan = 66500;
        maksKapasitas = 12;
        kursi = new boolean[4][5];
    }

    public void aturanBis(){
        System.out.println("Aturan Penggunaan Bis:");
        System.out.println("1. Memiliki SIM");
        System.out.println("2. Menyerahkan KTP");
        System.out.println("3. Menaati Peraturan Lalu Lintas");
        System.out.println("4. Menggunakan Sabuk Pengaman");
        System.out.println("5. Bersedia Mengganti Rugi Kerusakan");
    }

    public void kelengkapanBis(){
        System.out.println("Kelengkapan Bis:");
        System.out.println("1. Air Minum Dus (1) & Snack");
        System.out.println("2. Payung (6)");
        System.out.println("3. STNK Bis");
        System.out.println("4. Bensin penuh");
        System.out.println("5. Televisi");
    }

    @Override
    public double ongkosKirim(){
        return 10000;
    }

    @Override
    public void tampilkanKursi(){
        System.out.println("==========Kursi Tersedia==========");
        System.out.println("       [1][2]  L  [4][5]");
        for (int i = 0; i < kursi.length; i++){
            for (int j = 0; j < kursi[i].length; j++){
                if (!kursi[i][j]){
                    System.out.print("[   ]");
                } else {
                    System.out.print("[ X ]");
                } if (j == 1) System.out.print("  ||  ");
            }
            System.out.println();
        }
    }

    @Override
    public void display(){
        System.out.println("Tipe                : " + tipeKendaraan);
        System.out.println("Harga/jam           : " + hargaKendaraan);
        System.out.println("Maksimum Kapasitas  : " + maksKapasitas);
        System.out.println("Kursi               : " + getDaftarKursi());
        System.out.println("Deskripsi          : Sleeper Bus cocok untuk perjalanan jauh" );
        aturanBis();
        kelengkapanBis();
    }
}
