package Studi_Kasus2;
import java.time.LocalDate;

abstract class Promotion implements Applicable, Comparable<Promotion> {
    protected String kodePromo;
    protected double jumlahDiskon;
    protected LocalDate tanggalMulai;
    protected LocalDate tanggalBerakhir;

    public Promotion(String kodePromo, double jumlahDiskon, LocalDate tanggalMulai, LocalDate tanggalBerakhir) {
        this.kodePromo = kodePromo;
        this.jumlahDiskon = jumlahDiskon;
        this.tanggalMulai = tanggalMulai;
        this.tanggalBerakhir = tanggalBerakhir;
    }

    public abstract double getBesarPromo (Pemesanan pemesanan);

    @Override
    public int compareTo(Promotion other) {
        if (this.jumlahDiskon < other.jumlahDiskon) {
            return 1; 
        } else if (this.jumlahDiskon > other.jumlahDiskon) {
            return -1;
        } else {
            return 0;
        }
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    @Override
    public double hitungPotongan(double totalHarga) throws PromoException{
        return 0;
    }

    @Override
    public double hitungCashback(double totalHarga) throws PromoException{
        return 0;
    }

    @Override
    public double hitungPotonganOngkir(double ongkosKirim) throws PromoException {
        return 0;
    }

    public boolean isPromoValid(){
        LocalDate sekarang = LocalDate.now();
        return (sekarang.isEqual(tanggalMulai) || sekarang.isAfter(tanggalMulai)) &&
               (sekarang.isEqual(tanggalBerakhir) || sekarang.isBefore(tanggalBerakhir));
    }
}
