package modele.carte;

import java.util.Comparator;
import java.util.Map;

/**
 * MapValueComparator permet de trier une Map en fonction de ses valeurs et non de ses cl�s en passant 
 * les valeurs correspondantes aux cl�s dans la m�thode compare de {@link MapValueComparator#compare}.
 *
 * @param <K>
 * @param <V>
 * 
 * MapValueComparator est repr�sent� par : 
 * <ul>
 * <li> maptoSort, une map quelconque </li>
 * <li> valueComparator, un Comparator </li>
 * </ul>
 */
public class MapValueComparator<K, V> implements Comparator<K>{

	private final Map<K, V> mapToSort;
	private final Comparator<V> valueComparator;

	/**
	 * Constructeur instanciant maptoSort et valueComparator. 
	 * Passer valueComparator en argument permet de comparer les valeurs de la Map cl� par cl�.
	 * @param mapToSort
	 * @param valueComparator
	 */
	public MapValueComparator(Map<K, V> mapToSort, Comparator<V> valueComparator){
		this.mapToSort = mapToSort;
		this.valueComparator = valueComparator;
	}

	/**
	 * Compare deux valeurs de la Map
	 */
	@Override
	public int compare(K key1, K key2) 
	{

		return valueComparator.compare(mapToSort.get(key1), mapToSort.get(key2));
	}


}
