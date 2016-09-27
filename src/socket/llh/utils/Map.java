package socket.llh.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/***
 * 
 * @author dragon
 * <pre>
 * 该类用于重写相关的Map方法
 * </pre>
 * @param <K>
 * @param <V>
 */
public class Map<K,V> extends HashMap<K,V> {
	/**
	 * 重写removeByValue方法
	 * @param value
	 */
	public void removeByValue(Object value)
	{
		for(Object key : keySet())
		{
			if(get(key)==value){
				remove(key);
				break;
			}
		}
	}
	/**
	 * 重写valueSet方法
	 * @return
	 */
	public Set<V> valueSet(){
		 Set<V> result = new HashSet<V>();
		 //遍历所有Key组成的集合
		 for(K key : keySet())
		 {
			 result.add(get(key));
		 }
		 return result;
	}
	/**
	 * 重写getKeyByvalue方法
	 * @param val
	 * @return
	 */
	public K getKeyByvalue(V val)
	{
		//遍历所有key组成的集合
		for(K key : keySet())
		{
			if(get(key).equals(val)&&get(key)==val)
			{
				return key;
			}
		}
		return null;
	}
	/**
	 * 重写put方法
	 */
	public V put(K key, V value)
	{
		//遍历所有的value
		for(V val : valueSet())
		{
			//如果指定value与视图放入集合的value相同，则抛出异常
			if(val.equals(value)&&val.hashCode()==value.hashCode())
			{
				throw new RuntimeException
						("MyMap实例中不允许有重复value");
			}
		}
		return super.put(key, value);
	}
}
