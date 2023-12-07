package db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.ExecuteStatement;
import static db2.Patient.connectDB;

public class Room {
    private final int roomNumber;
    private String type;
    private int nurseID;
    private int floor;

    public Room(int roomNumber, String type, int nurseID, int floor) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.nurseID = nurseID;
        this.floor = floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public int getNurseID() {
        return nurseID;
    }

    public int getFloor() {
        return floor;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public static void insertData(Room rc) {
        try {
            System.out.println("Insert into room values("+
                    rc.getRoomNumber()+",'"
                    + rc.getType() +"',"
                    + rc.getNurseID() +","
                    + rc.getFloor()+ ")"
            );

            Connection con = connectDB();
            ExecuteStatement("Insert into room values("+
                    rc.getRoomNumber()+",'"
                    + rc.getType() +"',"
                    + rc.getNurseID() +","
                    + rc.getFloor()+ ")"
            );

            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getData(ArrayList<Room> data) throws SQLException, ClassNotFoundException {
        Connection con = connectDB();
        String SQL;
        System.out.println("Connection established");

        SQL = "select * from room order by roomNumber";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while ( rs.next() )
            data.add(new Room(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            Integer.parseInt(rs.getString(3)),
                            Integer.parseInt(rs.getString(4))
                    )
            );

        rs.close();
        stmt.close();
        con.close();
        System.out.println("Connection closed " + data.size());
    }

    public void updateType(int roomNumber, String type) {
        Connection con;
        try {
            System.out.println("update room set type = '"+type + "' where roomNumber = "+ roomNumber);
            con = connectDB();
            ExecuteStatement("update room set type = '"+type + "' where roomNumber = "+ roomNumber +";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateFloor(int roomNumber, int floor) {
        Connection con;
        try {
            System.out.println("update room set floor = '"+floor + "' where roomNumber = "+ roomNumber);
            con = connectDB();
            ExecuteStatement("update room set floor = '"+floor + "' where roomNumber = "+ roomNumber +";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateNurseID(int roomNumber, int nurseID) {
        Connection con;
        try {
            System.out.println("update room set nurseID = '"+nurseID + "' where roomNumber = "+ roomNumber);
            con = connectDB();
            ExecuteStatement("update room set nurseID = '"+nurseID + "' where roomNumber = "+ roomNumber +";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(Room row) {
        Connection con;
            try {
                System.out.println("delete from  Room where roomNumber="+row.getRoomNumber() + ";");
                con = connectDB();
                ExecuteStatement("delete from  Room where roomNumber="+row.getRoomNumber() + ";");
                con.close();
                System.out.println("Connection closed");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                }
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", nurseID=" + nurseID +
                ", floor=" + floor +
                '}';
    }
}

