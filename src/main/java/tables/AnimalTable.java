package tables;

import animals.Animal;
import animals.pets.Cat;
import animals.pets.Dog;
import db.IDBConnect;
import db.MySQLConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable {
    private static final String NAME = "animals";

    public AnimalTable() {
        super(NAME);
    }


    public void write (Animal animal) {
        this.dbConnector.execute(String.format("INSERT INTO %s (id,color,name,weight,type,age) "
                +"VALUES('%s','%s','%s','%s','%s','%s')",NAME,
                animal.getId(),animal.getColor(),animal.getName(),
                animal.getWeight(), animal.getType(),animal.getAge()));
    }

    public void print(ResultSet rs) throws SQLException {
        System.out.printf("%-10s %-20s %-10s %-5s %-10s %-5s%n", "Color", "Name", "Weight", "ID", "Type", "Age");
        System.out.println("-----------------------------------------------------------");

        while(rs.next()){
            System.out.printf("%-10s %-20s %-10s %-5d %-10s %-5d%n",
                    rs.getString("color"),
                    rs.getString("name"),
                    rs.getString("weight"),
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getInt("age"));
        }
    }

    public ArrayList<Animal> read() throws SQLException{
        ArrayList<Animal> animal = new ArrayList<>();
        ResultSet resultSet;

        dbConnector = new MySQLConnect();
        resultSet = this.dbConnector.executeQuery(String.format("SELECT * FROM  %s;", NAME));

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String color = resultSet.getString("color");
            String name = resultSet.getString("name");
            int weight = resultSet.getInt("weight");
            String type = resultSet.getString("type");
            int age = resultSet.getInt("age");

            Animal animals = new Animal(id,color,name,weight,type,age);

            animal.add(animals);
        }


        return animal;
    }

    public void updateAnimal(Animal animal) {
        String query = "UPDATE " + NAME + " SET color = ?, name = ?, weight = ?, age = ? WHERE id = ?";

        try (PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query)) {
            stmt.setString(1, animal.getColor());
            stmt.setString(2, animal.getName());
            stmt.setInt(3, animal.getWeight());
            stmt.setInt(4, animal.getAge());
            stmt.setInt(5, animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Animal getAnimalById(int id) {
        String query = "SELECT * FROM " + NAME + " WHERE id = ?";
        Animal animal = null;

        try (PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String color = rs.getString("color");
                String name = rs.getString("name");
                int weight = rs.getInt("weight");
                String type = rs.getString("type");
                int age = rs.getInt("age");

                animal = new Animal(id, color, name, weight, type, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

    public List<Animal> getAnimalsByType(String type) {
        List<Animal> animals = new ArrayList<>();
        String query = "SELECT * FROM " + NAME + " WHERE type = ?";

        try (PreparedStatement stmt = dbConnector.getConnection().prepareStatement(query)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String color = rs.getString("color");
                String name = rs.getString("name");
                int weight = rs.getInt("weight");
                int age = rs.getInt("age");

                Animal animal = new Animal(id, color, name, weight, type, age);
                animals.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animals;
    }



}
