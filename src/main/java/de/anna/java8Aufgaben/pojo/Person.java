package de.anna.java8Aufgaben.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Person {

    private String name;
    private String nachname;
    private Integer alter;
    private Date geburtsDatum;
    private String pesel;



    public Person(String name, String nachname, Integer alter){
        this.name = name;
        this.nachname = nachname;
        this.alter = alter;
    }

    public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Integer getAlter() {
        return alter;
    }

    public void setAlter(Integer alter) {
        this.alter = alter;
    }

    public Date getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(Date geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }



    @Override
    public String toString() {
        return name + " " + nachname + " " + alter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getNachname(), person.getNachname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNachname());
    }
}