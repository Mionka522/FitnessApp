package com.my.app;

public class SingleClubMember extends Member{
    public SingleClubMember(String pMemberType, int pMemberID, String pName, double pFees, int pClub) {
        super(pMemberType, pMemberID, pName, pFees);
    }
    private int club;
    @Override
    public String toString() {
        return getName()+" "+club;
    }

    public int getClub() {
        return club;
    }

    public void setClub(int pClub) {
        this.club = pClub;
    }
}
