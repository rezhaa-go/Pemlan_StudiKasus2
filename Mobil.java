package Studi_Kasus2;

public class Mobil extends Kendaraan {
    public Mobil(){
        tipeKendaraan = "Mobil";
        hargaKendaraan = 12500;
        maksKapasitas = 6;
        kursi = new boolean[3][2];
    }


    public void aturanMobil(){
        System.out.println("Aturan Penggunaan Mobil:");
        System.out.println("1. Memiliki SIM");
        System.out.println("2. Menyerahkan KTP");
        System.out.println("3. Menaati Peraturan Lalu Lintas");
        System.out.println("4. Menggunakan Sabuk Pengaman");
        System.out.println("5. Bersedia Mengganti Rugi Kerusakan");
    }

    public void kelengkapanMobil(){
        System.out.println("Kelengkapan Motor:");
        System.out.println("1. Air Minum 300ml (6)");
        System.out.println("2. Payung (6)");
        System.out.println("3. STNK Mobil");
        System.out.println("4. Bensin penuh");
        System.out.println("5. Palu Darurat");
    }

    @Override
    public double ongkosKirim(){
        return 7000;
    }

    @Override
    public void tampilkanKursi(){
        System.out.println("==========Kursi Tersedia==========");
        for (int i = 0; i < kursi.length; i++){
            for (int j = 0; j < kursi[i].length; j++){
                if (!kursi[i][j]){
                    System.out.print("[   ]");
                } else {
                    System.out.print("[ X ]");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void display(){
        System.out.println("Tipe                : " + tipeKendaraan);
        System.out.println("Harga/hari          : " + hargaKendaraan);
        System.out.println("Maksimum Kapasitas  : " + maksKapasitas);
        System.out.println("Kursi               : " + getDaftarKursi());
        System.out.println("Deskripsi           : Mobil cocok untuk perjalanan keluarga" );
        aturanMobil();
        kelengkapanMobil();
    }
}
