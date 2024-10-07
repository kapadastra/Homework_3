package animals;


public class Animal {


    private int id;
    private String type, name, color;
    private int weight, age;


//    public Animal(String name, Integer age, Integer weight, String color) {
//        this.name = name;
//        this.age = age;
//        this.weight = weight;
//        this.color = color;
//        this.type = "";
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    public Animal(String name, Integer age, Integer weight, String color, String type) {
        this.color = color;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.age = age;
    }

    public Animal(int id, String color, String name, int weight, String type, int age) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.age = age;
    }


    @Override
    public String toString() {
        //определения год\лет\года
        String year = "лет";
        int lastButOneCharacterAge = age / 10;
        int lastCharacterAge = age % 10;

        if (lastButOneCharacterAge == 1) {
            return year;
        } else if (lastCharacterAge == 1) {
            year = "год";
        } else if (lastCharacterAge == 2 || lastCharacterAge == 3 || lastCharacterAge == 4) {
            year = "года";
        }

        return "Привет! меня зовут " + name + ", мне " + age + " " + year + ", я вешу - " + weight + " кг, мой цвет - " + color;


    }
}

