package dto;

public class shw1013DTO {
    private int empno;         // 사원 번호
    private String ename;      // 사원 이름
    private String job;        // 직무
    private double sal;        // 급여

    // 기본 생성자
    public shw1013DTO() {
    }

    // 모든 필드를 포함한 생성자
    public shw1013DTO(int empno, String ename, String job, double sal) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.sal = sal;
    }

    // Getter 및 Setter 메서드
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
        return "shw1013DTO{" +
               "empno=" + empno +
               ", ename='" + ename + '\'' +
               ", job='" + job + '\'' +
               ", sal=" + sal +
               '}';
    }
}
