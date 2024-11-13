package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "scott";
    private static final String DB_PASSWORD = "tiger";

    public boolean registerEmployee(int empNo, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        String query = "INSERT INTO emp2 (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, empNo);
            pstmt.setString(2, ename);
            pstmt.setString(3, job);
            pstmt.setObject(4, mgr, java.sql.Types.INTEGER);
            // pstmt.setString(5, hiredate);
            pstmt.setDate(5, hiredate);
            pstmt.setObject(6, sal, java.sql.Types.DOUBLE);
            pstmt.setObject(7, comm, java.sql.Types.DOUBLE);
            pstmt.setObject(8, deptno, java.sql.Types.INTEGER);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getEmp2List() {
        List<String> empList = new ArrayList<>();
        String query = "SELECT ENAME FROM emp2";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                empList.add(rs.getString("ENAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empList;
    }

    // 추가된 메서드
    public boolean updateEmployee(int empNo, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        String query = "UPDATE emp2 SET ENAME = ?, JOB = ?, MGR = ?, HIREDATE = ?, SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, ename);
            pstmt.setString(2, job);
            pstmt.setObject(3, mgr, java.sql.Types.INTEGER);
            pstmt.setDate(4, hiredate);
            pstmt.setObject(5, sal, java.sql.Types.DOUBLE);
            pstmt.setObject(6, comm, java.sql.Types.DOUBLE);
            pstmt.setObject(7, deptno, java.sql.Types.INTEGER);
            pstmt.setInt(8, empNo);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}