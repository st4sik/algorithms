package com.algo.quiz;

/**
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed
 * immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of nn distinct
 * integer values, determines whether a given integer is in the array.
 */
public class BitonicArray {

	public int ascBinarySearch(int arr[], int low, int high, int key) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == key) {
				return mid;
			} else if (arr[mid] > key) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public int descBinarySearch(int arr[], int low, int high, int key) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == key) {
				return mid;
			} else if (arr[mid] < key) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public int bitonicSearch(int[] arr, int index, int key) {
		if (key > arr[index]) {
			return -1;
		} else if (key == arr[index]) {
			return index;
		} else if (key < arr[index]) {
			int element = ascBinarySearch(arr, 0, index - 1, key);
			if (element != -1) {
				return element;
			} else {
				return descBinarySearch(arr, index, arr.length - 1, key);
			}
		}
		return -1;
	}


	public int max(int[] arr, int low, int high) {
		int mid = low + (high - low) / 2;

		if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
			return mid;
		} else if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
			max(arr, mid, high);
		} else if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
			max(arr, low, mid);
		}
		return -1;
	}

	public static void main(String[] argc) {
		BitonicArray bitonicArray = new BitonicArray();

		int arr[] = {1, 3, 4, 6, 9, 14, 11, 5, 2, -4, -9};
		int key = 5;
		int low = 0;
		int high = arr.length - 1;
		int max = bitonicArray.max(arr, low, high);
		int i = bitonicArray.bitonicSearch(arr, max, key);
		System.out.println("Index = " + i + " Element = " + key + " ");
	}
}