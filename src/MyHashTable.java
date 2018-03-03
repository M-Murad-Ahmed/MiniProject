public class MyHashTable implements WordStore
{
    private class Cell
    {
        String word;
        int count;
        Cell next;
        Cell(String word)
        {
            this.word = word;
            count++;
            this.next = null;
        }
    }  
    private int pos,size;
    private Cell [] LinkedCells;
    public MyHashTable(int size)
    {
        this.size = size;
        LinkedCells = new Cell[this.size];
    }

    public void usedIndexes()
    {
        int count = 0;
        Cell ptr;
        for(int i=0;i<LinkedCells.length;i++)
        {
            if(LinkedCells[i]!=null)
            {
                ptr = LinkedCells[i];
                for(;ptr!=null;ptr = ptr.next)
                {
                    count+=ptr.count;
                }
            }
        }
        System.out.println("Total number of words : " + count);
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

    public void add(String word)
    { 
        pos = hashCode(word);
        if(LinkedCells[pos] == null)
        {
            LinkedCells[pos] = new Cell(word); 
            return;
        }
        else
        {   
            Cell ptr = LinkedCells[pos];
            for(;ptr!=null;ptr= ptr.next)
            {
                if(ptr.word.equals(word))
                {
                    ptr.count++;
                    return;
                }
            }

        }    
        Cell newWord = new Cell(word);
        newWord.next = LinkedCells[pos];
        LinkedCells[pos] = newWord;
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

    public int count(String word)
    {
        pos = hashCode(word);
        if(LinkedCells[pos]!=null)
        {
            Cell ptr = LinkedCells[pos];
            for(;ptr!=null;ptr = ptr.next)
            {
                if(ptr.word.equals(word))
                    return ptr.count;
            }
        }
        return 0;
    }

    public void remove(String word)
    {
        pos = hashCode(word);
        Cell ptr = LinkedCells[pos];
        if(ptr != null)
        {
            while(ptr.next != null && !ptr.word.equals(word))
            {
                ptr = ptr.next;
            }

            if(ptr.word.equals(word))
            {
                if(ptr.count == 1)
                {
                    ptr = ptr.next;
                    LinkedCells[pos] = ptr;
                    //return;
                }
                else if(ptr.count > 1)
                {
                    ptr.count--;
                    //return;
                }
            }
        }
    }
}
