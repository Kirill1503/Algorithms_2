package com.example.algorithms_2;

import java.util.Objects;

public class StringListImpl implements StringList{

    private static final int INITIAL_SIZE = 15;

    private final String[] data;

    private int capacity;

    public StringListImpl(String[] data, int capacity) {
        this.data = data;
        this.capacity = capacity;
    }

    public StringListImpl () {
        data = new String[INITIAL_SIZE];
        capacity = 0;
    }

    public StringListImpl(Integer a) {
        if (a <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть положительным");
        }
        data = new String[a];
        capacity = 0;
    }
    @Override
    public Integer add(Integer item) {
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (capacity == data.length) {
            throw new IllegalArgumentException("Массив заполенен");
        }
        if (index < capacity) {
            System.arraycopy(data, index, data, index + 1, capacity - index);
        }
        data[index] = String.valueOf(item);
        capacity++;
        return item;
    }

    @Override
    public String set(int index, Integer item) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя установить null");
        }
        return data[index] = String.valueOf(item);
    }

    @Override
    public String remove(Integer item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1) {
            throw new IllegalArgumentException("Такой элемент отсутствует");
        }
        return remove(indexForRemoving);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        String removedItem = data[index];
        if (index + 1 < capacity) {
            System.arraycopy(data, index + 1, data, index, capacity - index - 1);
        }
        capacity--;
        data[capacity] = null;
        return removedItem;
    }

    @Override
    public boolean contains(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список");
        }
        Integer[] arrayForSearch = toArray();
        sortInsertions(arrayForSearch);

        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя использовать null");
        }
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя использовать null");
        }
        for (int i = data.length - 1; i > 0; i--) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        return data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null || size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[capacity];
        System.arraycopy(data, 0, array, 0, capacity);
        return array;
    }

    public void sortInsertions(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
