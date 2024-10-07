package data;

public enum AnimalData {
    CAT("cat"),
    DOG("dog"),
    DUCK("duck");

    private String name;

    AnimalData(String name) {
        this.name = name;

    }

    public String getName(){
        return name;
    }
}
