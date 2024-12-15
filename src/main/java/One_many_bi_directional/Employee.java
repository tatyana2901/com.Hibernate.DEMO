package One_many_bi_directional;


import One_one_bi_directional.Detail;
import jakarta.persistence.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "employees_bi_direct")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "salary")
    private int salary;

    @ManyToOne(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE,
    CascadeType.REFRESH,
    CascadeType.DETACH}) //если указать cascade = Cascade.all или не убрать remove из списка, то удаление работника /департамента повлечет за собой удаление других работников и других департаментов как следствие
    @JoinColumn(name="department_id")//мы его так назвали в БД postgreSQL в таблице employees_bi_direct
    private Department department;

    public Employee() {
    }

    public Employee(String name, String surname, int salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}