package assignment3;

public class MyTestingClass {
    private final int id;
    private final String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        for (int i = 0; i < name.length(); i++) {
            result = 31 * result + name.charAt(i);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) o;
        return id == other.id && name.equals(other.name);
    }

    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", name='" + name + "'}";
    }
}
