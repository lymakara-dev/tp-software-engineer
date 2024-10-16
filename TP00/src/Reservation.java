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
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);

        setRoomNumber(roomNumber);
        setPersonName(personName);
        setStartTime(startTime);
        setEndTime(endTime);
        this.remark = remark;
    }
    

    
    public String getroomNumber() {
        return roomNumber;
    }


    public void setRoomNumber(String roomNumber) {
        if(roomNumber == null || roomNumber.isEmpty()){
            throw new IllegalArgumentException("Room number cannot be empty.");
        }
        Pattern pattern = Pattern.compile("^[A-Z]-[0-9]{3,3}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(roomNumber);
        boolean matchFound = matcher.find();
        if(matchFound){
            this.roomNumber=roomNumber;
            return;
        }else {
            throw new IllegalArgumentException("Room number format is not correct. Expected format: A-123");
        }
    }


    public String getPersonName() {
        return personName;
    }


    public void setPersonName(String personName) {
        this.personName = personName;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }
    

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalDateTime endTime) {
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

