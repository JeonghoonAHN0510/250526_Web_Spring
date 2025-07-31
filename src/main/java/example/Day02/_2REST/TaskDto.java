package example.Day02._2REST;

public class TaskDto {
    private int age;
    private String name;

    public TaskDto() { }
    public TaskDto(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override
    public String toString() {
        return "TaskDto{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
} // class end
