package hu.nye.progtech.finalTorpedo;

public class Location {

    public static final int notGuessed = 0;
    public static final int hit = 1;
    public static final int miss = 2;

    private boolean hasShip;
    private int status;
    private int lengthOfShip;
    private int facingOfShip;

    public Location(){
        status = 0;
        hasShip = false;
        lengthOfShip = -1;
        facingOfShip = -1;
    }

    public boolean checkHit()
    {
        if (status == hit)
            return true;
        else
            return false;
    }

    public boolean checkMiss(){
        if (status == miss)
            return true;
        else
            return false;
    }

    public boolean isNotGuessed(){
        if (status == notGuessed)
            return true;
        else
            return false;
    }

    public void markHit(){
        setStatus(hit);
    }

    public void markMiss(){
        setStatus(miss);
    }

    public void setShip(boolean val) { this.hasShip = val; }

    public boolean hasShip() {
        return hasShip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLengthOfShip() {
        return lengthOfShip;
    }

    public void setLengthOfShip(int lengthOfShip) {
        this.lengthOfShip = lengthOfShip;
    }

    public int getFacingOfShip() {
        return facingOfShip;
    }

    public void setFacingOfShip(int facingOfShip) {
        this.facingOfShip = facingOfShip;
    }

}
