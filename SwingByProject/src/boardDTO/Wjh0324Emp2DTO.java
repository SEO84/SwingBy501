package boardDTO;

import java.sql.Date;
import java.util.ArrayList;

public class Wjh0324Emp2DTO {
	public final int empno;
	public final String ename;
	public final String job;
	public final Integer mgr;
	public final Date hireDate;
	public final Double sal;
	public final Double comm;
	public final Integer deptno;
	
	public Wjh0324Emp2DTO(
		int empno,
		String ename,
		String job,
		Integer mgr,
		Date hireDate,
		Double sal,
		Double comm,
		Integer deptno
		) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
	
	public Object[] getRowFor(String ...colNames) {
		ArrayList<Object> al = new ArrayList<>();
		
		for (String col : colNames) {
			col = col.toLowerCase();
			switch(col) {
			case "empno": al.add(empno); break;
			case "ename": al.add(ename); break;
			case "job": al.add(job); break;
			case "mgr": al.add(mgr); break;
			case "hiredate": al.add(hireDate); break;
			case "sal": al.add(sal); break;
			case "comm": al.add(comm); break;
			case "deptno": al.add(deptno); break;
			default: al.add(null); break;
			}
		}
		
		return al.toArray(new Object[al.size()]);
	}
}
