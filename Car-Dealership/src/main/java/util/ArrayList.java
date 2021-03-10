package util;
// import java.util.ArrayList;

public class ArrayList<T> {
    private final int DEFAULT_CAPACITY = 10;
    Object[] arr;
    private int size;
    private int currentCapacity;

    public void add(T t){
        if(size >= currentCapacity){
            grow();
        }
        arr[size] = t;
        size++;
    }

    public void remove(T t){
        int index = indexOf(t);
        if(index < 0)
            return;

        for(;index < size-1;index++){
            arr[index] = arr[index + 1];
        }
        arr[size] = null;
        size--;
    }

    public Object get(int i){
        if(i < 0 || i >= size){
            return null;
        }

        return arr[i];
    }

    public int indexOf(T t){
        int index = 0;
        for(; index < size; index++){
            if(arr[index].equals(t)){
                return index;
            }
        }
        return -1;
    }

    public void sort(){
        // Implement sorting algorithm
        for(int i = 0; i < size;i++){
            for(int j = i+1; j < size; j++){
                // Compare stuff
            }
        }
    }

    private void grow(){
        int newCapacity = currentCapacity + (currentCapacity >> 1);
        Object[] temp = new Object[newCapacity];

        for(int i = 0; i < currentCapacity; i++)
            temp[i] = arr[i];

        arr = temp;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            sb.append(arr[i].toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public int size(){
        return size;
    }

    public ArrayList(){
        arr = new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
        size = 0;
    }
}
