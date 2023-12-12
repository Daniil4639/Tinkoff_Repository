package edu.hw7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseLock {

    private final Map<Integer, Person> personList;
    private final ReadWriteLock lock;

    public PersonDatabaseLock() {
        personList = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public void add(Person person) {
        lock.writeLock().lock();
        try {
            personList.put(person.id(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void delete(int id) {
        lock.writeLock().lock();
        try {
            personList.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<Person> findByName(String name) {
        List<Person> result;
        lock.readLock().lock();
        try {
            result = personList.values().stream().filter(elem -> elem.name().equals(name)).toList();
        } finally {
            lock.readLock().unlock();
        }

        return result;
    }

    public List<Person> findByAddress(String address) {
        List<Person> result;
        lock.readLock().lock();
        try {
            result = personList.values().stream().filter(elem -> elem.address().equals(address)).toList();
        } finally {
            lock.readLock().unlock();
        }

        return result;
    }

    public List<Person> findByPhone(String phone) {
        List<Person> result;
        lock.readLock().lock();
        try {
            result = personList.values().stream().filter(elem -> elem.phoneNumber().equals(phone)).toList();
        } finally {
            lock.readLock().unlock();
        }

        return result;
    }
}
