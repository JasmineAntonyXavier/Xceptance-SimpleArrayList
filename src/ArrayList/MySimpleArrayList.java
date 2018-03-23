package ArrayList;

import java.util.Arrays;

public class MySimpleArrayList implements IMySimpleArrayList {

	// The underlying Array holding the raw data in the ArrayList
	private String[] myArray;

	// The Number of memory slots that are requested by the Array
	private int _physical_size;

	// The number of used slots in the array
	private int _logical_size;

	// Constructor just for unit testing, since it's default it is only usable
	// within the package
	MySimpleArrayList(String[] array, int logical_size, int physical_size) {
		this.myArray = array;
		this._logical_size = logical_size;
		this._physical_size = physical_size;
	}

	// Public constructor for external user
	public MySimpleArrayList() {
		// Default physical size of the array will be 4
		_physical_size = 4;
		_logical_size = 0;
		myArray = new String[_physical_size];
	}

	@Override
	public String get(int index) {
		if (index < 0 || index >= _logical_size) {
			throw new IndexOutOfBoundsException("Argument was out of Bounds");
		}
		return myArray[index];
	}

	@Override
	public void add(String s) {
		myArray[_logical_size] = s;
		_logical_size++;
		ResizeArray();
	}

	@Override
	/*
	 * Assumption, the String in this interface is asking for what is inserted I
	 * believe however that this can be a void interface
	 */
	public String insert(int index, String s) {
		if (index < 0 || index > _logical_size) {
			throw new IndexOutOfBoundsException("Argument was out of Bounds");
		}

		// Algorithm for Inserting:
		// 1) Store Current value at index to temp variable
		// 2) Replace Current value at index with new value
		// 3) Repeat until everything is shifted down one
		String temp = myArray[index];
		String toBeInserted = s;
		for (int i = index; i <= _logical_size; i++) {
			temp = myArray[i];
			myArray[i] = toBeInserted;
			toBeInserted = temp;
		}

		_logical_size++;

		ResizeArray();
		return s;
	}

	@Override
	// Assumption, the String in this interface is asking for what is deleted
	public String remove(int index) {
		if (index < 0 || index >= _logical_size) {
			throw new IndexOutOfBoundsException("Argument was out of Bounds");
		}

		String deletedValue = myArray[index];

		// Algorithm: Shift everything down one from the deleted index
		for (int i = index; i < _logical_size - 1; i++) {
			myArray[i] = myArray[i + 1];
		}

		_logical_size--;
		ResizeArray();
		return deletedValue;
	}

	@Override
	public int size() {
		return _logical_size;
	}

	// Used for Memory Management (Increase or Decrease space based on logical
	// size compared to physical size)
	private void ResizeArray() {
		if (_logical_size >= _physical_size) {
			_physical_size = _physical_size * 2;
			myArray = Arrays.copyOf(myArray, _physical_size);
		} else if (_physical_size / 2 >= 4 && _logical_size < (_physical_size / 2)) {
			_physical_size = _physical_size / 2;
			myArray = Arrays.copyOf(myArray, _physical_size);
		}
	}
}
