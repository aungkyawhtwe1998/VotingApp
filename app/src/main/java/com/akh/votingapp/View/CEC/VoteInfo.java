package com.akh.votingapp.View.CEC;

public class VoteInfo {
    String voteID,voterID,votedPersonID,votedDateTime;
    int votedPersonType;

    public VoteInfo() {
    }

    public VoteInfo(String voteID, String voterID, String votedPersonID, String votedDateTime, int votedPersonType) {
        this.voteID = voteID;
        this.voterID = voterID;
        this.votedPersonID = votedPersonID;
        this.votedDateTime = votedDateTime;
        this.votedPersonType = votedPersonType;
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
}
