package objects;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Map {
	private String mapName;
	private int amountOfPeople;
	public Map( String mapName, int amountOfPeople){
		this.mapName = mapName;
		this.amountOfPeople = amountOfPeople;
	}

	public String getMapName() {
		return mapName;
	}

	public int getAmountOfPeople() {
		return amountOfPeople;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Map map = (Map) o;

		if (amountOfPeople != map.amountOfPeople) {
			return false;
		}
		return mapName != null ? mapName.equals(map.mapName) : map.mapName == null;
	}

	@Override
	public int hashCode() {
		int result = mapName != null ? mapName.hashCode() : 0;
		result = 31 * result + amountOfPeople;
		return result;
	}

	@Override
	public String toString() {
		return "Map{" +
			"mapName='" + mapName + '\'' +
			", amountOfPeople=" + amountOfPeople +
			'}';
	}
}
