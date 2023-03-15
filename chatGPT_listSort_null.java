/*
어떻게 예외처리해? 예시 코드 보여줘
Comparator에서 null 값 처리를 위한 예외 처리는 다음과 같이 할 수 있습니다.
*/
public class Person {
    private String name;
    private Integer age;

    // constructor, getter, setter, etc.

    public static Comparator<Person> AGE_NAME_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            // null 값 처리
            if (p1 == null && p2 == null) {
                return 0;
            } else if (p1 == null) {
                return 1;  // p1이 null이면 p2가 더 작다고 판단
            } else if (p2 == null) {
                return -1;  // p2가 null이면 p1이 더 작다고 판단
            }

            // 나이로 비교
            int ageCompare = Integer.compare(p1.getAge(), p2.getAge());
            if (ageCompare != 0) {
                return ageCompare;
            }

            // 나이가 같으면 이름으로 비교
            if (p1.getName() == null && p2.getName() == null) {
                return 0;
            } else if (p1.getName() == null) {
                return 1;
            } else if (p2.getName() == null) {
                return -1;
            }
            return p1.getName().compareTo(p2.getName());
        }
    };
}
/*
위의 코드에서는 null 값 처리를 위해, null 값을 가지고 있는 객체를 다른 객체보다 크거나 작은 값으로 간주하여 처리합니다.
이 예시 코드에서는 p1이 null인 경우 p2를 더 작은 값으로 처리하고, p2가 null인 경우 p1을 더 작은 값으로 처리합니다.
이후에는, null이 아닌 객체들 간의 비교를 일반적인 방법으로 수행합니다.
이 예시 코드에서는 나이로 비교를 우선적으로 수행하고, 나이가 같은 경우에는 이름으로 비교합니다.
이 때, 이름이 null인 경우에 대해서도 위와 같은 방식으로 처리하여, null 값이 있는 객체를 정렬할 때에도 예외 처리를 완료합니다.
*/
/*
코드를 더 줄일 수는 없을까??
네, 코드를 더 간결하게 작성할 수 있습니다.
Java 8부터는 Comparator 인터페이스에 새로운 메서드인 thenComparing()이 추가되었습니다.
이 메서드는 기존 Comparator 객체에 추가적인 정렬 기준을 더할 수 있는 메서드로,
첫 번째 Comparator로 정렬한 결과가 같은 경우, 두 번째 Comparator를 사용하여 추가적으로 정렬을 수행합니다.
따라서, 이름을 비교하는 Comparator를 추가적으로 구현할 필요 없이, 아래와 같이 코드를 간결하게 작성할 수 있습니다.

public class Person {
    private String name;
    private Integer age;

    // constructor, getter, setter, etc.

    public static Comparator<Person> AGE_NAME_COMPARATOR = Comparator.comparing(Person::getAge)
            .thenComparing(Comparator.nullsLast(Comparator.naturalOrder()).reversed(), Person::getName);
}
위 코드에서는 먼저 getAge() 메서드를 사용하여 나이를 기준으로 Comparator를 생성합니다.
이후에는 thenComparing() 메서드를 사용하여, 이름을 비교하는 Comparator를 추가로 생성합니다.
이름을 비교할 때, Comparator.nullsLast() 메서드를 사용하여 null 값을 가장 마지막에 위치하도록 설정하고,
Comparator.naturalOrder() 메서드를 사용하여 이름을 오름차순으로 정렬합니다. 마지막으로 reversed() 메서드를 사용하여 이름을 내림차순으로 정렬합니다.
위와 같이 thenComparing() 메서드를 사용하면, 코드를 더 간결하게 작성할 수 있으며, 가독성도 좋아집니다.
*/
