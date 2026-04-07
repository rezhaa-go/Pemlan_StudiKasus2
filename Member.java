package Studi_Kasus2;

public class Member extends Pelanggan {
    private String kapanMember;
    public Member(String firstName, String lastName, String noHP, String tanggal) {
        super(firstName, lastName, noHP);
        this.member = true;
        this.kapanMember = tanggal;
    }

    public String getKapanMember() {
        return kapanMember;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Tanggal Bergabung   : " + kapanMember);
    }
}
