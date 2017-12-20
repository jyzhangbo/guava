package cn.ennwifi.guava.netty.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangbo
 *
 */
public class EquipmentData {

  public String equipmentType;

  public String equipmentCode;

  public List<MetaData> datas;

  public EquipmentData() {
    datas = new ArrayList<>();
  }

  /**
   * @return the equipmentType
   */
  public String getEquipmentType() {
    return equipmentType;
  }

  /**
   * @param equipmentType the equipmentType to set
   */
  public void setEquipmentType(String equipmentType) {
    this.equipmentType = equipmentType;
  }

  /**
   * @return the equipmentCode
   */
  public String getEquipmentCode() {
    return equipmentCode;
  }

  /**
   * @param equipmentCode the equipmentCode to set
   */
  public void setEquipmentCode(String equipmentCode) {
    this.equipmentCode = equipmentCode;
  }

  /**
   * @return the datas
   */
  public List<MetaData> getDatas() {
    return datas;
  }

  /**
   * @param datas the datas to set
   */
  public void setDatas(List<MetaData> datas) {
    this.datas = datas;
  }

}
