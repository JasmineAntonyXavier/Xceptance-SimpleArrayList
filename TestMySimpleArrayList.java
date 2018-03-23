package ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TestMySimpleArrayList {

	private IMySimpleArrayList simpleArrayList;

	@Before
	public void runBeforeTestMethod() {
		String[] array = new String[4];
		array[0] = "One";
		array[1] = "Two";
		simpleArrayList = new MySimpleArrayList(array, 2, 4);
	}

	//Region Constructor Tests
	public void MySimpleArrayList_ValidObject(){
		simpleArrayList = new MySimpleArrayList();
		assertEquals(0, simpleArrayList.size());
	}
	//End Region
	
	//Region Get Tests
	@Test
	public void get_ValidIndex() {
		assertEquals("One", simpleArrayList.get(0));
		assertEquals("Two", simpleArrayList.get(1));
	}

	@Test
	public void get_NegativeIndex_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.get(-1);
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}

	@Test
	public void get_IndexBiggerThanSize_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.get(Integer.MAX_VALUE);
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}

	@Test
	public void get_IndexBiggerThanSizeByOne_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.get(2);
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}

	//End Region
	
	//Region Add Tests
	@Test
	public void add_OneItem() {
		simpleArrayList.add("Three");
		assertEquals("Three", simpleArrayList.get(2));
		assertEquals(3, simpleArrayList.size());
	}

	@Test
	public void add_OneNullItem() {
		simpleArrayList.add(null);
		assertEquals(null, simpleArrayList.get(2));
		assertEquals(3, simpleArrayList.size());
	}

	@Test
	public void add_ForceResizeOfArray() {
		simpleArrayList.add("Three");
		simpleArrayList.add("Four");
		simpleArrayList.add("Five");
		assertEquals("Five", simpleArrayList.get(4));
		assertEquals(5, simpleArrayList.size());
	}
	//End Region
	
	//Region Size Tests
	@Test
	public void size_ReturnsValidSize() {
		assertEquals(2, simpleArrayList.size());
	}
	//End Region
	
	//Region Insert Tests
	@Test
	public void insert_Beginning() {
		String insertOutput = simpleArrayList.insert(0, "Zero");
		
		assertEquals("Zero", insertOutput);
		assertEquals(3, simpleArrayList.size());
		assertEquals("Zero", simpleArrayList.get(0));
		assertEquals("One", simpleArrayList.get(1));
		assertEquals("Two", simpleArrayList.get(2));
	}

	@Test
	public void insert_End() {
		String insertOutput = simpleArrayList.insert(2, "Three");
		
		assertEquals("Three", insertOutput);
		assertEquals(3, simpleArrayList.size());
		assertEquals("One", simpleArrayList.get(0));
		assertEquals("Two", simpleArrayList.get(1));
		assertEquals("Three", simpleArrayList.get(2));
	}

	@Test
	public void insert_Middle() {
		String insertOutput = simpleArrayList.insert(1, "OnePointFive");
		
		assertEquals("OnePointFive", insertOutput);
		assertEquals(3, simpleArrayList.size());
		assertEquals("One", simpleArrayList.get(0));
		assertEquals("OnePointFive", simpleArrayList.get(1));
		assertEquals("Two", simpleArrayList.get(2));

	}

	@Test
	public void insert_Middle_ForceResize() {
		String insertOutput = simpleArrayList.insert(1, "OnePointSevenFive");
		assertEquals("OnePointSevenFive", insertOutput);
		
		insertOutput = simpleArrayList.insert(1, "OnePointFive");
		assertEquals("OnePointFive", insertOutput);
		
		insertOutput = simpleArrayList.insert(1, "OnePointTwoFive");
		assertEquals("OnePointTwoFive", insertOutput);
		
		assertEquals(5, simpleArrayList.size());
		assertEquals("One", simpleArrayList.get(0));
		assertEquals("OnePointFive", simpleArrayList.get(2));
		assertEquals("Two", simpleArrayList.get(4));
	}

	@Test
	public void insert_NegativeIndex_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.insert(-1, "NegativeIndex");
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}
	
	@Test
	public void insert_LargeIndex_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.insert(Integer.MAX_VALUE, "LargeNumber");
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}
	//End Region

	//Region Remove Tests
	@Test
	public void remove_Beginning() {
		String removeOutput = simpleArrayList.remove(0);
		
		assertEquals("One", removeOutput);
		assertEquals(1, simpleArrayList.size());
		assertEquals("Two", simpleArrayList.get(0));
	}

	@Test
	public void remove_End() {
		String removeOutput = simpleArrayList.remove(1);
		
		assertEquals("Two", removeOutput);
		assertEquals(1, simpleArrayList.size());
		assertEquals("One", simpleArrayList.get(0));
	}

	@Test
	public void remove_Middle() {
		simpleArrayList.insert(1, "OnePointFive");
		assertEquals(3, simpleArrayList.size());

		String removeOutput = simpleArrayList.remove(1);
		assertEquals("OnePointFive", removeOutput);

		assertEquals(2, simpleArrayList.size());
		assertEquals("One", simpleArrayList.get(0));
		assertEquals("Two", simpleArrayList.get(1));
	}

	@Test
	public void remove_ForceDownsize() {

		simpleArrayList.insert(1, "OnePointSevenFive");
		simpleArrayList.insert(1, "OnePointFive");
		simpleArrayList.insert(1, "OnePointTwoFive");

		assertEquals(5, simpleArrayList.size());

		String removeOutput = simpleArrayList.remove(1);
		assertEquals("OnePointTwoFive", removeOutput);
		
		removeOutput = simpleArrayList.remove(1);
		assertEquals("OnePointFive", removeOutput);
		
		removeOutput = simpleArrayList.remove(1);
		assertEquals("OnePointSevenFive", removeOutput);
		
		assertEquals(2, simpleArrayList.size());
	}

	@Test
	public void remove_NegativeIndex_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.remove(-1);
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}

	@Test
	public void remove_LargeIndex_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.remove(Integer.MAX_VALUE);
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}

	@Test
	public void remove_RightAfterLastIndex_ThrowsIndexOutOfBoundsException() {
		try {
			String temp = simpleArrayList.remove(2);
			Assert.fail("Fail! Method was expected to throw an exception because negative numbers are not supported.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Argument was out of Bounds", e.getMessage());
		}
	}
	//End Region
}
