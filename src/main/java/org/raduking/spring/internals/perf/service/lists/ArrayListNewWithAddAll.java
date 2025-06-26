package org.raduking.spring.internals.perf.service.lists;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ArrayListNewWithAddAll extends ListsConcatenation {

	@Override
	public Object runStep() {
		List<Integer> result = new ArrayList<>(list1);
		result.addAll(list2);
		return list1;
	}

}
