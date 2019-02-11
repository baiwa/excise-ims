package th.co.baiwa.buckwaframework.common.persistence.dao;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Batch update callback interface used by the {@link CommonJdbcDao#executeBatch(String, BatchSetter)} class.
 *
 * @author: Taechapon Himarat (Su)
 * @since November 9, 2017
 */
public interface BatchSetter<T> {
	
	/**
	 * Return the List of the batch process.
	 * @return the List for execute in the batch process
	 */
	public List<T> getBatchObjectList();
	
	/**
	 * Prepare object in {@link getBatchObjectList} to Array of Object for using in batch process
	 * @param obj the Object will be using in {@link CommonJdbcDao#preparePs(PreparedStatement, Object[])}
	 * @return the Array of Object
	 */
	public Object[] toObjects(T obj);
	
	/**
	 * Return the size of the batch for call {@link PreparedStatement#executeUpdate()} method.
	 * @return the number for execute in the batch process
	 */
	public int getExecuteSize();
	
}
