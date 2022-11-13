package m11.d12.Ch1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		PriorityQueue<Person> personPriorityQueue = new PriorityQueue<>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				if (o1.getAge() > o2.getAge()) {
					return 1;
				}
				return -1;
			}
		});
		for (int i=0;i < 10; i++) {
			Person person = new Person(i, 10-i);
			personPriorityQueue.offer(person);
		}

		personPriorityQueue.peek().setAge(2);
		while (personPriorityQueue.size() != 0) {
			Person pollOne = personPriorityQueue.poll();
			Person pollSec = personPriorityQueue.poll();
			if (pollSec.getId() < pollOne.getId() && pollSec.getAge() <= pollOne.getAge()) {
				// 对编号小的做操作
				pollSec.setAge(pollSec.getAge() + 4);
			} else {
				pollOne.setAge(pollOne.getAge() + 4);
			}
			personPriorityQueue.offer(pollOne);
			personPriorityQueue.offer(pollSec);
		}
	}
}

@Data
@AllArgsConstructor
class Person {
	private int id;

	private int age;
}