package com.example.simplestore.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.simplestore.entity.IEntity;

public abstract class AbstractBean<T> {

	protected Long nextId = null;
	protected HashMap<Long, T> data = null;

	private HashMap<Long, T> getData() {
		if(data == null) {
			initDataStore();
		}
		return data;
	}

	/**
	 * @return 
	 * @return
	 */
	public void initDataStore() {
		data =  new HashMap<Long, T>();
		nextId = 0L;
	}

	public Long add( T item ) {
		Long newId = nextId++;
		((IEntity) item).setId(newId);
		getData().put(newId, item);
		
		return newId;
	}
	public boolean remove( T item ) {
		return getData().remove(((IEntity) item).getId(), item);
	}
	public T update( T item ) {
		return getData().put(((IEntity) item).getId(), item);
	}
	public T getOne( Long key ) {
		return getData().get(key);
	}
	public Collection<T> getAll() {
		return getData().values();
	}

	public List<T> fetchEntities(int offset, int limit) {
		List<T> result = new ArrayList<T>();

		Collection<T> collection = getData().values();
		Long count = 0L;
		for (T t : collection) {
			Long id = ((IEntity) t).getId();
			if( id > offset && count < limit ) {
				result.add(t);
				count++;
			}
		}

		return result;
	}
	public int getCount() {
		return getData().size();
	}

	public List<T> fetchFilteredEntities( int offset, int limit, Map<String, Object> parameters ) {
		List<T> result = new ArrayList<T>();
		Collection<T> collection = getData().values();
		int count = 0;
		for (T t : collection) {
			Long id = (Long) invokeMethod(t, "getId");

			if( id != null && id >= offset && count < limit ) {
				if(parameters != null) {
					boolean filterOut = false;
					for (String key : parameters.keySet()) {
						Object obj = invokeMethod(t, key);
						Object objFilter = parameters.get(key);

						// Filter numbers
						if(Number.class.isAssignableFrom(objFilter.getClass())) {
							if(objFilter != null && obj != null && !obj.equals(objFilter)) {
								filterOut = true;
								break;
							}
						}

						// Filter character sequences
						else if(CharSequence.class.isAssignableFrom(objFilter.getClass())) {
							
							if(objFilter != null && obj != null && !(obj.toString().contains(objFilter.toString()))) {
								filterOut = true;
								break;
							}
						}
					}
					if(!filterOut) {
						result.add(t);
						count++;
					}
				}
				else {
					result.add(t);
					count++;
				}
			}
		}

		return result;
	}
	public int getCount( Map<String, Object> parameters ) {
		Collection<T> collection = getData().values();
		int count = 0;
		for (T t : collection) {
			if(parameters != null) {
				boolean filterOut = false;
				for (String key : parameters.keySet()) {
					Object obj = invokeMethod(t, key);
					Object objFilter = parameters.get(key);
					
					// Filter numbers
					if(Number.class.isAssignableFrom(objFilter.getClass())) {
						if(objFilter != null && obj != null && !obj.equals(objFilter)) {
							filterOut = true;
							break;
						}
					}

					// Filter character sequences
					else if(CharSequence.class.isAssignableFrom(objFilter.getClass())) {
						
						if(objFilter != null && obj != null && !(obj.toString().contains(objFilter.toString()))) {
							filterOut = true;
							break;
						}
					}
				}
				if(!filterOut) {
					count++;
				}
			}
			else {
				count++;
			}
		}

		return count;
	}

	/**
	 * @param t
	 * @param id
	 * @param args
	 * @return
	 */
	@SuppressWarnings("all")
	protected Object invokeMethod(T t, String methodName,  Class... args) {
		Object ret = null;
		Method method;
		try {
			method = t.getClass().getMethod(methodName, args);
			ret = method.invoke(t, args);
		} 
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return ret;
	}

	/**
	 * @param t
	 * @param id
	 * @return
	 */
	@SuppressWarnings("all")
	protected Object invokeMethod(T t, String methodName) {
		return invokeMethod(t, methodName, null);
	}
}
