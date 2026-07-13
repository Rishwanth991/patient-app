package p1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class patientDAO {

    public int addPatient(patient p) {

        int status = 0;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "insert into patient values(?,?,?,?,?,?)");

            ps.setInt(1, p.getPid());
            ps.setString(2, p.getPname());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getDisease());
            ps.setString(6, p.getMobile());

            status = ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }

   
    public ArrayList<patient> getPatient() {

        ArrayList<patient> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement("select * from patient");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                patient p = new patient();

                p.setPid(rs.getInt(1));
                p.setPname(rs.getString(2));
                p.setAge(rs.getInt(3));
                p.setGender(rs.getString(4));
                p.setDisease(rs.getString(5));
                p.setMobile(rs.getString(6));

                list.add(p);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}