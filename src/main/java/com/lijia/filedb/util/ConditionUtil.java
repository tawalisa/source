package com.lijia.filedb.util;

import com.lijia.filedb.Operation;

public class ConditionUtil {

	public static boolean compareInt(Integer id, int parseInt,
			Operation operation) {
		if(operation.equals(Operation.eq)){
			return id.equals(parseInt);
		}
		return false;
	}

}
