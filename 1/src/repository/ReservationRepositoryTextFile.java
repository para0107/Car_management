package repository;

import domain.DateConverter;
import domain.Reservation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;


public class ReservationRepositoryTextFile extends FileRepository<String, Reservation> {
    public ReservationRepositoryTextFile(String fileName) {
        super(fileName);
    }
    protected void readFromFile()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] tokens = line.split(",");
                if(tokens.length != 3)
                    throw new RuntimeException("Invalid file format");
                else{
                    DateConverter converter = new DateConverter();

                    Reservation res= new Reservation(tokens[0], converter.convertStringToDate(tokens[1]), Integer.parseInt(tokens[2]));
                   elements.put(res.getId(), res);
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    protected void writeToFile()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            Iterable<Reservation> iter = getAll();
            String separator = ",";
            for(Reservation res : iter)
            {
                writer.write(res.getId() + separator + res.getDate() + separator + res.getPrice() + "\n");
            }}
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
