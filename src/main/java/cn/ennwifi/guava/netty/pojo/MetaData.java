package cn.ennwifi.guava.netty.pojo;

/**
 * @author zhangbo
 *
 */
public class MetaData {

  public String metric;

  public String value;

  /**
   * @return the metric
   */
  public String getMetric() {
    return metric;
  }

  /**
   * @param metric the metric to set
   */
  public void setMetric(String metric) {
    this.metric = metric;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

}
