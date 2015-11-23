package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HardAITest {

	@Test
	public void test() {
		List<String> a=new ArrayList<String>();
		a.add("a");
		a.add("b");
		System.out.println(a);
		List<String> b=a;
		b.remove("b");
		System.out.println(a);
		
	}

}
