package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("ERROR: not such resume in the base!");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == storage.length) {
            System.out.println("ERROR: the storage is full!");
        } else if (index == -1) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR: such resume already exist");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: not such resume in the base!");
        } else {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: not such resume in the base!");
        } else {
            storage[index] = storage[size-1];
            storage[size - 1] = null;
            size--;
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
