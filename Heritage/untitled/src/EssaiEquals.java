import com.sun.xml.internal.ws.spi.db.RepeatedElementBridge;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Objects;

public class EssaiEquals {

    int essaiNombre;
    int essaiString;

    public EssaiEquals(int essaiNombre, int essaiString) {
        this.essaiNombre = essaiNombre;
        this.essaiString = essaiString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EssaiEquals that = (EssaiEquals) o;
        return essaiNombre == that.essaiNombre &&
                essaiString == that.essaiString;
    }

    @Override
    public String toString() {
        return "EssaiEquals{" +
                "essaiNombre=" + essaiNombre +
                ", essaiString=" + essaiString +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(essaiNombre, essaiString);
    }

    public int getEssaiNombre() {
        return essaiNombre;
    }

    public int getEssaiString() {
        return essaiString;
    }

}
