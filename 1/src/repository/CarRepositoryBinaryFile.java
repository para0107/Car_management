package repository;
import domain.Car;

import java.io.*;
import java.util.HashMap;
import java.util.List;


public  class CarRepositoryBinaryFile extends FileRepository<String, Car> {
    public CarRepositoryBinaryFile(String fileName) {
        super(fileName);
    }

    protected void readFromFile(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            elements = (HashMap<String,Car>) inputStream.readObject();
//            // read the list of car obj from binary file
//            //populate the memory repo
//            for(Car car : cars){
//                super.add(car.getId(), car);
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            System.out.println("File not found " + fileName);
//        }
//        catch (EOFException e)
//        {
//            System.out.println("End of file reached");
//        }
//        catch (IOException  | ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//    }
//    protected void writeToFile(List<Car> cars)
//    {
//        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
//            outputStream.writeObject(cars);
//        }
//        catch (IOException e)
//        {
//           // e.printStackTrace();
//            throw new RuntimeException(e);
//    }
//    }
        }
        catch(EOFException e)
        {
this.elements = new HashMap<String, Car>();
writeToFile();
        }
        catch ( IOException | ClassNotFoundException e){
            throw new RuntimeException(e);}
        }
    protected void writeToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(elements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
