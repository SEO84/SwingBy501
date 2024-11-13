package service;

import dao.EmpDAO;
import java.sql.Date;

public class EmpInsertService {

    private EmpDAO empDAO;

    public EmpInsertService() {
        this.empDAO = new EmpDAO();
    }

    public boolean registerEmployee(int empNo, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        return empDAO.registerEmployee(empNo, ename, job, mgr, hiredate, sal, comm, deptno);
    }
}