package testRandom;

import java.util.LinkedList;
import java.util.List;

public class Kuikui {
	public static void main(String[] args) {
//		List<Randm> ll = new LinkedList<Randm>();
//		ll.add(new Randm("Kane","Bayern",29));
//		ll.add(new Randm("Benzema","Al-Itthad", 35));
////		Randm ran = new Randm("Kane", "Bayern", 29);
//		System.out.println(ll);
		
		Randm ran = new Randm();
		ran.setName("Bergson");
		ran.setClub("JDT");
		ran.setAge(30);
		
		List<Randm> ll = new LinkedList<Randm>();
		ll.add(ran);
		
		System.out.println(ll);
	}
}