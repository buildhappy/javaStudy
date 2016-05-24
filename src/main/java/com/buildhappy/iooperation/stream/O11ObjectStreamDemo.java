package com.buildhappy.iooperation.stream;

import java.io.*;

class O11ObjectStreamDemo {
    public static void main(String[] args) throws Exception {
        //writeObj();
        readObj();
    }

    public static void readObj() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"));

        Person p = (Person) ois.readObject();

        System.out.println(p);
        ois.close();
    }

    public static void writeObj() throws IOException {
        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream("obj.txt"));

        oos.writeObject(new Person("lisi0", 399, "kr"));

        oos.close();
    }

    static class Person implements Serializable {

        public static final long serialVersionUID = 42L;

        private String name;
        transient int age;
        static String country = "cn";

        Person(String name, int age, String country) {
            this.name = name;
            this.age = age;
            this.country = country;
        }

        public String toString() {
            return name + ":" + age + ":" + country;
        }
    }
}
