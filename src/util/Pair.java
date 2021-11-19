package util;

import java.util.Objects;

/**
 * A standard generic Pair<X,Y>, with severals functions well implemented.
 * 
 * @param <X> first {@link Class} type
 * @param <Y> second {@link Class} type
 */
public class Pair<X, Y> {

    private X x;
    private Y y;

    /**
     * Creates the pair.
     * 
     * @param x first element
     * @param y second element
     */
    public Pair(final X x, final Y y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);

    }

    /**
     * @return the x
     */
    public X getFirst() {
        return this.x;
    }

    /**
     * @return the y
     */
    public Y getSecond() {
        return this.y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Pair [x = " + this.x + ", y = " + this.y + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
        result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (this.x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!this.x.equals(other.x)) {
            return false;
        }
        if (this.y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!this.y.equals(other.y)) {
            return false;
        }
        return true;
    }

}
