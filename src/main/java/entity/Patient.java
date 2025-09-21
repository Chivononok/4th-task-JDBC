package entity;

import java.util.Objects;

public class Patient {
    private long id;
    private String name;
    private String surname;
    private String diagnos;
    private Integer chamber;
    private Integer age;

    public Patient(Long id, String name, String surname, String diagnos, int chamber, int age){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.diagnos = diagnos;
        this.chamber = chamber;
        this.age = age;
    }

    public Patient(){

    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Patient other = (Patient) obj;
        return id == other.id &&
                chamber == other.chamber &&
                age == other.age &&
                Objects.equals(name, other.name) &&
                Objects.equals(surname, other.surname) &&
                Objects.equals(diagnos, other.diagnos);
    }

    public String toString(){
        return "id: " + this.id + " name: " + this.name + " surname: " + this.surname + " diagnos: " + this.diagnos +
                " chamber: " + this.chamber + " age: " + this.age;
    }

    public Integer getChamber(){
        return this.chamber;
    }
    public String getName(){
        return  this.name;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getDiagnos(){
        return this.diagnos;
    }
    public Integer getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setDiagnos(String diagnos){
        this.diagnos = diagnos;
    }
    public void setChamber(Integer chamber){
        this.chamber=chamber;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
