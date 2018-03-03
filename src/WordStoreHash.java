public class WordStoreHash implements WordStore
{
    private static class WordCell
    {
        String word;
        WordCell next;
        WordCell(String word)
        {
            this.word = word;
            next = null;
        }
    }
    private int pos;
    private WordCell [] myHashTable;
    private int size;
    public WordStoreHash(int size)
    {
        this.size = size;
        myHashTable = new WordCell[this.size];
    }

    public void printArray()
    {
        System.out.println();
        for(int i=0;i<myHashTable.length;i++)
        {
            System.out.println("Bucket " + i + ": ");
            WordCell start = myHashTable[i];
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
        return code%size;
    }

    public void add(String word)
    { 
        pos = hashCode(word);
        WordCell myObject = new WordCell(word);
        if(myHashTable[pos] == null)
        {
            myHashTable[pos] = myObject;
        }
        else
        {   
            myObject.next = myHashTable[pos];
            myHashTable[pos] = myObject;
        }
    }

    public int count(String word)
    {
        pos = hashCode(word);
        int count = 0;
        WordCell myObject  = myHashTable[pos];
        for(;myObject!=null;myObject = myObject.next)
        {
            if(myObject.word.equals(word))
            {
                count++;
            }
        }
        return count;
    }

    public void remove(String word)
    {
        pos = hashCode(word);
        if (myHashTable[pos] != null) 
        {
            WordCell prevEntry = null;
            WordCell entry = myHashTable[pos];
            while (entry.next != null && !entry.word.equals(word)) 
            {
                prevEntry = entry;
                entry = entry.next;
            }
            if (entry.word.equals(word)) 
            {
                if (prevEntry == null)
                    myHashTable[pos]= entry.next;
                else
                    prevEntry.next = entry.next;
            }
        }
    }
}