package Studi_Kasus2;

import java.time.LocalDate;

public class CashbackPromo extends Promotion {

    public CashbackPromo(String kodePromo, double jumlahDiskon, LocalDate tanggalMulai, LocalDate tanggalBerakhir) {
        super(kodePromo, jumlahDiskon, tanggalMulai, tanggalBerakhir);
    }

    @Override
    public double getBesarPromo(Pemesanan pemesanan) {
        return jumlahDiskon;
    }

    @Override
    public boolean isCustomerEligible(Pelanggan pelanggan) {
        return true;
    }

    @Override
    public boolean isMinimumPriceEligible(Pemesanan pemesanan) {
        return pemesanan.getTotalHarga() > 100000;
    }

    @Override
    public boolean isShippingFeeEligible(Pemesanan pemesanan) {
        return false;
    }

    @Override
    public double hitungCashback(double totalHarga) throws PromoException {
        if (jumlahDiskon > totalHarga) {
            throw new PromoException("Cashback tidak boleh melebihi total harga.");
        }
        return jumlahDiskon;
    }
}