package HWCollections;

public class MyArrayList <T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int arrayLength;

    /* К О Н С Т Р У К Т О Р Ы */

    // Создание пустого списка
    public MyArrayList(){
        this.arrayLength = 0;
        this.elements =(T[]) new Comparable[DEFAULT_CAPACITY];
    }

    //Создание списка, который имеет заданную емкость capacity
    public MyArrayList(int initCapacity) throws IllegalArgumentException{
        if (initCapacity <= 0){
            throw new IllegalArgumentException("Incorrect Capacity");
        }
        this.elements = (T[]) new Comparable[initCapacity];
        this.arrayLength = 0;
    }

    //Создание списка, в которую добавляются все элементы переданной коллекции
    public MyArrayList(MyArrayList<? extends T> collection){
        if(collection.size()==0) {
            this.arrayLength = 0;
            this.elements =(T[]) new Comparable[DEFAULT_CAPACITY];
            return;
        }

        this.elements =(T[]) new Comparable[collection.size()];
        System.arraycopy(collection.elements, 0, this.elements, 0, collection.size());
        this.arrayLength = collection.size();
    }


    /* М Е Т О Д Ы */

    /* СЛУЖЕБНЫЕ МЕТОДЫ */
    // Увеличение массива
    private void growArray(){
        T[] newElements = (T[]) new Comparable[(this.arrayLength  * 3) / 2 + 1 ];
        System.arraycopy(this.elements, 0, newElements, 0, this.arrayLength);
        this.elements = newElements;
    }

    //Проверка допустимости индекса
    private void indexCheck(int toCheck, int upperLimit) throws IndexOutOfBoundsException{
        if(toCheck > upperLimit || toCheck < 0){
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    public String toString(){
        StringBuilder arrayToString = new StringBuilder();
        arrayToString.append("[");
        for (int i=0; i < this.arrayLength; i++){
            arrayToString.append(this.elements[i]);
            arrayToString.append(", ");
        }
        //избавляюсь от последней запятой, если несколько элементов
        if (arrayToString.length() > 1)
            arrayToString.delete(arrayToString.length()-2, arrayToString.length());
        arrayToString.append("]");
        return arrayToString.toString();
    }

    //Возвращает длину массива
    public int size(){
        return this.arrayLength;
    }

    //Добавляет в конец списка новый элемент
    public void add(T newElement){
        this.add(this.arrayLength, newElement);
    }

    //Добавляет в список по индексу index новый элемент
    public void add (int index, T newElement){
        indexCheck(index, this.arrayLength);
        if(this.arrayLength == this.elements.length){
            growArray();
        }
        System.arraycopy(this.elements, index, this.elements, index + 1, this.arrayLength-index);
        this.elements[index] = newElement;
        this.arrayLength++;
    }

    //Возвращает объект из списка по индексу index
    public T get(int index){
        indexCheck(index, this.arrayLength-1);
        return this.elements[index];
    }

    //Удаляет элемент из списка по индексу index, возвращая при этом удаленный элемент
    public T remove(int index){
        indexCheck(index, this.arrayLength - 1);
        T removedElement = this.elements[index];

        System.arraycopy( this.elements, index + 1, this.elements, index, this.arrayLength-index);
        this.arrayLength--;
        return removedElement;
    }

    // Добавляет в список все элементы коллекции collection.
    // Если в результате добавления список был изменен, то возвращается true, иначе возвращается false
    public boolean addAll(MyArrayList<? extends T> collection){
        if(collection.size()==0)
            return false;
        for (int i = 0; i < collection.size(); i++) {
            this.add(collection.get(i));
        }
        return true;
    }

    // Сортировка пузырьком
    public void bubbleSort(){
        boolean flag;
        for(int i=0; i < this.arrayLength - 1; i++){
            flag = false;
            for(int j=0; j < this.arrayLength - 1 - i; j++){
                if((this.elements[j]).compareTo(this.elements[j+1]) > 0){
                    T tmp = this.elements[j];
                    this.elements[j] = this.elements[j+1];
                    this.elements[j+1] = tmp;
                    flag= true;
                }
            }
            if (!flag)
                break;
        }
    }
}
