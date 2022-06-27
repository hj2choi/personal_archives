package hk.ust.steve.signalcollector.bean;

import java.util.Iterator;
import java.util.LinkedList;

public class DataLinkedList {
	private int mMaxSize;
	private int mDataCount;
	private LinkedList<float[]> mLinkedList;
	private float[] mSums;
	
	public DataLinkedList(int maxSize, int dataCount) {
		mMaxSize = maxSize;
		mDataCount = dataCount;
		mLinkedList = new LinkedList<float[]>();
		mSums = new float[dataCount];
	}
	
	public void addData(float[] data) {
		mLinkedList.add(data);
		for ( int i = 0; i < mSums.length; i++ ) {
			mSums[i] += data[i];
		}
		
		if ( mLinkedList.size() > mMaxSize ) {
			float[] dropData = mLinkedList.get(0);
			for ( int i = 0; i < mSums.length; i++ ) {
				mSums[i] -= dropData[i];
			}
			mLinkedList.remove(0);
		}
	}
	
	public float[] getWeightedAverageFilteredData() {
		float[] data = new float[mLinkedList.get(0).length];
		
		for ( int i = 0; i < mSums.length; i++ ) {
			data[i] = mSums[i] / mLinkedList.size();
		}
		
		return data;
	}
	
	public float[] getMeanData() {
		float[] data = new float[mLinkedList.get(0).length];
		
		for ( int i = 0; i < mSums.length; i++ ) {
			data[i] = mSums[i] / mLinkedList.size();
		}
		
		return data;
	}
	
	public float[] getStandDeviation() {
		float[] data = new float[mLinkedList.get(0).length];
		float[] average = getMeanData();

		if ( mLinkedList.size() == 1 ) {
			return average;
		}
		
		Iterator<float[]> iterator = mLinkedList.iterator();
		while ( iterator.hasNext() ) {
			float[] temp = iterator.next();
			for ( int i = 0; i < temp.length; i++ ) {
				data[i] += Math.pow(temp[i]-average[i], 2);
			}
			
			
		}
		for ( int i = 0; i < average.length; i++ ) {
			data[i] = (float) Math.sqrt(data[i]/ (mLinkedList.size()-1));
		}
		
		return data;
	}
	
	public void clear() {
		mLinkedList.clear();
		for ( int i = 0; i < mSums.length; i++ ) {
			mSums[i] = 0;
		}
	}

}
