package service;

import dao.EmpDAO;
import java.sql.Date;

public class EmpUpdateService {

    private EmpDAO empDAO;

    public EmpUpdateService() {
        this.empDAO = new EmpDAO();
    }

    public boolean updateEmployee(int empNo, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        return empDAO.updateEmployee(empNo, ename, job, mgr, hiredate, sal, comm, deptno);
    }
}