package Studi_Kasus2;

abstract class Pelanggan {
    protected String firstName;
    protected String lastName;
    protected String noHP;
    protected boolean member;
    protected Pemesanan pemesananAktif;

    public Pelanggan(String firstName, String lastName, String noHP){
        this.firstName = firstName;
        this.lastName = lastName;
        this.noHP = noHP;
    }

    public String getFullName(){
        if (lastName == null || lastName.isEmpty()){
            return firstName;
        } else {
            return firstName + " " + lastName;
        }
    }

    public boolean isMember(){
        return member;
    }

    public void makeOrder(Kendaraan k, int lamaPenyewaan, String tanggalMulai, String tanggalSelesai, int jumlahPenumpang, int jumlahKendaraan){
        if (this.pemesananAktif == null){
            this.pemesananAktif = new Pemesanan(this, k, lamaPenyewaan, tanggalMulai, tanggalSelesai, jumlahPenumpang, jumlahKendaraan);
            System.out.println("Pesanan berhasil dibuat!");
        } else {
            System.out.println("Anda sudah memiliki pesanan aktif. Silakan selesaikan pesanan Anda sebelum membuat pesanan baru.");
        }
    }

        public void confirmPay(int nomorInput) {
            if (pemesananAktif != null && pemesananAktif.getNoPesanan() == nomorInput) {
                pemesananAktif.pay();
            } else {
                System.out.println("Nomor pesanan tidak valid atau pesanan belum dibayar.");
            }
        }

    public double diskon(double totalHarga){
        if(member){
            return (totalHarga * 0.01);
        } else {
        return 0;
        }
    }

    public Pemesanan getPemesananAktif() {
        return pemesananAktif;
    }

    public void display(){
        System.out.println("Nama                : " + getFullName());
        System.out.println("Nomor HP            : " + noHP);
        System.out.println("Member              : " + (member ? "Ya" : "Bukan"));
    }
}
