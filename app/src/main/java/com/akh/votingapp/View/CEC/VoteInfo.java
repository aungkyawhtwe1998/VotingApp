package com.akh.votingapp.View.CEC;

public class VoteInfo {
    String voteID, voterID, votedPersonID, votedDateTime;
    int votedPersonType;
    boolean firstVote, secondVote, thirdVote, fourthVote, fifthVote, sixthVote;

    public VoteInfo() {
    }

    public VoteInfo(String voteID, String voterID, String votedPersonID, String votedDateTime, int votedPersonType, boolean firstVote, boolean secondVote, boolean thirdVote, boolean fourthVote, boolean fifthVote, boolean sixthVote) {
        this.voteID = voteID;
        this.voterID = voterID;
        this.votedPersonID = votedPersonID;
        this.votedDateTime = votedDateTime;
        this.votedPersonType = votedPersonType;
        this.firstVote = firstVote;
        this.secondVote = secondVote;
        this.thirdVote = thirdVote;
        this.fourthVote = fourthVote;
        this.fifthVote = fifthVote;
        this.sixthVote = sixthVote;
    }

    public String getVoteID() {
        return voteID;
    }

    public void setVoteID(String voteID) {
        this.voteID = voteID;
    }

    public String getVoterID() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public String getVotedPersonID() {
        return votedPersonID;
    }

    public void setVotedPersonID(String votedPersonID) {
        this.votedPersonID = votedPersonID;
    }

    public String getVotedDateTime() {
        return votedDateTime;
    }

    public void setVotedDateTime(String votedDateTime) {
        this.votedDateTime = votedDateTime;
    }

    public int getVotedPersonType() {
        return votedPersonType;
    }

    public void setVotedPersonType(int votedPersonType) {
        this.votedPersonType = votedPersonType;
    }

    public boolean isFirstVote() {
        return firstVote;
    }

    public void setFirstVote(boolean firstVote) {
        this.firstVote = firstVote;
    }

    public boolean isSecondVote() {
        return secondVote;
    }

    public void setSecondVote(boolean secondVote) {
        this.secondVote = secondVote;
    }

    public boolean isThirdVote() {
        return thirdVote;
    }

    public void setThirdVote(boolean thirdVote) {
        this.thirdVote = thirdVote;
    }

    public boolean isFourthVote() {
        return fourthVote;
    }

    public void setFourthVote(boolean fourthVote) {
        this.fourthVote = fourthVote;
    }

    public boolean isFifthVote() {
        return fifthVote;
    }

    public void setFifthVote(boolean fifthVote) {
        this.fifthVote = fifthVote;
    }

    public boolean isSixthVote() {
        return sixthVote;
    }

    public void setSixthVote(boolean sixthVote) {
        this.sixthVote = sixthVote;
    }
}