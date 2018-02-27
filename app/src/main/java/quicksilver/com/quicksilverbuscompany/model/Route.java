package quicksilver.com.quicksilverbuscompany.model;

import java.util.List;

public class Route {
    private String id;
    private String name;
    private List<Stop> stops;
    private String description;
    private boolean accessible;
    private String image;

    public Route() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        image = imageUrl;
    }
}
