package dto;

public class shw1013DTO {
    private int empno;
    private String ename;
    private String job;
    private double sal;

    // 기본 생성자
    public shw1013DTO() {
        super();
    }

    // 매개변수가 있는 생성자
    public shw1013DTO(int empno, String ename, String job, double sal) {
        super();
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.sal = sal;
    }

    // Getter 및 Setter
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "shw1013DTO [empno=" + empno + ", ename=" + ename + ", job=" + job + ", sal=" + sal + "]";
    }
}
