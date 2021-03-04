package hk.ust.steve.signalcollector.utils;

import android.graphics.Color;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

import hk.ust.steve.signalcollector.Consts;
import hk.ust.steve.signalcollector.bean.Position;

public class MathUtil {
	public static double getAverage(List<Double> nums) {
        double _sum = 0;
        for (double num : nums) {
            _sum += num;
        }
        return _sum / nums.size();
    }

    public static double getStandardDeviation(List<Double> nums) {
        double average = getAverage(nums);
        double _sum = 0;
        for (double num : nums) {
            double temp = num - average;
            _sum += temp * temp;
        }
        return Math.sqrt(_sum / nums.size());
    }

    public static double getMaximum(List<Double> nums) {
        double _max = nums.get(0);
        for (double num : nums) {
            if (_max < num) {
                _max = num;
            }
        }
        return _max;
    }
    
	public static PointF midPoint(PointF p1, PointF p2) {
		return new PointF((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}

	public static double distance(PointF p1, PointF p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}

	public static double distance(Position p1, Position p2) {
		return Math.sqrt(
				(p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
	}

	public static double angle(PointF origin, PointF p) {
		return Math.atan2(p.y - origin.y, p.x - origin.x);
	}

	public static double squareDistance(PointF p1, PointF p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}

	public static double mean(List<Integer> list) {
		double sum = 0;
		for (int i : list) {
			sum += i;
		}
		return sum / list.size();
	}

	public static double sum(double[] d) {
		double sum = 0;
		for (double i : d) {
			sum += i;
		}
		return sum;
	}

	static public float max(float[] a) {
		if (a.length == 1) {
			return a[0];
		}
		float maxNum = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > maxNum) {
				maxNum = a[i];
			}
		}
		return maxNum;
	}

	static public float[] averageSensorValue(ArrayList<float[]> a) {
		float result[] = a.get(0).clone();
		for (int i = 1; i < a.size(); i++) {
			for (int j = 0; j < result.length; j++) {
				result[j] += a.get(i)[j];
			}
		}
		for (int j = 0; j < result.length; j++) {
			result[j] /= a.size();
		}
		return result;
	}

	static public float normalizeDegree(float degree) {
		while (degree >= 180)
			degree -= 360;
		while (degree < -180)
			degree += 360;
		return degree;
	}

	static public float stdInRange(List<Float> data, int start, int end) {
		if (start == end)
			return 0;
		float mean = meanInRange(data, start, end);
		float std = 0;
		for (int i = start; i < end; i++) {
			float temp = data.get(i) - mean;
			std += temp * temp;
		}
		return (float) Math.sqrt(std / (end - start));
	}

	static public float meanInRange(List<Float> data, int start, int end) {
		if (start == end)
			return 0;
		float sum = 0;
		for (int i = start; i < end; i++) {
			sum += data.get(i);
		}
		return sum / (end - start);
	}

	/**
	 * check in the specific scope of the list, whether there exists a number
	 * above a certain value
	 */
	static public boolean hasDataAboveValue(List<Float> data, int start, int end, float value) {
		for (int i = start; i < end; i++) {
			if (data.get(i) > value) {
				return true;
			}
		}
		return false;
	}

//	public static boolean posOnSegment(Position pos, Position segBegin, Position segEnd) {
//		return ((pos.getX() - segBegin.getX()) * (segEnd.getY() - segBegin.getY()) == (segEnd.getX() - segBegin.getX())
//				* (pos.getY() - segBegin.getY()) && Math.min(segBegin.getX(), segEnd.getX()) <= pos.getX()
//				&& pos.getX() <= Math.max(segBegin.getX(), segEnd.getX())
//				&& Math.min(segBegin.getY(), segEnd.getY()) <= pos.getY()
//				&& pos.getY() <= Math.max(segBegin.getY(), segEnd.getY()));
//	}

    public static boolean posOnSegment2(Position pos, Position segBegin, Position segEnd) {
        double px = segBegin.getX(), py = segBegin.getY();
        double qx = pos.getX(), qy = pos.getY();
        double rx = segEnd.getX(), ry = segEnd.getY();
        boolean onLine = Math.abs((qx * ry - qy * rx) - (px * ry - py * rx) + (px * qy - py * qx) ) < 1e-6;
        if (!onLine) {
        	return false;
        } else {
        	if (Math.abs(px - qx) < Consts.eps) {
        		return (py <= qy && qy <= ry) || (ry <= qy && qy <= py);
        	} else {
        		return (px <= qx && qx <= rx) || (rx <= qx && qx <= px);
        	}
        }
    }

	public static double getSlope(Position p1, Position p2) {
		if (Math.abs((p1.getX() - p2.getX())) < Consts.eps) {
			return Double.NaN;
		}
		return (p1.getY() - p2.getY()) * 1.0 / (p1.getX() - p2.getX());
	}

	/**
	 * check if pos is above/on/below the line(pointInLine, slope)
	 * if the slope is Nan, it means the slope is 90 degree, 
	 * and then the function will check if the pos is on the left of/on/on the right of the line(pointInLine, slope)
	 * 
	 * @param pos the position to be checked
	 * @param pointInLine a point in the line
	 * @param slope the slope of the line
	 * @return
	 */
	public static int posAccording2Line(Position pos, Position pointInLine, double slope) {
		double temp = 0;
		if (Double.isNaN(slope)) {
			temp = pos.getX() - pointInLine.getX();
		} else {
			temp = pos.getY() - (slope * (pos.getX() - pointInLine.getX()) + pointInLine.getY());
		}
		if (Math.abs(temp) < Consts.eps) {
			return 0;
		} else if (temp < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	public static double getDistFromPos2LineSeg(Position pos, Position segBegin, Position segEnd) {
		double cross = (segEnd.getX() - segBegin.getX()) * (pos.getX() - segBegin.getX())
				+ (segEnd.getY() - segBegin.getY()) * (pos.getY() - segBegin.getY());
		if (cross <= 0) {
			return distance(pos, segBegin);
		}

		double line_len_square = Math.pow(distance(segBegin, segEnd), 2);
		if (cross >= line_len_square) {
			return distance(pos, segEnd);
		}

		double rate = cross / line_len_square;
		double px = segBegin.getX() + (segEnd.getX() - segBegin.getX()) * rate;
		double py = segBegin.getY() + (segEnd.getY() - segBegin.getY()) * rate;
		Position answer = new Position(px, py);
		return distance(pos, answer);
	}
	
	public static List<Integer> getTurningPoint(List<Position> allPos) {
		List<Integer> answer = new ArrayList<Integer>();
		for (int i = 1; i < allPos.size() - 1; i++) {
			if (!posOnSegment2(allPos.get(i), allPos.get(i - 1), allPos.get(i + 1))) {
				answer.add(i);
			}
		}
		return answer;
	}

	public static List<Integer> genColorSet(int beginColor, int endColor, int size) throws Exception {
        if (size == 0) {
            throw new Exception("size too small to generate a list");
        } else if (size == 1){
            List<Integer> result = new ArrayList<Integer>();
            result.add(endColor);
            return result;
        }
        int beginR = Color.red(beginColor), beginG = Color.green(beginColor), beginB = Color.blue(beginColor);
        int endR = Color.red(endColor), endG = Color.green(endColor), endB = Color.blue(endColor);
        double increaseR = 1.0 * (endR - beginR) / (size - 1),
                increaseG = 1.0 * (endG - beginG) / (size - 1),
                increaseB = 1.0 * (endB - beginB) / (size - 1);
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            int currentR = (int)(i * increaseR + beginR);
            int currentG = (int)(i * increaseG + beginG);
            int currentB = (int)(i * increaseB + beginB);
            result.add(Color.rgb(currentR, currentG, currentB));
        }
        return result;
    }

}
