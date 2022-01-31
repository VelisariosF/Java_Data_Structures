/**
 * This class implements the hash table data structure.The specific hast table 
 * can contain only integers.
 * 
 * @author Velisarios Fafoutis
 * 
 */

import java.util.LinkedList;
import java.util.Vector;
public class HashTable{
      private Vector<LinkedList<Integer>> hashtable ;
      private int vecsize;
      public HashhTable(){
          vecsize = 100;
          hashtable = new Vector<>(vecsize);
          for(int i = 0; i < vecsize; i++)
          {
              LinkedList<Integer> linkedlist = new LinkedList<>();
              hashtable.add(linkedlist);
          }
        
      }

      public int getHashTableSize(){
          return hashtable.size();
      }

      private int hashFunction(int value){
          return value % hashtable.size();
      }

      public HashTable insertHashTable(int value){
          if(!exists(value)){
            int index = hashFunction(value);
            hashtable.get(index).add(value);
            return this;
          }
          return this;
     }

    public HashTable deletion(int value){
        if(exists(value))
        {

        
          int index = hashFunction(value);
          for(int i = 0; i < hashtable.get(index).size(); i ++)
          {
              if(hashtable.get(index).get(i) == value)
              {
                hashtable.get(index).remove(i);
              }
          }
        }
          return this;
    }

     public boolean exists(int value){
          int index = hashFunction(value);
          for(int i = 0; i < hashtable.get(index).size(); i ++)
          {
              if(hashtable.get(index).get(i) == value)
              {
                return true;
              }
              
          }
          
        return false;
    } 

    public void printelements(){
      for(LinkedList<Integer> i : this.hashtable){
         for(int j : i)
         {
           System.out.println(j);
         }
      }
    }

}
