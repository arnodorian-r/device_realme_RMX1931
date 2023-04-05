package android.os;
import java.io.File;

public class OplusBaseEnvironment {
    private static final String PATH = "/vendor";

    public static File getOplusProductDirectory() {
	return new File(PATH);
    }

    public static File getOplusEngineerDirectory() {
	return new File(PATH);
    }

    public static File getOplusVersionDirectory() {
        return new File(PATH);
    }

    public static File getMyPreloadDirectory() {
        return new File(PATH);
    }

    public static File getMyHeytapDirectory() {
        return new File(PATH);
    }

    public static File getMyStockDirectory() {
        return new File(PATH);
    }

    public static File getMyProductDirectory() {
        return new File(PATH);
    }

    public static File getMyCountryDirectory() {
        return new File(PATH);
    }

    public static File getMyRegionDirectory() {
        return new File(PATH);
    }

    public static File getMyOperatorDirectory() {
        return new File(PATH);
    }

    public static File getMyCarrierDirectory() {
        return new File(PATH);
    }

    public static File getMyCompanyDirectory() {
        return new File(PATH);
    }

    public static File getMyEngineeringDirectory() {
        return new File(PATH);
    }
}
