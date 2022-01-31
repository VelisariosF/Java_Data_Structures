/**
 * This class implements an AVL tree of integers that supports insertion.
 * deletion , search and lots of other functional operations.
 *  
 * @author Velisarios Fafoutis
 *     
 */


public class AVL{
   
    private class Noode{
         int key;
         Noode left , right;
        
         public Noode(Noode left, Noode right, int value){
            this.key = value;
            this.left = left;
            this.right = right;
             
         }
    }

    private Noode root = null ;
    private int countnodes = 0;
    private int height = 0;
    
    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(Noode k){
        if(k == null) return 0;
        return Math.max(getHeight(k.left), getHeight(k.right)) + 1;
    }
   /*
    *  getBfactor returns the balance factor of the specific node temp
    *  b_factor must be -1,0 or 1
    *  In any other case rotations must be implemented  
    */
   
    private int  getBfactor(Noode temp){
        int left_height = getHeight(root.left);
        int right_height = getHeight(root.right);
        int b_factor = left_height - right_height;
        return b_factor;
    }


    //Right rotation function

    private Noode Right_rotation(Noode r){
         Noode temp;
         temp = r.left;
         r.left = temp.right;
         temp.right = r;
         
         return temp;
    }

    //Left rotation function

    private Noode Left_rotation(Noode r){
        Noode temp;
        temp = r.right;
        r.right = temp.left;
        temp.left = r;

        return temp;
    } 

    // On a left left case we perfome a right rotation
   
    private Noode leftLeftCase(Noode r){
        return Right_rotation(r);
    }
    // On a right right case we perform a left rotaion
    private Noode rigthRightCase(Noode r){
        return Left_rotation(r);
    }


    // Left-Right-Case

    private Noode Left_Right_Case(Noode r){
          r.left = Left_rotation(r.left);
          return leftLeftCase(r);
    

    }

    // Right-Left-Case
    private Noode Right_Left_Case(Noode r){
        r.right = Right_rotation(r.right);
        return rigthRightCase(r);
    }
    
    private Noode getBalanced(Noode r){
        if(getBfactor(r) == 2){
            if(getBfactor(r.right) >= 0)
            {
                   return rigthRightCase(r);
            }
            else
            {
                return Right_Left_Case(r);
            }

        }

        if(getBfactor(r) == -2)
        {
            if(getBfactor(r.left) >= 0)
            {
                return Left_Right_Case(r);
            }
            else
            {
                return leftLeftCase(r);
            }
        }
        return r;
         
    }
    //Searches if the currentn node exists in the tree

    public boolean exists(int value){
         return exists(root, value);
    }

    private boolean exists(Noode r, int value){
        // Base case: reached bottom, value not found
        if(r == null)
        {
            return false;
        }
        else
        {
            if(value == r.key)
            {
                return true;
            }
            else if(value < r.key)
             {
                return exists(r.left, value);
            }
            else
            {
                return exists(r.right, value);
            }

        }

        
    }
     public boolean insert(int value){
         if(exists(value)){
             return false;
         }
         else{
             root = insert(root, value);
             countnodes ++;
             return true;
         }
     }
     private Noode insert(Noode r,int value){
        if(r == null)
        {
           r = new Noode(null,null,value);

        }
        else
        {
           if(value <= r.key)
            {
               insert(r.left, value);
               getBalanced(r.left);  
            } 
            else
            {
                insert(r.right, value);
                getBalanced(r.right);
            }      
        }

        return r ;
    }


    public int getsize(){
        return countnodes;
    }
     
    public boolean remove(int value){
        if(exists(value)){
             root = remove(root, value);
             countnodes -- ;
             return true;
        }
        else
        {
            return false;
        }

    }
    public Noode remove(Noode r , int value){
        if(r == null) return null;

        if(value < r.key)
        {
            r.left = remove(r.left, value);
        }
        else if(value > r.key)
        {
            r.right = remove(r.right, value);
        }
        else
        {
           if(r.left == null)
           {
               Noode rightchild = r.right;

               
               r = null;
               return rightchild;
           }
           else if(r.right == null){
               Noode leftchild = r.left;

               
               r = null;
               return leftchild;
           }
           else
           {
            Noode tmp = findMin(r.right);

            r.key = tmp.key;

            r.right = remove(r.right, tmp.key);
          
           }
        }

        return r;
    } 

    private Noode findMin(Noode r){
        while(r.left != null) 
        {
            r = r.left;
        }
        return r;
    }

    private Noode findMax(Noode r){
        while(r.right != null){
            r = r.right;
        }
        return r;
    }


}