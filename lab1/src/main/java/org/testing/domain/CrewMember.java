package org.testing.domain;

public class CrewMember extends Person{
    StatusType status;
    Reason reason;

    public enum Reason {
        WILL,
        ORDER,
        ACCIDENT,
        PHYSICAL_PRINCIPLE
    }

    public enum StatusType {
        CAPTAIN,
        PILOT,
        ENGINEER,
        PASSANGER,
        ASTRONAUT
    }

    public CrewMember(final String name,
                      final int age,
                      final Reason reason,
                      final StatusType type) {
        super(name, age);
        this.reason  = reason;
        this.status = type;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(final StatusType status) {
        this.status = status;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(final Reason reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "status='" + status + '\'' +
                ", reason=" + reason +
                '}';
    }
}
