package mfwallace.acserver;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model class representing an aircraft in the queue.
 */
public class Aircraft implements Comparable<Aircraft> {

    /** The type of plane.  The order of the enums goes from Most Important to Least Important. */
    public enum ModelType {
        Emergency, VIP, Passenger, Cargo
    }

    /** The size of plane.  The order of the enums goes from Most Important to Least Important. */
    public enum ModelSize {
        Large, Small
    }

    /** The plane ID. */
    private int id = 0;

    /** The type of aircraft. */
    private ModelType modelType = ModelType.VIP;

    /** The size of the aircraft. */
    private ModelSize modelSize = ModelSize.Small;

    /** The date/time the aircraft was added to the queue. */
    @JsonIgnore
    private long dateAdded;

    /**
     * Default constructor.
     */
    public Aircraft() {
        dateAdded = System.currentTimeMillis();
    }

    /**
     * Constructor taking the parameters for the aircraft.
     *
     * @param id the plane ID
     * @param modelType the model type
     * @param modelSize the model size
     */
    public Aircraft(final int id, final ModelType modelType, final ModelSize modelSize) {
        this.id = id;
        setModelType(modelType);
        setModelSize(modelSize);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(final ModelType modelType) {

        if (modelType != null) {
            this.modelType = modelType;
        }
    }

    public ModelSize getModelSize() {
        return modelSize;
    }

    public void setModelSize(final ModelSize modelSize) {

        if (modelSize != null) {
            this.modelSize = modelSize;
        }
    }

    @Override
    public String toString() {
        return String.format("Aircraft: ID: %d, Type: '%s', Size: '%s'", id, modelType, modelSize);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(final Aircraft o) {

        // Last resort, planes added earlier get removed first
        final int shallowCompare = shallowCompare(o);
        return ((shallowCompare == 0) ? Long.compare(dateAdded, o.dateAdded) : shallowCompare);
    }

    /**
     * Helper method to compare just the salient fields for an aircraft.
     *
     * @param o the aircraft we're comparing this to
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    public int shallowCompare(final Aircraft o) {

        // First check the model type for sorting
        if (!modelType.equals(o.modelType)) {
            return Integer.compare(modelType.ordinal(), o.modelType.ordinal());
        } else if (!modelSize.equals(o.modelSize)) {
            // The model size is different, so return that comparison
            return Integer.compare(modelSize.ordinal(), o.modelSize.ordinal());
        }

        // The type and size are the same, so return 0
        return 0;
    }
}
