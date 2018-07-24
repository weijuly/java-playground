package com.weijuly.play;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListFlattenTest {

	@SuppressWarnings("unchecked")
	public List flatten(List nested) {
		List result = new ArrayList();
		for (Object item : nested) {
			if (item instanceof List) {
				result.addAll(flatten((List) item));
			} else {
				result.add(item);
			}
		}
		return result;
	}


	@Test
	public void test() {
		Assert.assertEquals(flatten(oned()).size(), 3);
		Assert.assertEquals(flatten(twod()).size(), 9);
		Assert.assertEquals(flatten(threed()).size(), 27);
		Assert.assertEquals(flatten(fourd()).size(), 81);
	}

	private List<String> oned() {
		List<String> oned = new ArrayList<>();
		oned.add("one");
		oned.add("two");
		oned.add("three");
		return oned;
	}

	private List<List<String>> twod() {
		List<List<String>> twod = new ArrayList<>();
		twod.add(oned());
		twod.add(oned());
		twod.add(oned());
		return twod;
	}

	private List<List<List<String>>> threed() {
		List<List<List<String>>> threed = new ArrayList<>();
		threed.add(twod());
		threed.add(twod());
		threed.add(twod());
		return threed;
	}

	private List<List<List<List<String>>>> fourd() {
		List<List<List<List<String>>>> fourd = new ArrayList<>();
		fourd.add(threed());
		fourd.add(threed());
		fourd.add(threed());
		return fourd;
	}

}
