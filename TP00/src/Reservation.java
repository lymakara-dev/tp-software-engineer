import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reservation {
    private String roomNumber;
    private String personName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String remark;

    public Reservation(String roomNumber, String personName, String startTimeString, String endTimeString,
            String remark) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startTimeFormatted = LocalDateTime.parse(startTimeString, formatter);
        LocalDateTime endTimeFormatted = LocalDateTime.parse(endTimeString, formatter);

        setRoomNumber(roomNumber);
        setPersonName(personName);
        setStartTime(startTimeFormatted);
        setEndTime(endTimeFormatted);
        setRemark(remark);
    }
    

    
    public String getroomNumber() {
        return roomNumber;
    }


    public void setRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.isEmpty()) {
            throw new IllegalArgumentException(" Room number cannot be empty");
        } else {
            Pattern pattern = Pattern.compile("^[A-Z]-[0-9]{3,3}",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(roomNumber);
            boolean valid = matcher.find();
            if (valid) {
                this.roomNumber = roomNumber;
            } else {
                throw new IllegalArgumentException(" Please enter valid room number (Example: A-301)");
            }
        }
    }


    public String getPersonName() {
        return personName;
    }


    public void setPersonName(String personName) {
        if (personName == null || personName.isEmpty()) {
            throw new IllegalArgumentException(" Name cannot be empty");
        } else {
            Pattern pattern = Pattern.compile("[aeiou]+[bcdfghjklmnpqrstvwxyz]+|[bcdfghjklmnpqrstvwxyz]+[aeiou]+",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(personName);
            boolean valid = matcher.find();
            if (valid) {
                this.personName = personName;
            } else {
                throw new IllegalArgumentException(" Please enter valid name (Example: Ly Makara)");
            }
        }
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }
    

    public void setStartTime(LocalDateTime startTime) {
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(" Start time must be in the future.");
        }
        this.startTime = startTime;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalDateTime endTime) {
        if (endTime.isBefore(startTime.plusHours(1))) {
            throw new IllegalArgumentException(" End time must be at least one hour after the start time.");
        }
        this.endTime = endTime;
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return roomNumber + "\t" + personName + "\t" +  startTime.format(formatter) + "\t" +  endTime.format(formatter) + "\t" +  remark;
    }
}