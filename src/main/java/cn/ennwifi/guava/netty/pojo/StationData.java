package cn.ennwifi.guava.netty.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 高压光伏上传数据请求.
 * 
 * @author zhangbo
 */
public class StationData {

  /**
   * 站ID.
   */
  public String stationId;

  /**
   * 时间.
   */
  public String timestamp;


  public List<EquipmentData> equipmentDatas;

  public StationData() {
    equipmentDatas = new ArrayList<>();
  }

  /**
   * @return the stationId
   */
  public String getStationId() {
    return stationId;
  }

  /**
   * @param stationId the stationId to set
   */
  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  /**
   * @return the timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * @return the equipmentDatas
   */
  public List<EquipmentData> getEquipmentDatas() {
    return equipmentDatas;
  }

  /**
   * @param equipmentDatas the equipmentDatas to set
   */
  public void setEquipmentDatas(List<EquipmentData> equipmentDatas) {
    this.equipmentDatas = equipmentDatas;
  }


}
