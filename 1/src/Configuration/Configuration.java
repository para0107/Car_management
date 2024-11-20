package Configuration;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;

public class Configuration {
    private Properties properties = new Properties();

    public Configuration(String filePath)
    {
        try(FileInputStream input = new FileInputStream(filePath)){
            properties.load(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public String getRepositoryType()
    {
        return properties.getProperty("RepositoryType", "text");
    }
    public String getCarsFileName()
    {
        return properties.getProperty("CarsFileName", "cars.txt");
    }
}
