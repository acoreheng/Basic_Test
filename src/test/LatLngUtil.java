package test;

/**
 * 经纬度计算
 * @author AcoreHeng
 * @version 创建时间：2012-10-9 下午2:34:53
 */
public class LatLngUtil {
	final double EARTH_RADIUS = 6371000.00;// 地球的半径
	int distance = 300;

	public double costLng(int lat) {
		return 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS))
				/ Math.cos(lat));
	}

	public double costLat(int lat) {
		return 300 / EARTH_RADIUS;
	}

	public static double geo_distance(double lat1, double lng1, double lat2,
			double lng2) {
		// earth's mean radius in KM
		double r = 6378.137;
		lat1 = Math.toRadians(lat1);
		lng1 = Math.toRadians(lng1);
		lat2 = Math.toRadians(lat2);
		lng2 = Math.toRadians(lng2);
		double d1 = Math.abs(lat1 - lat2);
		double d2 = Math.abs(lng1 - lng2);
		double p = Math.pow(Math.sin(d1 / 2), 2) + Math.cos(lat1)
				* Math.cos(lat2) * Math.pow(Math.sin(d2 / 2), 2);
		double dis = r * 2 * Math.asin(Math.sqrt(p));
		return dis;
	}

	// 将角度转换为弧度
	public static double deg2rad(double degree) {
		return degree / 180 * Math.PI;
	}

	// 将弧度转换为角度
	public static double rad2deg(double radian) {
		return radian * 180 / Math.PI;
	}

	/**
	 * 计算两点间距离
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		double miles = dist * 60 * 1.1515;
		return miles;
	}
	public static void main(String[] args) {
		System.out.println(geo_distance(38.95, 123.35, 38.95, 123.34));
		System.out.println(distance(38.54, 123.35, 38.95, 123.94));
	}
}
