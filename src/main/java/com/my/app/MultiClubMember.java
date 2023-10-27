package com.my.app;

public class MultiClubMember extends Member{
    public MultiClubMember(String memberType, int memberID, String name, double fees,int pMembershipPoints) {
        super(memberType, memberID, name, fees);
    }
    private int membershipPoints;

    public int getMembershipPoints() {
        return membershipPoints;
    }

    public void setMembershipPoints(int pMembershipPoints) {
        this.membershipPoints = pMembershipPoints;
    }
    @Override
    public String toString() {
        return getName()+" "+membershipPoints;
    }

}
