package service;

import dao.EmpDAO;

public class EmpUpdateService {

    private EmpDAO empDAO;

    public EmpUpdateService() {
        this.empDAO = new EmpDAO();
    }

    public boolean updateEmployee(int empNo, String ename, String job, int mgr, String hiredate, double sal, double comm, int deptno) {
        return empDAO.updateEmployee(empNo, ename, job, mgr, hiredate, sal, comm, deptno);
    }
}