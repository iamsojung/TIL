/* 
 나이가 같은 사람들 중에 이름 순서대로 정렬하는건 어떻게 구현해?
 나이가 같은 사람들 중에 이름 순서대로 정렬하려면, Comparator 인터페이스를 구현한 객체에서 두 번째 기준으로 이름을 추가하면 됩니다. 
 예를 들어, 다음과 같이 Person 클래스의 Comparator 객체를 구현하여 나이와 이름 순서로 정렬할 수 있습니다.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Person 객체 리스트 생성
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("John", 25));
        list.add(new Person("Adam", 20));
        list.add(new Person("Kate", 30));
        list.add(new Person("Bob", 25));
        list.add(new Person("Alice", 25));

        // 나이와 이름으로 정렬하는 Comparator 구현
        Comparator<Person> ageAndNameComparator = new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                // 나이로 우선 비교
                int ageDiff = Integer.compare(p1.getAge(), p2.getAge());
                if (ageDiff != 0) {
                    return ageDiff;
                }
                // 나이가 같으면 이름으로 비교
                return p1.getName().compareTo(p2.getName());
            }
        };

        // 나이와 이름으로 정렬
        Collections.sort(list, ageAndNameComparator);

        // 정렬된 리스트 출력
        System.out.println("Sorted by age and name:");
        for (Person p : list) {
            System.out.println(p);
        }
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
/*
위 코드에서는 Person 클래스에 대한 Comparator 인터페이스를 구현하여 나이와 이름으로 정렬하는 ageAndNameComparator를 구현하였습니다.
compare 메서드에서는 나이로 먼저 비교한 뒤, 나이가 같은 경우 이름으로 비교하도록 구현하였습니다.
이후 Collections.sort() 메서드에 ageAndNameComparator 객체를 전달하여 리스트를 정렬하였습니다.
*/
