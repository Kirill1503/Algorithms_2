package com.example.algorithms_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StringListImplTest {

    StringList stringList = new StringListImpl();

    @ParameterizedTest
    @MethodSource("params")
    void add(String params) {
        stringList.add(Integer.valueOf(params));
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThat(stringList.isEmpty()).isFalse();
    }


    @Test
    void add2() {
        stringList.add(0, 1);
        stringList.add(1, 2);
        stringList.add(2, 3);
        stringList.add(3, 4);
        stringList.add(4, 5);
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThat(stringList.size()).isEqualTo(5);
        assertThat(stringList.get(2)).isEqualTo("test3");
        assertThat(stringList.isEmpty()).isFalse();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.add(-1, 2));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.add(1, null));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.add(15, 3));
    }
    @Test
    void set() {
        stringList.add(0, 1);
        stringList.add(1, 2);
        stringList.add(2, 3);
        stringList.set(0, 3);
        assertThat(stringList.get(0)).isEqualTo("test5");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.set(-1, 3));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.set(15, 2));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.set(3, null));
    }

    @Test
    void remove() {
        stringList.add(1);
        System.out.print(Arrays.toString(stringList.toArray()));
        stringList.remove(1);
        System.out.print(Arrays.toString(stringList.toArray()));
        stringList.isEmpty();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.remove(-1));
    }

    @Test
    void testRemove() {
        stringList.add(0, 1);
        stringList.add(1, 2);
        stringList.add(2, 3);
        stringList.remove(0);
        assertThat(stringList.get(0)).isEqualTo(1);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.remove(14));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.remove(-1));
    }

    @Test
    void contains() {
        stringList.add(0, 1);
        stringList.add(1, 2);
        stringList.add(2, 3);
        assertThat(stringList.contains(1)).isTrue();
        assertThat(stringList.contains(6)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("params")
    void indexOf(Integer item) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.indexOf(null));
        stringList.add(1);
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThat(stringList.indexOf(4)).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        stringList.add(1);
        stringList.add(2);
        stringList.add(3);
        stringList.add(4);
        stringList.add(5);
        stringList.add(6);
        stringList.add(7);
        stringList.add(8);
        stringList.add(9);
        stringList.add(10);
        stringList.add(11);
        stringList.add(12);
        stringList.add(13);
        stringList.add(14);
        stringList.add(15);
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.lastIndexOf(null));
        assertThat(stringList.lastIndexOf(15)).isEqualTo(14);
        assertThat(stringList.indexOf(21)).isEqualTo(-1);
    }

    @Test
    void get() {
    }

    @Test
    void testEquals() {
        StringList stringList1 = new StringListImpl();
        StringList stringList2 = new StringListImpl();
        StringList stringList3 = new StringListImpl();
        stringList1.add(1);
        stringList1.add(2);
        stringList1.add(3);
        stringList2.add(1);
        stringList2.add(2);
        stringList2.add(3);
        stringList3.add(3);
        assertThat(stringList1.equals(stringList2)).isEqualTo(true);
        assertThat(stringList1.equals(stringList3)).isEqualTo(false);
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    public void clear() {
        stringList.add(1);
        stringList.add(2);
        stringList.add(3);
        stringList.add(4);
        assertThat(stringList.size()).isEqualTo(4);
        stringList.clear();
        assertThat(stringList.isEmpty()).isTrue();
        assertThat(stringList.toArray()).hasSize(0);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.get(1));
    }

    @Test
    void toArray() {
    }

    public static Stream<Arguments> params (){
        return Stream.of(
                Arguments.of(4),
                Arguments.of(3),
                Arguments.of(6)
        );
    }
}