package Studi_Kasus2;

public class Motor extends Kendaraan {
    public Motor(){
        tipeKendaraan = "Motor";
        hargaKendaraan = 3000;
        maksKapasitas = 2; 
    }

    public void aturanMotor(){
        System.out.println("Aturan Penggunaan Motor:");
        System.out.println("1. Gunakan Helm");
        System.out.println("2. Memiliki SIM");
        System.out.println("3. Menyerahkan KTP");
        System.out.println("4. Menaati Peraturan Lalu Lintas");
        System.out.println("5. Bersedia mengganti rugi kerusakan");
    }

    public void kelengkapanMotor(){
        System.out.println("Kelengkapan Motor:");
        System.out.println("1. Helm (2)");
        System.out.println("2. Jas Hujan (2)");
        System.out.println("3. STNK motor");
        System.out.println("4. Bensin penuh");
        System.out.println("5. Riding Gloves");
    }

    @Override
    public double ongkosKirim(){
        return 5000;
    }

    @Override
    public void tampilkanKursi(){
    }

    @Override
    public void display(){
        System.out.println("Tipe                : " + tipeKendaraan);
        System.out.println("Harga/hari          : " + hargaKendaraan);
        System.out.println("Maksimum Kapasitas  : " + maksKapasitas);
        System.out.println("Deskripsi          : Motor untuk 2 penumpang" );
        aturanMotor();
        kelengkapanMotor();
    }
}
