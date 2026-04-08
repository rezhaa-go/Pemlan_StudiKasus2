package Studi_Kasus2;

public class Guest extends Pelanggan {

    public Guest(String firstName, String lastName, String noHP) {
        super(firstName, lastName, noHP);
        this.member = false;
    }

    public Guest(String firstName, String noHP) {
        super(firstName, "", noHP);
        this.member = false;
    }
}
