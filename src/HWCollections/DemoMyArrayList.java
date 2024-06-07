package HWCollections;

public class DemoMyArrayList {
    public static void main(String[] args) {
        MyArrayList<Integer> test1 = new MyArrayList<Integer>();
        MyArrayList<Integer> test2 = new MyArrayList<Integer>(3);
        MyArrayList<String> testString = new MyArrayList<String>();


        // Метод вставки в конец списка
       // test2.add(2);
        test2.add(3);
        test2.add(4);
        test2.add(5);
        test2.add(6);
        test2.add(7);
        test1.add(1);
        testString.add("Мама");
        testString.add("мыла");
        //test2.add("imposter");


        MyArrayList<Integer> test3 = new MyArrayList<Integer>(test2);
        System.out.println("Test3" + test3);


        //Все элементы test2 в test1 + поломка
        test1.addAll(test2);
        //test1.addAll(testString);

        //Вставка по индексу. Изначально в test2: 2 3 4 5 5 6 7
        test2.add(6, 8);
        System.out.println("Test2 после вставки по индексу " + test2);
        testString.add(1, "кошку");

        //Вставим побольше, чтобы проверить, корректно ли увеличивается массив
        test2.add(9);
        test2.add(10);
        test2.add(11);
        test2.add(12);

        //Сортировка test1
        test1.bubbleSort();
        System.out.println("Test1 после сортировки " + test1);

        testString.bubbleSort();
        System.out.println("Test со строками после сортировки " + testString);

        //Теперь поудаляем всякого
        test1.remove(1); //удаляем элемент 2 с индексом 1
        test2.remove(7);
        test1.remove(5);
        //поломка
        //test1.remove(12);
        //test2.remove(-1);

        System.out.println("Test1 в итоге " + test1);
        System.out.println("Test2 в итоге " + test2);
        System.out.println("Test со строками в итоге " + testString);

        //еще раз все закинем в test1
        test1.addAll(test2);
        System.out.println("Test1 и test2 " + test1);

        //Контрольная сортировка test1
        test1.bubbleSort();
        System.out.println("Test1 после сортировки-2 " + test1);
    }
}
