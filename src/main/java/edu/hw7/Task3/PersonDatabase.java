package edu.hw7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabase {

    private final Map<Integer, Person> personList;

    public PersonDatabase() {
        personList = new HashMap<>();
    }

    public synchronized void add(Person person) {
        personList.put(person.id(), person);
    }

    public synchronized void delete(int id) {
        personList.remove(id);
    }

    public synchronized List<Person> findByName(String name) {
        return personList.values().stream().filter(elem -> elem.name().equals(name)).toList();
    }

    public synchronized List<Person> findByAddress(String address) {
        return personList.values().stream().filter(elem -> elem.address().equals(address)).toList();
    }

    public synchronized List<Person> findByPhone(String phone) {
        return personList.values().stream().filter(elem -> elem.phoneNumber().equals(phone)).toList();
    }
}
