package repository;

import domain.Reservation;

import java.io.*;
import java.util.HashMap;

public class ReservationRepositoryBinaryFile extends FileRepository<String, Reservation>
{
    public ReservationRepositoryBinaryFile(String fileName)
    {
        super(fileName);
    }

    protected void readFromFile()
    {
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            elements = (HashMap<String, Reservation>) inputStream.readObject();
        }
        catch (EOFException e)
        {
            this.elements = new HashMap<String, Reservation>();
            writeToFile();
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected void writeToFile()
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            outputStream.writeObject(elements);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
