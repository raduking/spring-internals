package org.raduking.spring.internals.perf.service.lists;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class ListNewWithStreams extends ListsConcatenation {

	@Override
	public Object runStep() {
		return Stream.concat(list1.stream(), list2.stream()).toList();
	}

}
