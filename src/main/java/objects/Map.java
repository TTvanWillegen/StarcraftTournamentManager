package objects;

import java.io.File;





/**
 * Creates an object for a map, containing the name of the map, the map location aswell as the
 * maximum amount of teams that can play the map.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Map {

    private String mapName;

    private File mapFile = null;

    private int maxAmountOfTeams;

    public Map( String mapName,  int maxAmountOfTeams) {
        this.mapName = mapName;
        this.maxAmountOfTeams = maxAmountOfTeams;
    }

    public void useFile( File file) {
        this.mapFile = file;
    }


    public File getMapFile() {
        return this.mapFile;
    }

    public String getMapName() {
        return mapName;
    }

    /**
     * Returns the maximum amount of {@link objects.team.Team}s that can play on this {@link Map}.
     *
     * @return {@link int} the maximum amount of {@link objects.team.Team}s
     */
    public int getMaxAmountOfTeams() {
        return maxAmountOfTeams;
    }

    @Override
    public boolean equals( Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Map map = (Map) o;

        if (maxAmountOfTeams != map.maxAmountOfTeams) {
            return false;
        }
        return
            mapName != null ? mapName.equals(map.mapName) : map.mapName == null;
    }

    @Override
    public int hashCode() {
        int result = mapName != null ? mapName.hashCode() : 0;
        result = 31 * result + maxAmountOfTeams;
        return result;
    }

    @Override
    public String toString() {
        return "Map{" + "mapName='" + mapName + '\'' + ", maxAmountOfTeams=" +
            maxAmountOfTeams + '}';
    }
}
