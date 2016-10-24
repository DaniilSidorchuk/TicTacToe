package NewVersionWithVisualization;




public class PlayerHuman implements Player {

    private String name;
    private int age;
    private String type;

    public PlayerHuman(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }


    public String getType() {
        return type;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }
}
