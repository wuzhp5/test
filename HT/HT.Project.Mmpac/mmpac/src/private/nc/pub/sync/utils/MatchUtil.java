package nc.pub.sync.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;

public class MatchUtil<O, N> {

	//String维度组合、O value对象、N新key值
	public Map<N, O> replaceByDimension(Map<String, O> orignalMap, Map<String, N> Map) throws BusinessException{
		Set<String> orignalKeys = new HashSet<String>();
		Set<String> keys = new HashSet<String>();
		return null;
	}
	
}
