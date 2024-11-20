package repository;

import domain.Car;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class CarRepositoryTextFile extends FileRepository<String, Car>{
    public CarRepositoryTextFile(String fileName) {
        super(fileName);
    }

//   protected void readFromFile() {
//        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
//            String line;
//            while((line = reader.readLine()) != null){
//                String[] parts = line.split(",");
//                String id = parts[0];
//                String model = parts[1];
//                int year = Integer.parseInt(parts[2]);
//                Car car = new Car(id, model, year, true);
//                //populate the memorepo directly(in-memory)
//                super.add(car.getId(), car);
//            }
//        }
//        catch (IOException e)
//        {e.printStackTrace();
//        }
//    }
    protected void readFromFile()
    {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                if(tokens.length != 3)
                    throw new IOException("File not valid.\n");
                else{
                    Car car = new Car(tokens[0], tokens[1], Integer.parseInt(tokens[2]), true);
                    elements.put(car.getId(), car);
                }
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

//    protected void writeToFile(List<Car> cars) {
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
//            for(Car car : cars){
//                writer.write(car.getId() + "," + car.getModel() + "," + car.getYear());
//                writer.newLine();
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();}
//        }
    protected void writeToFile(){
        try(BufferedWriter bw = new BufferedWriter( new FileWriter(fileName))){
            Iterable<Car> iter = getAll();
            String separator = ",";
            for(Car car : iter){
                bw.write(car.getId() + separator + car.getModel() + separator + car.getYear());
                bw.newLine();
            }
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
