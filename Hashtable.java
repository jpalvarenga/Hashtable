import java.util.*;

public class Hashtable  {
	
	private static final Integer BUCKETS = 314527;
	
	public final List<HashEntry> table;

	/**
	 * Initializes Hashtable.
	 */
	public Hashtable() {
		this.table = new ArrayList<HashEntry>(BUCKETS);
		for(int i = 0; i < BUCKETS; i++) {
			table.add(null);
		}
	}
	
	/**
	 * Hashcode.
	 * @param key to generate hashcode.
	 * @return int
	 */
	public int hash(String key) {
		int hash = key.hashCode() % BUCKETS;
		return (hash < 0) ? hash * -1 : hash;
	}

	/**
	 * Get the value of a key.
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		HashEntry current = table.get(hash(key));
		while(current != null) {
			if(current.key.equals(key)){
				return current.value;
			}
			current = current.next;
		}
		return null;
	}
	
	/**
	 * Add key and value to the table
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		HashEntry current = table.get(hash(key));
		
		// First case: If head is equal to null;
		if(current == null) {
			table.set(hash(key), new HashEntry(key, value));
			return;
		}
		
		HashEntry previous = null;
		while(current != null) {
			// Second case: Head is not null and key exists.
			if(current.key.equals(key)) {
				current.value = value;
				return;
			}
			previous = current;
			current = current.next;
		}
		// Third case: Head is not null, and key does not exist.
		previous.next = new HashEntry(key, value);
	}

	/**
	 * Remove value from a table
	 * @param key
	 * @param value
	 */
	public String remove(String key) {
		HashEntry current = table.get(hash(key));
		
		if(current.key.equals(key)) {
			table.set(hash(key), null);
			return current.value;
		}
		
		HashEntry previous = null;
		while(current != null) {
			previous = current;
			current = current.next;
			
			if(current.key.equals(key)) {
				String value = current.value;
				previous.next = current.next;
				return value;
			}
		}
		return null;
	}

	public boolean containsKey(String key) {
		HashEntry current = table.get(hash(key));
		while(current != null) {
			if(current.key.equals(key)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	public class HashEntry {
		
		private HashEntry next;
		
		private final String key;
		private String value;
		
		public HashEntry(String key, String value) {
			this.next = null;
			
			this.key = key;
			this.value = value;
		}
		
		public String toString() {
			String str = key + " " + value;
			return (next == null)? str : str + "->" + next.toString();
			
		}
		
	}
	

	public static void main(String[] args) {
		Hashtable table = new Hashtable();
		table.put("joao", "1");
		table.put("joao", "2");
		table.put("ajoa", "2");
		table.put("hello", "1");
		table.remove("joao");
		System.out.println(table.containsKey("joao"));
		System.out.println(table.containsKey("ajoa"));
		System.out.println(table.table.get(table.hash("ajoa")).toString());
	}

}
