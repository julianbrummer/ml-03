package uni.ml.dataset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lombok.NonNull;

/**
 * An attribute with a predefined enumerable list of values.
 * Note the values do not have to be enums.
 * @author Julian Brummer
 *
 * @param <T> The type of the attribute. This can be any type, but it must be comparable.
 */
public class EnumAttribute<T extends Comparable<T>> extends Attribute<T> implements Iterable<Value<T>> {

	private List<Value<T>> values;
	
	/**
	 * Creates an Attribute with a list of allowed values.
	 * @param name The name of the attribute.
	 */
	@SafeVarargs
	public EnumAttribute(@NonNull String name, Value<T>... values) {
		super(name);
		this.values = Arrays.asList(values);
	}
	
	/**
	 * Creates an Attribute with a list of allowed values.
	 * @param name The name of the attribute.
	 */
	@SafeVarargs
	public EnumAttribute(@NonNull String name, T... values) {
		super(name);
		this.values = new ArrayList<>();
		for (T v : values) {
			this.values.add(new Value<T>(v));
		}
	}
	
	/**
	 * Adds a new value to the list of allowed values assignable to this attribute.
	 * @param value
	 */
	public void addValue(Value<T> value) {
		values.add(value);
	}
	
	@Override
	public boolean isAllowed(Value<?> value) {
		return values.contains(value);
	}

	@Override
	public Iterator<Value<T>> iterator() {
		return values.iterator();
	}
	
	public Iterator<Value<T>> values() {
		return values.iterator();
	}

}
