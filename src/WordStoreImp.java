public class WordStoreImp implements WordStore
{
    private static class Cell
    {
        String word;
        Cell next;
        Cell(String word)
        {
            this.word = word;
            next = null;
        }
    }
    private int pos, size;
    private Cell [] LinkedCells;
    public WordStoreImp(int size)
    {
        this.size = size;
        LinkedCells = new Cell[this.size];
    }

    public void printArray()
    {
        System.out.println();
        for(int i=0;i<LinkedCells.length;i++)
        {
            System.out.println("Bucket " + i + ": ");
            Cell start = LinkedCells[i];
            while(start!=null)
            {
                System.out.print(start.word + " " );
                start = start.next;
            }
            System.out.println();
        }
    }

    public int hashCode(String word)
    { 
        int code = word.hashCode()%size;
        code%=size;
        if(code<0)
        {
            code+=size;
        }
        return code;
    }

    public void add(String word)
    { 
        pos = hashCode(word);
        if(LinkedCells[pos] == null)
        {
            LinkedCells[pos] = new Cell(word);
        }
        else
        {   Cell pointer = new Cell(word);
            pointer.next = LinkedCells[pos];
            LinkedCells[pos] = pointer;
        }
    }

    public int count(String word)
    {
        pos = hashCode(word);
        int count = 0;
        Cell pointer = LinkedCells[pos];
        for(;pointer!=null;pointer = pointer.next)
        {
            if(pointer.word.equals(word))
                count++;
        }
        return count;
    }

    public void remove(String word)
    {
        pos = hashCode(word);
        if (LinkedCells[pos] != null) 
        {
            if(LinkedCells[pos].word.equals(word))
            {
                LinkedCells[pos] = LinkedCells[pos].next;
            }
            else
            {
                Cell prevEntry = null;
                Cell entry = LinkedCells[pos];
                while (entry.next != null && !entry.word.equals(word)) 
                {
                    prevEntry = entry;
                    entry = entry.next;
                }
                
                if (entry.word.equals(word)) 
                {
                    prevEntry.next = entry.next;
                }
            }
        }
    }
}
