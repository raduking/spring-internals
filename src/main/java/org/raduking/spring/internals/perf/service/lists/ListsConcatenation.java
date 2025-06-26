package org.raduking.spring.internals.perf.service.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.stressium.AbstractLoopPerformanceService;

public abstract class ListsConcatenation extends AbstractLoopPerformanceService<ListsConcatenation.Settings> {

	public static final int DEFAULT_LIST_SIZE = 6;

	protected List<Integer> list1;
	protected List<Integer> list2;

	@Override
	public Class<Settings> getCustomSettingsClass() {
		return Settings.class;
	}

	@Override
	public void setUp(Settings settings) {
		list1 = new ArrayList<>(buildList(settings));
		list2 = new ArrayList<>(buildList(settings));
	}

	@Override
	public void tearDown() {
		list1 = null;
		list2 = null;
	}

	protected List<Integer> buildList(Settings settings) {
		return IntStream.range(0, settings.getListSize()).mapToObj(Integer::valueOf).toList();
	}

	static class Settings extends AbstractLoopPerformanceService.Settings {

		private int listSize = DEFAULT_LIST_SIZE;

		public int getListSize() {
			return listSize;
		}

		public void setListSize(int listSize) {
			this.listSize = listSize;
		}

	}

}
