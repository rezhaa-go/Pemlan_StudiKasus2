package Studi_Kasus2;

interface Applicable {
    boolean isCustomerEligible(Pelanggan pelanggan);

    boolean isMinimumPriceEligible(Pemesanan pemesanan);

    boolean isShippingFeeEligible(Pemesanan pemesanan);

    double hitungPotongan(double totalHarga) throws PromoException;

    double hitungCashback(double totalHarga) throws PromoException;

    double hitungPotonganOngkir(double ongkosKirim) throws PromoException;
}
