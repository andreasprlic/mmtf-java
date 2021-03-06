package org.rcsb.mmtf.encoder;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for the array encoder util functions.
 * @author Anthony Bradley
 *
 */
public class TestArrayEncoders {

	/**
	 * Run length decode int test.
	 */
	@Test
	public void runLengthDecodeTest() {
		// Allocate the byte array
		int[] inputData = {15,15,15,100,100,111,111,111,111};
		int[] outputDataTest =  {15,3,100,2,111,4};
		int[] outputData = ArrayEncoders.runlengthEncode(inputData);
		assertArrayEquals(outputDataTest, outputData);
	}
	
	/**
	 * Run length decode int test on empty arrays.
	 */
	@Test
	public void emptyRunLengthDecodeTest() {
		// Allocate the byte array
		int[] inputData = {};
		int[] outputDataTest =  {};
		int[] outputData = ArrayEncoders.runlengthEncode(inputData);
		assertArrayEquals(outputDataTest, outputData);
	}
	
	/**
	 * Delta decode int test.
	 */
	@Test
	public void deltaDecodeTest() {
		// Allocate the byte array
		int[] inputData = {15,18,118,117,128,132};
		int[] outputDataTest =  {15,3,100,-1,11,4};
		int[] outputData = ArrayEncoders.deltaEncode(inputData);
		assertArrayEquals(outputDataTest, outputData);
	}
	
	
	/**
	 * Delta decode int test on empty arrays.
	 */
	@Test
	public void emptyDeltaDecodeTest() {
		// Allocate the byte array
		int[] inputData = {};
		int[] outputDataTest =  {};
		int[] outputData = ArrayEncoders.deltaEncode(inputData);
		assertArrayEquals(outputDataTest, outputData);
	}
}
