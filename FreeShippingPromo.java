package Studi_Kasus2;

import java.time.LocalDate;

public class FreeShippingPromo extends Promotion {
    public FreeShippingPromo(String kodePromo, double jumlahDiskon, LocalDate tanggalMulai, LocalDate tanggalBerakhir) {
        super(kodePromo, jumlahDiskon, tanggalMulai, tanggalBerakhir);
    }

    @Override
    public double getBesarPromo(Pemesanan pemesanan) {
        return Math.min(jumlahDiskon, pemesanan.getBiayaOngkir());
    }

    @Override
    public boolean isCustomerEligible(Pelanggan pelanggan) {
        return (pelanggan instanceof Member);
    }

    @Override
    public boolean isMinimumPriceEligible(Pemesanan pemesanan) {
        return true;
    }

    @Override
    public boolean isShippingFeeEligible(Pemesanan pemesanan) {
        return true;
    }

    @Override
    public double hitungPotonganOngkir(double ongkosKirim) throws PromoException {
        if (ongkosKirim <= 0){
            throw new PromoException("Pastikan ongkos kirim tidak negatif!");
        }
        return Math.min(jumlahDiskon, ongkosKirim);
    }
}