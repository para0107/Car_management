package repository;
import domain.Identifiable;

import java.util.Optional;

public abstract class FileRepository <ID, T extends Identifiable<ID>> extends MemoryRepository<ID, T>
{
    protected String fileName;
    public FileRepository(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }
//    protected void loadFromFile()
//    {
////List<T> elementsFromFile = readFromFile();
////try
////{
////    for(T element : elementsFromFile)
////    {
////        ID id = element.getId();
////        super.add(id, element);
////    }
////}
////catch (Exception e)
////{
////    System.out.println("Elements are null" + e);
////}
//        readFromFile();
//    }

//    private void saveToFile(){
//        writeToFile(new ArrayList<>(super.getAll()));
//    }
    public void add(ID id, T element)
    {
        super.add(id, element);
        writeToFile();
    }
    public Optional<T> delete(ID id)
    {
        super.delete(id);
        writeToFile();
        return Optional.empty();
    }
    public void modify(ID id, T element)
    {
        super.modify(id, element);
        writeToFile();
    }
    //readFromFile is void and should populate "MemoRepo" directly
    protected abstract void readFromFile();
    protected abstract void writeToFile();
}